package kr.co.kyobongbook.book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import kr.co.kyobongbook.book.dto.get.request.FindBooksRequest;
import kr.co.kyobongbook.book.dto.get.response.FindBooksResponse;
import kr.co.kyobongbook.book.dto.put.request.UpdateBookRequest;
import kr.co.kyobongbook.book.dto.put.response.UpdateBookResponse;
import kr.co.kyobongbook.book.service.facade.impl.BookFacadeImpl;
import kr.co.kyobongbook.common.util.DtoToQueryParamUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc // -> webAppContextSetup(webApplicationContext)
@AutoConfigureRestDocs // -> apply(documentationConfiguration(restDocumentation))
@SpringBootTest
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookFacadeImpl bookFacade;
    @Autowired
    private ObjectMapper objectMapper;


    static String getengthString(Integer length) {
        String returnVal = "";
        for (int idx=0; idx<length; idx++) {
            returnVal += idx + "";
        }
        return returnVal;
    }
    static List<Long> getMaxCodesList(Integer max) {
        List<Long> returnVals = new ArrayList<>();
        for (int idx=0; idx<max; idx++) {
            returnVals.add((long) idx);
        }
        return returnVals;
    }
    @ParameterizedTest
    @MethodSource("provideFindBooksRequestStreams")
    @DisplayName("도서 조회 파라메터  valid 테스트")
    void findBooksParametersValidTest(FindBooksRequest request) throws Exception {
        String url = String.format("%s%s", "/book?",
                DtoToQueryParamUtil.convertToQueryParams(request));

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();
    }

    private static Stream<FindBooksRequest> provideFindBooksRequestStreams() {
        return Stream.of(
                FindBooksRequest.builder()
                        .title("너에게 해주지 못한 말들" + getengthString(1000))
                        .author("권태영")
                        .categoryCodes(List.of(1L))
                        .page(0)
                        .size(100)
                        .build(),
                FindBooksRequest.builder()
                        .title("너에게 해주지 못한 말들")
                        .author("권태영" + getengthString(1000))
                        .categoryCodes(List.of(1L))
                        .page(0)
                        .size(100)
                        .build(),
                FindBooksRequest.builder()
                        .title("너에게 해주지 못한 말들")
                        .author("권태영")
                        .categoryCodes(getMaxCodesList(6))
                        .page(0)
                        .size(100)
                        .build()
        );
    }

    @Test
    @DisplayName("도서 정보 수정 파라메터 valid 테슽")
    void updateBookTest() throws Exception {
//        UpdateBookRequest request = UpdateBookRequest.builder()
//                .categoryId(1L)
//                .updateCategoryId(5L)
//                .isAvailable(false)
//                .notAvailableReason("분실" + getengthString(1000))
//                .build();
//        Mockito.when(bookFacade.updateBook(anyLong(),any()))
//                .thenReturn(UpdateBookResponse.builder()
//                        .isUpdate(true)
//                        .build());
//
//        String url = "/book/{bookId}";
//        mockMvc.perform(put(url, 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//                ).andDo(print())
//                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("도서 조회 API 문서 작성")
    void findBooksRestDocTest() throws Exception {
        FindBooksRequest findBooksRequest =  FindBooksRequest.builder()
                .title("너에게 해주지 못한 말들")
                .author("권태영")
                .categoryCodes(List.of(1L))
                .page(0)
                .size(100)
                .build();
        Mockito.when(bookFacade.findBooks(any()))
                .thenReturn(getFindBooksResponse());

        String url = String.format("%s%s", "/book?",
                DtoToQueryParamUtil.convertToQueryParams(findBooksRequest));

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk()).andDo(document("book-search"
                        , preprocessRequest(prettyPrint())
                        , preprocessResponse(prettyPrint())
                , queryParameters(
                                parameterWithName("categoryCodes").description("카테고리 코드 List")
                                , parameterWithName("title").description("도서 제목")
                                , parameterWithName("author").description("저자")
                                , parameterWithName("page").description("페이지 넘버")
                                , parameterWithName("size").description("노출 데이터 로우 수")
                        )
                        , responseFields(
                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("도서 정보")
                        , fieldWithPath("data[].bookId").type(JsonFieldType.NUMBER).description("도서 아이디")
                                , fieldWithPath("data[].title").type(JsonFieldType.STRING).description("도서 제목")
                                , fieldWithPath("data[].author").type(JsonFieldType.STRING).description("도서 저자")
                                , fieldWithPath("data[].isAvailable").type(JsonFieldType.BOOLEAN).description("도서 대여 가능 여부")
                                , fieldWithPath("data[].notAvailableReason").type(JsonFieldType.STRING).description("도서 대여 불가 사유").optional()
                                , fieldWithPath("data[].bookCategories").type(JsonFieldType.ARRAY).description("도서 카테고리 정보")
                                , fieldWithPath("data[].bookCategories[].categoryId").type(JsonFieldType.NUMBER).description("도서 카테고리 아이디")
                                , fieldWithPath("data[].bookCategories[].categoryName").type(JsonFieldType.STRING).description("도서 카테고리 명")
                        )
                ));
    }
    //파라메터 테스트
    FindBooksResponse getFindBooksResponse() {
        String json ="{\n"
                + "    \"data\": [\n"
                + "        {\n"
                + "            \"bookId\": 1,\n"
                + "            \"title\": \"너에게 해주지 못한 말들\",\n"
                + "            \"author\": \"권태영\",\n"
                + "            \"isAvailable\": true,\n"
                + "            \"notAvailableReason\": null,\n"
                + "            \"bookCategories\": [\n"
                + "                {\n"
                + "                    \"categoryId\": 1,\n"
                + "                    \"categoryName\": \"문학\"\n"
                + "                }\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"bookId\": 2,\n"
                + "            \"title\": \"단순하게 배부르게\",\n"
                + "            \"author\": \"현영서\",\n"
                + "            \"isAvailable\": true,\n"
                + "            \"notAvailableReason\": null,\n"
                + "            \"bookCategories\": [\n"
                + "                {\n"
                + "                    \"categoryId\": 1,\n"
                + "                    \"categoryName\": \"문학\"\n"
                + "                }\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"bookId\": 3,\n"
                + "            \"title\": \"게으른 사랑\",\n"
                + "            \"author\": \"권태영\",\n"
                + "            \"isAvailable\": true,\n"
                + "            \"notAvailableReason\": null,\n"
                + "            \"bookCategories\": [\n"
                + "                {\n"
                + "                    \"categoryId\": 1,\n"
                + "                    \"categoryName\": \"문학\"\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        try {
            return objectMapper.readValue(json, FindBooksResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Test
    @DisplayName("도서 업데이트 API 문서 작성")
    void updateBookRestDocTest() throws Exception {
//        UpdateBookRequest request = UpdateBookRequest.builder()
//                .categoryId(1L)
//                .updateCategoryId(5L)
//                .isAvailable(false)
//                .notAvailableReason("분실")
//                .build();
//        Mockito.when(bookFacade.updateBook(anyLong(),any()))
//                .thenReturn(UpdateBookResponse.builder()
//                        .isUpdate(true)
//                        .build());
//
//        String url = "/book/{bookId}";
//        mockMvc.perform(RestDocumentationRequestBuilders.put(url, 1L)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//        ).andDo(print())
//                .andExpect(status().isOk()).andDo(document("book-update"
//                        , preprocessRequest(prettyPrint())
//                        , preprocessResponse(prettyPrint())
//                        ,pathParameters(
//                                parameterWithName("bookId").description("도서 아이디")
//                        )
//                        , requestFields(
//                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER)
//                                        .description("변경 전 카테고리 아이디")
//                        , fieldWithPath("updateCategoryId").type(JsonFieldType.NUMBER)
//                                        .description("변경 할 카테고리 아이디")
//                        , fieldWithPath("isAvailable").type(JsonFieldType.BOOLEAN)
//                                        .description("책 대여 가능 여부")
//                                , fieldWithPath("notAvailableReason").type(JsonFieldType.STRING)
//                                        .description("대여 불가 사유")
//                        )
//                        , responseFields(
//                                fieldWithPath("isUpdate").type(JsonFieldType.BOOLEAN)
//                                        .description("업데이트 완료 여부")
//                        )
//                ));
    }

}