package kr.co.kyobongbook.book.entity.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Getter;

@Embeddable
@Getter
public class BookCategoryId implements Serializable {
    private Long bookId;
    private Long categoryId;

}
