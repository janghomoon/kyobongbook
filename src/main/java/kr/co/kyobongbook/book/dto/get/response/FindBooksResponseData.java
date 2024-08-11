package kr.co.kyobongbook.book.dto.get.response;

import java.util.List;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;
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
public class FindBooksResponseData {

    private Long bookId;
    private String title;
    private String author;
    private Boolean isAvailable;
    private String notAvailableReason;
    private List<CategoryEnums> bookCategories;
}
