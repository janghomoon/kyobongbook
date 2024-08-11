package kr.co.kyobongbook.book.infra.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;

@Converter
public class CategoryConverter implements AttributeConverter<CategoryEnums, Long> {


    @Override
    public Long convertToDatabaseColumn(CategoryEnums attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public CategoryEnums convertToEntityAttribute(Long dbData) {
        CategoryEnums categoryEnums = CategoryEnums.findByCode(dbData);
        if (Objects.isNull(categoryEnums)) return null;
        return categoryEnums;
    }

}
