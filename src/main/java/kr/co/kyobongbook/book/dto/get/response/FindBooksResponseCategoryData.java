package kr.co.kyobongbook.book.dto.get.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindBooksResponseCategoryData {

    private Long categoryId;

    private String categoryName;

}
