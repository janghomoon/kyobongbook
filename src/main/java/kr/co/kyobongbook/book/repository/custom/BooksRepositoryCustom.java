package kr.co.kyobongbook.book.repository.custom;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.entity.Books;
import org.springframework.data.domain.Page;

public interface BooksRepositoryCustom {

    Page<Books> findBooksByParams(FindBooksRequest request);
    void updateBookCategory(Long bookId, UpdateBookRequest request);
}
