package kr.co.kyobongbook.book.dto.get.request;

import ch.qos.logback.core.util.StringUtil;
import java.util.List;
import kr.co.kyobongbook.book.entity.Book;
import kr.co.kyobongbook.book.entity.BookCategory;
import kr.co.kyobongbook.book.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindBooksRequest {

    List<BookCategory> bookCategoryList;
    @Length(max = 100, message = "카테고리명은 100자를 초과할 수 없습니다.")
    private String categoryName;
    @Length(max = 1000, message = "제목은 1000자를 초과할 수 없습니다.")
    private String title;
    @Length(max = 1000, message = "저자는 1000자를 초과할 수 없습니다.")
    private String author;

    private String sort;

    private String sortDirection;

    private Integer page;

    private Integer size;

    public Pageable toPageable() {
        if (page == null) {
            return Pageable.ofSize(100);//default
        }
        return PageRequest.of(this.page, this.size);

    }

    public PageRequest toPageRequest() {
        return PageRequest.of(this.page, this.size, toSortDirection(), toPageRequestSort());
    }

    //    private final QBooks books = QBooks.books;
//    private final QCategories categories = QCategories.categories;
//    private final QBookCategories bookCategories = QBookCategories.bookCategories;
    public Sort toSort() {
        return Sort.by(toSortDirection(), toPageRequestSort());
    }

    public String toVariable() {
        //조회 필터에 있는 값들만  조건으로
        if (this.sort.equals("categoryName")) {
            return "categories";
        }
        return "books";
    }

    public Class toTargetClass() {
        if (this.sort.equals("categoryName")) {
            return Category.class;
        }
        return Book.class;
    }

    private String toPageRequestSort() {
        if (StringUtil.isNullOrEmpty(this.sort)) {
            return "bookId";
        }
        return this.sort;
    }

    private Direction toSortDirection() {
        if (StringUtil.isNullOrEmpty(this.sortDirection)) {
            return Direction.ASC;
        }
        return this.sortDirection.equals("desc") ? Direction.DESC : Direction.ASC;
    }
}
