package kr.co.kyobongbook.book.dto.put.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateBookRequest {

    private Long categoryId;
    private Long updateCategoryId;


    private Boolean isAvailable;
    @Length(max = 1000, message = "대여 불가 사유는 1000자를 초과할 수 없습니다.")
    private String notAvailableReason;
}
