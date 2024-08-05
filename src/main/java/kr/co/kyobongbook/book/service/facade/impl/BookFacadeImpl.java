package kr.co.kyobongbook.book.service.facade.impl;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponse;
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
    public FindBoosResponse findBooks(FindBooksRequest request) throws KyobongException {
        return bookService.findBooks(request);
    }
}
