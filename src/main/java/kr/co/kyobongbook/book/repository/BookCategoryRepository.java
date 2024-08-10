package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.entity.BookCategory;
import kr.co.kyobongbook.book.entity.id.BookCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, BookCategoryId> {
//    Page<Booz>
}
