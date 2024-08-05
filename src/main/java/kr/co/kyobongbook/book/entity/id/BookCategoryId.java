package kr.co.kyobongbook.book.entity.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class BookCategoryId implements Serializable {
    private Long bookId;
    private Long categoryId;

    public BookCategoryId(Long bookId, Long categoryId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
    }
}
