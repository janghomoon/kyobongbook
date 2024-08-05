package kr.co.kyobongbook.book.service;

import java.util.Objects;
import java.util.Optional;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.dto.put.response.UpdateBookResponse;
import kr.co.kyobongbook.book.entity.BookCategories;
import kr.co.kyobongbook.book.entity.Books;
import kr.co.kyobongbook.book.repository.BooksRepository;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BooksRepository booksRepository;


    @Transactional(readOnly = true)
    public FindBooksResponse findBooks(FindBooksRequest request) throws KyobongException {
        Page<Books> books = booksRepository.findBooksByParams(request);
        if (Objects.isNull(books)) return FindBooksResponse.builder().build();

        return FindBooksResponse.builder()
                .data(
                        books.get().map(Books::toFindBoosResponseData).toList()
                )
                .build();
    }

    @Transactional
    public UpdateBookResponse updateBook(Long bookId, UpdateBookRequest request) throws KyobongException {
        Optional<Books> optional = booksRepository.findById(bookId);
        if (optional.isEmpty()) throw new KyobongException("updateBook error 책정보 없음");
        Books books = optional.get();
        books.updateBookInfo(request);
        BookCategories bookCategories = books.getBookCategories().stream().filter(c -> Objects.equals(
                c.getBookCategoryId().getCategoryId(), request.getCategoryId())).findAny().orElse(null);

        if (Objects.nonNull(bookCategories)) {
            booksRepository.updateBookCategory(bookId, request);
        }
        return UpdateBookResponse.builder().isUpdate(true).build();
    }

}
