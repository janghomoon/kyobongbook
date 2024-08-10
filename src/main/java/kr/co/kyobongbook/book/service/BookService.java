package kr.co.kyobongbook.book.service;

import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.dto.put.response.UpdateBookResponse;
import kr.co.kyobongbook.book.repository.BookRepository;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;


    @Transactional(readOnly = true)
    public FindBooksResponse findBooks(FindBooksRequest request) throws KyobongException {
//        Page<Book> books = booksRepository.findBooksByParams(request);
//        if (Objects.isNull(books)) return FindBooksResponse.builder().build();
//
//        return FindBooksResponse.builder()
//                .data(
//                        books.get().map(Book::toFindBoosResponseData).toList()
//                )
//                .build();
        return null;
    }

    @Transactional
    public UpdateBookResponse updateBook(Long bookId, UpdateBookRequest request) throws KyobongException {
//        Optional<Book> optional = booksRepository.findById(bookId);
//        if (optional.isEmpty()) throw new KyobongException("updateBook error 책정보 없음");
//        Book book = optional.get();
//        book.updateBookInfo(request);
//        BookCategory bookCategory = book.getBookCategories().stream().filter(c -> Objects.equals(
//                c.getBookCategoryId().getCategoryId(), request.getCategoryId())).findAny().orElse(null);
//
//        if (Objects.nonNull(bookCategory)) {
//            booksRepository.updateBookCategory(bookId, request);
//        }
        return UpdateBookResponse.builder().isUpdate(true).build();
    }

}
