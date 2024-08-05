package kr.co.kyobongbook.book.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponse;
import kr.co.kyobongbook.book.dto.get.response.FindBoosResponseData;
import kr.co.kyobongbook.book.entity.BookCategories;
import kr.co.kyobongbook.book.entity.Books;
import kr.co.kyobongbook.book.entity.QBooks;
import kr.co.kyobongbook.book.entity.QCategories;
import kr.co.kyobongbook.book.repository.BooksRepository;
import kr.co.kyobongbook.book.repository.custom.impl.BooksRepositoryCustomImpl;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.CollectionsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BooksRepository booksRepository;
    private final BooksRepositoryCustomImpl booksRepositoryCustom;
//    private final JPAQueryFactory jpaQueryFactory;

    //todo 작업 영역
    @Transactional(readOnly = true)
    public FindBoosResponse findBooks(FindBooksRequest request) throws KyobongException {
        Page<Books> books = booksRepository.findBooksByParams(request);
        if (Objects.isNull(books)) return FindBoosResponse.builder().build();

        return FindBoosResponse.builder()
                .data(
                        books.get().map(Books::toFindBoosResponseData).toList()
                )
                .build();
    }


}
