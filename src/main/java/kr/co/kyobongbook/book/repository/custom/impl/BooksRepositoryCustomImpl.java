package kr.co.kyobongbook.book.repository.custom.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
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
//        Page
        Pageable pageable = request.toPageable();
//        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

//        Path<?> path = books.getString(sortColumn); // 동적으로 컬럼 선택
//        Order order = sortDirection.equalsIgnoreCase("asc") ? Order.ASC : Order.DESC;

//        OrderSpecifier<?> orderBy = new OrderSpecifier<>(order, path);
        List<Books> list = jpaQueryFactory.selectFrom(books)
                .join(books.bookCategories, bookCategories).fetchJoin()//N+1 해결
                .join(bookCategories.category, categories).fetchJoin()
                .where(categoryNameEq(request.getCategoryName())
                        , bookTitleEq(request.getTitle()),
                        bookAuthorEq(request.getAuthor())
                ).offset(request.getPage())
                .limit(request.getSize())
//                .orderBy(getOrderSpecifier(request.toTargetClass(), request.toVariable(), request.toSort()).stream().toArray(OrderSpecifier[]::new))
//                .orderBy()
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

    private <T> List<OrderSpecifier> getOrderSpecifier(Class<T> type, String variable, Sort sort) {
        List<OrderSpecifier> list = new ArrayList<>();
        sort.forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(type, variable);
            list.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        return list;
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
