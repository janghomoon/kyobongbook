package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.entity.BookCategories;
import kr.co.kyobongbook.book.entity.id.BookCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoriesRepository extends JpaRepository<BookCategories, BookCategoryId> {

}
