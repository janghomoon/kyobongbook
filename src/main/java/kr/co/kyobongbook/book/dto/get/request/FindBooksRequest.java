package kr.co.kyobongbook.book.dto.get.request;

import ch.qos.logback.core.util.StringUtil;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.kyobongbook.book.entity.Book;
import kr.co.kyobongbook.book.entity.BookCategory;
import kr.co.kyobongbook.book.entity.Category;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.flywaydb.core.internal.util.CollectionsUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FindBooksRequest {

    //최대 5개
    @Size(max = 5, message = "조회 카테고리는 최대 5개를 넘어갈 수 잆습니다.")
    private List<Long> categoryCodes;
    @Length(max = 1000, message = "제목은 1000자를 초과할 수 없습니다.")
    private String title;
    @Length(max = 1000, message = "저자는 1000자를 초과할 수 없습니다.")
    private String author;

    private Integer page;

    private Integer size;

    public Pageable toPageable() {
        if (page == null) {
            return Pageable.ofSize(100);
        }
        return PageRequest.of(this.page, this.size);
    }

    public List<CategoryEnums> toCategoryEnums() {
        if (CollectionUtils.isEmpty(this.categoryCodes)) return Collections.emptyList();

        return this.categoryCodes.stream().map(CategoryEnums::findByCode).toList();
    }



}
