package kr.co.kyobongbook.book.repository.specification;

import static kr.co.kyobongbook.book.infra.constant.BookConstants.AUTHOR;
import static kr.co.kyobongbook.book.infra.constant.BookConstants.BOOK_CATEGORIES;
import static kr.co.kyobongbook.book.infra.constant.BookConstants.CATEGORY;
import static kr.co.kyobongbook.book.infra.constant.BookConstants.TITLE;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import kr.co.kyobongbook.book.entity.Book;
import kr.co.kyobongbook.book.entity.BookCategory;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class BookSpecification implements Specification<Book> {

    private final String title;
    private final String author;
    private final List<CategoryEnums> categories;

    public BookSpecification(String title, String author, List<CategoryEnums> categories) {
        this.title = title;
        this.author = author;
        this.categories = categories;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {
        Predicate conditions = criteriaBuilder.conjunction();
        if (StringUtils.hasText(this.title)) conditions = criteriaBuilder.and(conditions, criteriaBuilder.like(root.get(TITLE), this.title + "%"));
        if (StringUtils.hasText(this.author)) conditions = criteriaBuilder.and(conditions, criteriaBuilder.like(root.get(AUTHOR), this.author + "%"));
        if (!CollectionUtils.isEmpty(this.categories)) {
            Join<Book, BookCategory> bookCategoryJoin = root.join(BOOK_CATEGORIES);
            conditions = criteriaBuilder.and(conditions,
                    bookCategoryJoin.get(CATEGORY).in(this.categories));
        }
        return conditions;
    }
}
