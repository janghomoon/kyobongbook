package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
