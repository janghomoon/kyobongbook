package kr.co.kyobongbook.book.service.facade;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;

public interface BookFacade {

    FindBooksResponse findBooks(FindBooksRequest request);
    void updateBook(Long bookId, UpdateBookRequest request);
}
