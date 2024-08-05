package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.entity.Books;
import kr.co.kyobongbook.book.repository.custom.BooksRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BooksRepository extends JpaRepository<Books, Long>, BooksRepositoryCustom {

    Page<Books> findBooksByParams(FindBooksRequest request);

    void updateBookCategory(Long bookId, UpdateBookRequest request);
}
