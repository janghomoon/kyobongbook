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
import java.util.Objects;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponseData;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;
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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
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
                        .map(BookCategory::toFindBooksResponseCategoryData).toList())
                .build();
    }

    public void updateBookInfo(UpdateBookRequest request) {
        if (request.getCategoryId() != null) this.bookCategories.stream()
                .filter(c -> Objects.equals(
                c.getCategory().getCode(), request.getCategoryId()))
                .findAny()
                .ifPresent(c -> c.updateCategory(CategoryEnums.findByCode(request.getUpdateCategoryId())));
        if (request.getIsAvailable() != null) this.isAvailable = request.getIsAvailable();
        if (request.getNotAvailableReason() != null) this.notAvailableReason = request.getNotAvailableReason();
    }

}
