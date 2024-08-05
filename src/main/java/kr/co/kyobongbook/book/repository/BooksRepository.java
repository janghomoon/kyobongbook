package kr.co.kyobongbook.book.repository;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.entity.Books;
import kr.co.kyobongbook.book.repository.custom.BooksRepositoryCustom;
import kr.co.kyobongbook.book.repository.custom.impl.BooksRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
//@Repository
//@RequiredArgsConstructor
public interface BooksRepository extends JpaRepository<Books, Long>, BooksRepositoryCustom {
    Page<Books> findBooksByParams(FindBooksRequest request);
//    Page<Books> findAll(FindBooksRequest request){
//
//    }

}
