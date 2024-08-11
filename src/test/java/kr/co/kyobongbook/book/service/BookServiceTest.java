package kr.co.kyobongbook.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.entity.Book;
import kr.co.kyobongbook.book.repository.BookRepository;
import kr.co.kyobongbook.book.repository.specification.BookSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

@Transactional
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("제목, 저자, 카테고리 전체 파라메터 조회 응답 값 정상 테스트")
    void findAllTest() {
        //given
        FindBooksRequest request = FindBooksRequest.builder()
                .page(0)
                .size(100)
                .title("너에게 해주지 못한 말들")
                 .author("권태영")
                 .categoryCodes(List.of(1L))
                .build();
//        //when
        BookSpecification bookSpecification = new BookSpecification(request.getTitle(), request.getAuthor(), request.toCategoryEnums());
        Page<Book> page = bookRepository.findAll(bookSpecification, request.toPageable());
//        //than
        assertEquals(page.get().findAny().get().getTitle(), "너에게 해주지 못한 말들");

        titleTest(FindBooksRequest.builder()
                .page(0)
                .size(100)
                .title("초격자 투자")
                .build());

        authorTest(FindBooksRequest.builder()
                .page(0)
                .size(100)
                .author("지승열")
                .build());

        categoryTest(FindBooksRequest.builder()
                .categoryCodes(List.of(1L))
                .build());
    }

    void titleTest(FindBooksRequest request) {
        //when
        BookSpecification bookSpecification = new BookSpecification(request.getTitle(), request.getAuthor(), request.toCategoryEnums());
        Page<Book> page = bookRepository.findAll(bookSpecification, request.toPageable());
        //then
        assertEquals(page.get().findAny().get().getAuthor(), "장동혁");
    }

    void authorTest(FindBooksRequest request) {
        //when
        BookSpecification bookSpecification = new BookSpecification(request.getTitle(), request.getAuthor(), request.toCategoryEnums());
        Page<Book> page = bookRepository.findAll(bookSpecification, request.toPageable());
        //then
        assertEquals(page.getTotalElements(), 2);
    }

    void categoryTest(FindBooksRequest request) {
        //when
        BookSpecification bookSpecification = new BookSpecification(request.getTitle(), request.getAuthor(), request.toCategoryEnums());
        Page<Book> page = bookRepository.findAll(bookSpecification, request.toPageable());
        //then
        assertEquals(page.getTotalElements(), 3);
    }

    @Test
    @DisplayName("카테고리 변경, 분실 여부 변경 테스트")
    void updateBookCateogryTest() {
//        //given
        UpdateBookRequest request= UpdateBookRequest.builder()
                .categoryId(1L)
                .updateCategoryId(5L)
                .isAvailable(false)
                .notAvailableReason("책 분실로 인한 대여 불가")
                .build();
        Long bookId = 1L;
//        //when
        Optional<Book> optional = bookRepository.findById(bookId);
        optional.get().updateBookInfo(request);
//        //then
        Book book = bookRepository.findById(bookId).get();
        assertEquals(book.getBookCategories().stream().findAny().get().getCategory().getCode(), request.getUpdateCategoryId());
        assertFalse(book.getIsAvailable());
        assertEquals(book.getNotAvailableReason(), request.getNotAvailableReason());

    }

}
