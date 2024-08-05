package kr.co.kyobongbook.book;

import static kr.co.kyobongbook.book.infra.constant.BookConstants.BOOK_URI;

import jakarta.validation.Valid;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponse;
import kr.co.kyobongbook.book.service.facade.impl.BookFacadeImpl;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = BOOK_URI, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookFacadeImpl bookFacade;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.initDirectFieldAccess();
    }

    @GetMapping
    public ResponseEntity<FindBoosResponse> findBooks(@ModelAttribute @Valid FindBooksRequest request) throws KyobongException {
        return ResponseEntity.ok(bookFacade.findBooks(request));
    }

    //
    //도서 검색 //페이징 은 이야기 없었지만
//    도서는 훼손 또는 분실 등의 이유로 대여가 중단 될 수 있다.
    //도서 수정
//    - 도서는 카테고리가 변경될 수 있다.
//- 카테고리 별로 도서를 검색 할 수 있다.
//- 지은이와 제목으로 도서를 검색 할 수 있다.
//- 현재 서점에 있는 도서 목록은 다음과 같다.



}
