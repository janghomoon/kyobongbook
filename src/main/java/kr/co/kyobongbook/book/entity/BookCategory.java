package kr.co.kyobongbook.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
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
import kr.co.kyobongbook.book.infra.convert.CategoryConverter;
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
@Table(name = "book_category" , uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "category_id"}))
public class BookCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_category_id")
    private Long bookCategoryId;


    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @Convert(converter = CategoryConverter.class)
    @Column(name = "category_id")
    @Comment("카테고리 아이디")
    private CategoryEnums category;


}
