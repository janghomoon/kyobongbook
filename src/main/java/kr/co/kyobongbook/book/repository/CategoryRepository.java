package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

}
