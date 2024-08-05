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
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponseCategoryData;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponseData;
import kr.co.kyobongbook.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

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
    //    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private Timestamp createdAt;

//    @Column(name = "updated_a  
//            t", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//            private Timestamp updatedAt;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bookId;

//    @Comment("책 제목")
//    @Column(nullable = false, length = 1000)
//    private String title;
//
//    @Comment("책 저자")
//    @Column(nullable = false, length = 1000)
//    private String author;
//
//    @Comment("책 대여 가능 여부")
//    @Column(nullable = false)
//    private Boolean isAvailable;
//
//    @Comment("책 대출 불가 사유")
//    @Column(nullable = false, length = 1000)
//    private String notAvailableReason;
//
//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//    private List<BookCategories> bookCategories;


    public FindBoosResponseData toFindBoosResponseData() {
        return FindBoosResponseData.builder()
                .bookId(this.bookId)
                .title(this.title)
                .isAvailable(this.isAvailable)
                .notAvailableReason(this.notAvailableReason)
                .bookCategories(toFindBoosResponseCategoryDataList())
                .build();
    }

    private List<FindBoosResponseCategoryData> toFindBoosResponseCategoryDataList() {
        if (CollectionUtils.isEmpty(this.bookCategories)) {
            return null;
        }
        return this.bookCategories.stream()
                .map(BookCategories::toFindBoosResponseCategoryData)
                .toList();
    }
}
