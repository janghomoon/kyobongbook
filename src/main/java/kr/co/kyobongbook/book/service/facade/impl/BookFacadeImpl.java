package kr.co.kyobongbook.book.service.facade.impl;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.dto.put.response.UpdateBookResponse;
import kr.co.kyobongbook.book.service.BookService;
import kr.co.kyobongbook.book.service.facade.BookFacade;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookFacadeImpl implements BookFacade {
    private final BookService bookService;

    @Override
    public FindBooksResponse findBooks(FindBooksRequest request) throws KyobongException {
        return bookService.findBooks(request);
    }

    public UpdateBookResponse updateBook(Long bookId, UpdateBookRequest request) throws KyobongException {
        return bookService.updateBook(bookId, request);
    }
}
