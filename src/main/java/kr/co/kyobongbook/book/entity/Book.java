package kr.co.kyobongbook.book.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponseData;
import kr.co.kyobongbook.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "book")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @Comment("도서 아이디")
    private Long bookId;

    @Column(name = "title", nullable = false, length = 1000)
    @Comment("도서 제목")
    private String title;

    @Column(name = "author", nullable = false, length = 1000)
    @Comment("도서 저자")
    private String author;

    @Column(name = "is_available", nullable = false)
    @Comment("책 대여 가능 여부")
    private Boolean isAvailable;

    @Column(name = "not_available_reason", length = 1000)
    @Comment("책 대여 불가 사유")
    private String notAvailableReason;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    @Comment("도서 카테고리")
    private List<BookCategory> bookCategories;

    public FindBooksResponseData toFindBooksResponseData() {
        return FindBooksResponseData.builder()
                .bookId(this.bookId)
                .author(this.author)
                .title(this.title)
                .isAvailable(this.isAvailable)
                .notAvailableReason(this.notAvailableReason)
                .bookCategories(this.bookCategories.stream()
                        .map(BookCategory::getCategory).toList())
                .build();
    }

}
