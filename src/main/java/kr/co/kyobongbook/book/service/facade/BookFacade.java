package kr.co.kyobongbook.book.service.facade;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;

public interface BookFacade {

    FindBooksResponse findBooks(FindBooksRequest request);
}
