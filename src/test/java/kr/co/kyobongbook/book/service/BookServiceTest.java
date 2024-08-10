package kr.co.kyobongbook.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@SpringBootTest
//@Transactional
//@Disabled
class BookServiceTest {
//    @Autowired
//    private BooksRepository booksRepository;

    @Test
    @DisplayName("조회 정상응답")

    void findBooksSuccessTest() {
//        //given
//        FindBooksRequest request = FindBooksRequest.builder()
//                .page(0)
//                .size(100)
//                .title("너에게 해주지 못한 말들")
//                .build();
//        //when
//        Page<Book> page = booksRepository.findBooksByParams(request);
//        List<Book> books = page.get().toList();
//        //than
//        //1권의 책 조회
//        assertEquals(books.size(), 1);
//        //같은 제목 1개
//        assertEquals(books.stream().findAny().get().getTitle(), "너에게 해주지 못한 말들");


    }

    @Test
    @DisplayName("도서 정보 카테고리 업데이트 테스트")
    void updateBookCateogryTest() {
//        //given
//        UpdateBookRequest request= UpdateBookRequest.builder()
//                .categoryId(1L)
//                .updateCategoryId(5L)
//                .isAvailable(false)
//                .notAvailableReason("책 분실로 인한 대여 불가")
//                .build();
//        Long bookId = 1L;
//        //when
//        booksRepository.updateBookCategory(bookId, request);
//        //then
//        Book book = booksRepository.findById(bookId).get();
//        assertEquals(book.getBookCategories().stream().findAny().get().getBookCategoryId().getCategoryId(), request.getUpdateCategoryId());

    }
    @Test
    @DisplayName("도서 정보 업데이트  대여불가 및 대여불가 사유")
    void updateBookTest() {
//        //ginven
//        UpdateBookRequest request= UpdateBookRequest.builder()
//                .categoryId(1L)
//                .updateCategoryId(5L)
//                .isAvailable(false)
//                .notAvailableReason("책 분실로 인한 대여 불가")
//                .build();
//        Long bookId = 1L;
//
//        //when
//        Optional<Book> optional = booksRepository.findById(bookId);
//        if (optional.isEmpty()) throw new KyobongException("updateBook error 책정보 없음");
//        Book book = optional.get();
//        book.updateBookInfo(request);
//
//        //then
//        //도서 대여 상태 확인
//        Optional<Book> optionalResult = booksRepository.findById(bookId);
//        Book bookResult = optionalResult.get();
//        //도서 대여 상태 확인
//        assertFalse(bookResult.getIsAvailable());
//        //도서 대여 불가 사유 확인
//        assertEquals(bookResult.getNotAvailableReason(), request.getNotAvailableReason());
    }
}
