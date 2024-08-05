package kr.co.kyobongbook.book.service.facade;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponse;

public interface BookFacade {

    FindBoosResponse findBooks(FindBooksRequest request);
}
