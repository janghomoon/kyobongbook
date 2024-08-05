package kr.co.kyobongbook.book;

import static kr.co.kyobongbook.book.infra.constant.BookConstants.BOOK_URI;

import jakarta.validation.Valid;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.dto.put.response.UpdateBookResponse;
import kr.co.kyobongbook.book.service.facade.impl.BookFacadeImpl;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<FindBooksResponse> findBooks(@ModelAttribute @Valid FindBooksRequest request) throws KyobongException {
        return ResponseEntity.ok(bookFacade.findBooks(request));
    }

    @PutMapping( "/{bookId}")
    public ResponseEntity<UpdateBookResponse> updateBook( @PathVariable(value = "bookId") Long bookId, @RequestBody
    @Valid UpdateBookRequest request) throws KyobongException {
        return ResponseEntity.ok(bookFacade.updateBook(bookId, request));
    }

}
