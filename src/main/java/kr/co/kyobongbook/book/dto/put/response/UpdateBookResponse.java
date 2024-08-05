package kr.co.kyobongbook.book.dto.put.response;

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
public class UpdateBookResponse {

    private Boolean isUpdate;
}
