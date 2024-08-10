package kr.co.kyobongbook.book.infra.enums;

import java.util.Arrays;
import kr.co.kyobongbook.book.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnums {
    LITERATURE(1L, "문학"),
    ECONOMIC_MANAGEMENT(2L, "경제경영"),
    HUMANITIES(3L, "인문학"),
    INFORMATION_TECHNOLOGY(4L, "IT"),
    SCIENCE(5L, "과학"),
    ;
    private final Long code;
    private final String name;


    public static CategoryEnums findByCode(Long code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public Category toCategory() {
        return Category.builder()
                .categoryId(code)
                .categoryName(name)
                .build();
    }
}
