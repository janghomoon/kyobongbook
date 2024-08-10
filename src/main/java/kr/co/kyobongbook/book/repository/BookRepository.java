package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BooksRepository extends JpaRepository<Book, Long> {// , BooksRepositoryCustom
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
    Page<Book> findByCategoryContaining(String category, Pageable pageable);
    Page<Book> findBooksByParams(FindBooksRequest request);

    void updateBookCategory(Long bookId, UpdateBookRequest request);
}
