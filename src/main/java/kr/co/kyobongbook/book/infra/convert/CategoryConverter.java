package kr.co.kyobongbook.book.infra.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;
import kr.co.kyobongbook.book.entity.Category;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;

@Converter
public class CategoryConverter implements AttributeConverter<Category, Long> {


    @Override
    public Long convertToDatabaseColumn(Category attribute) {
        return attribute != null ? attribute.getCategoryId() : null;
    }

    @Override
    public Category convertToEntityAttribute(Long dbData) {
        CategoryEnums categoryEnums = CategoryEnums.findByCode(dbData);
        if (Objects.isNull(categoryEnums)) return null;
        return categoryEnums.toCategory();
    }

}
