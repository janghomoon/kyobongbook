package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.entity.Book;
import kr.co.kyobongbook.book.infra.enums.CategoryEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByBookCategories_Category(CategoryEnums category, Pageable pageable);
    Page<Book> findByTitleContaining(String title, Pageable pageable);
    Page<Book> findByAuthorContaining(String author, Pageable pageable);
}
