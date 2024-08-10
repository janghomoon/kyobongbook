package kr.co.kyobongbook.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponseCategoryData;
import kr.co.kyobongbook.book.entity.id.BookCategoryId;
import kr.co.kyobongbook.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "book_category" , uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "category_id"}))
public class BookCategories extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookCategoryId;

    @EmbeddedId
    private BookCategoryId bookCategoryId;

    @MapsId("bookId")
    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Books book;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Categories category;

    public FindBooksResponseCategoryData toFindBoosResponseCategoryData() {
        return FindBooksResponseCategoryData.builder()
                .categoryId(this.category.getCategoryId())
                .categoryName(this.category.getCategoryName())
                .build();
    }



}
