package kr.co.kyobongbook.book.repository.custom.impl;

import static kr.co.kyobongbook.book.infra.constant.BookConstants.ASC;
import static kr.co.kyobongbook.book.infra.constant.BookConstants.CATEGORY_ID;
import static kr.co.kyobongbook.book.infra.constant.BookConstants.CATEGORY_NAME;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.entity.Books;
import kr.co.kyobongbook.book.entity.QBookCategories;
import kr.co.kyobongbook.book.entity.QBooks;
import kr.co.kyobongbook.book.entity.QCategories;
import kr.co.kyobongbook.book.repository.custom.BooksRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BooksRepositoryCustomImpl implements BooksRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QBooks books = QBooks.books;
    private static final QCategories categories = QCategories.categories;
    private static final QBookCategories bookCategories = QBookCategories.bookCategories;

    @Override
    @Transactional(readOnly = true)
    public Page<Books> findBooksByParams(FindBooksRequest request) {
        Pageable pageable = request.toPageable();
        List<Books> list = jpaQueryFactory.selectFrom(books)
                .join(books.bookCategories, bookCategories).fetchJoin()//N+1 해결
                .join(bookCategories.category, categories).fetchJoin()
                .where(categoryNameEq(request.getCategoryName())
                        , bookTitleEq(request.getTitle()),
                        bookAuthorEq(request.getAuthor())
                ).offset(request.getPage())
                .limit(request.getSize())
                .orderBy(sortClassification(request.toSort(), request.getSort(), request.getSortDirection()))
                .fetch();
        JPAQuery<Long> count = jpaQueryFactory.select(books.count()).from(books)
                .join(books.bookCategories, bookCategories).fetchJoin()
                .join(bookCategories.category, categories).fetchJoin()
                .where(categoryNameEq(request.getCategoryName())
                        , bookTitleEq(request.getTitle()),
                        bookAuthorEq(request.getAuthor())
                );
        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    @Override
    public void updateBookCategory(Long bookId, UpdateBookRequest request) {
        jpaQueryFactory.update(bookCategories)
                .set(bookCategories.bookCategoryId.categoryId, request.getUpdateCategoryId())
                .where(bookCategories.bookCategoryId.bookId.eq(bookId), bookCategories.bookCategoryId.categoryId.eq(
                        request.getCategoryId())).execute();
    }

    private OrderSpecifier<?>[] sortClassification(Sort sorts, String sortName,
            String sortDirection) {
        if (sortName.equals(CATEGORY_ID) || sortName.equals(CATEGORY_NAME)) {
            return getSortedColumnCategories(sorts, sortDirection);
        }
        return getSortedColumnBooks(sorts);
    }

    private OrderSpecifier<?>[] getSortedColumnCategories(Sort sortName, String sortDirection) {
        if (CATEGORY_ID.equals(sortName)) return new OrderSpecifier[]{sortDirection.equals(ASC) ? categories.categoryId.asc() : categories.categoryId.desc()};

        return new OrderSpecifier[]{sortDirection.equals(ASC) ? categories.categoryName.asc() : categories.categoryName.desc()};

    }

    private OrderSpecifier<?>[] getSortedColumnBooks(Sort sorts) {
        return sorts.toList().stream().map(x -> {
            Order direction = x.isAscending() ? Order.ASC : Order.DESC;
            SimplePath<Object> filedPath = Expressions.path(Object.class, books, x.getProperty());
            return new OrderSpecifier(direction, filedPath);
        }).toArray(OrderSpecifier[]::new);
    }

    private BooleanExpression categoryNameEq(String categoryName) {
        if (StringUtils.isNullOrEmpty(categoryName)) {
            return null;
        }
        return categories.categoryName.eq(categoryName);
    }

    private BooleanExpression bookTitleEq(String title) {
        if (StringUtils.isNullOrEmpty(title)) {
            return null;
        }
        return books.title.eq(title);
    }

    private BooleanExpression bookAuthorEq(String author) {
        if (StringUtils.isNullOrEmpty(author)) {
            return null;
        }
        return books.author.eq(author);
    }

}
