package kr.co.kyobongbook.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponseCategoryData;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponseData;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Books extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", nullable = false, length = 1000)
    private String title;

    @Column(name = "author", nullable = false, length = 1000)
    private String author;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "not_available_reason", length = 1000)
    private String notAvailableReason;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookCategories> bookCategories;

    public void updateBookInfo(UpdateBookRequest request) {
        if (request.getIsAvailable()!= null && !this.isAvailable.equals(request.getIsAvailable())) this.isAvailable = request.getIsAvailable();
        if (StringUtils.hasText(request.getNotAvailableReason())) this.notAvailableReason = request.getNotAvailableReason();
    }

    public FindBooksResponseData toFindBoosResponseData() {
        return FindBooksResponseData.builder()
                .bookId(this.bookId)
                .title(this.title)
                .author(this.author)
                .isAvailable(this.isAvailable)
                .notAvailableReason(this.notAvailableReason)
                .bookCategories(toFindBoosResponseCategoryDataList())
                .build();
    }

    private List<FindBooksResponseCategoryData> toFindBoosResponseCategoryDataList() {
        if (CollectionUtils.isEmpty(this.bookCategories)) {
            return null;
        }
        return this.bookCategories.stream()
                .map(BookCategories::toFindBoosResponseCategoryData)
                .toList();
    }
}
