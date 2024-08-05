package kr.co.kyobongbook.book.infra.enums;

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
}
