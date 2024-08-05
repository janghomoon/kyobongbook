package kr.co.kyobongbook.common.infra.exception.handler;

import java.security.InvalidParameterException;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Unexpected error occurred message {}", ex.getMessage(), ex);
        // 모든 예외에 대한 처리 로직
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error");
    }

    @ExceptionHandler(KyobongException.class)
    public ResponseEntity<String> handleCustomException(KyobongException ex) {
        // 특정 예외에 대한 처리 로직
        log.error("Custom exception occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> handleInvalidParameterException(InvalidParameterException ex) {
        log.error("handleInvalidParameterException error occurred message {}", ex.getMessage(), ex);
        String errorMessage = "Invalid parameter: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

//    @ExceptionHandler(DuplicateKeyException.class)
//@ExceptionHandler(DuplicateKeyException.class)
//@ResponseBody
//public ResponseEntity<Object> handleDuplicateKeyException(HttpServletRequest request,
//        final DuplicateKeyException e) {
//    printExceptionLog(request, e);
//
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(new ErrorAPIResponse(ErrCode.DUPLICATE_KEY));
//}
//
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public ResponseEntity<Object> handleAccessDeniedException(HttpServletRequest request,
//            final AccessDeniedException e) {
//        printExceptionLog(request, e);
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(new ErrorAPIResponse(SecurityErrorCode.UNAUTHORIZED));
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseBody
//    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
//            HttpServletRequest request, final HttpRequestMethodNotSupportedException e) {
//        printExceptionLog(request, e);
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrCode.NOT_FOUND.getDescription());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(HttpServletRequest request,
//            MethodArgumentNotValidException e) {
//        printExceptionLog(request, e);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorAPIResponse(e));
//    }
//
//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<Object> handleBindingExceptions(HttpServletRequest request,
//            BindException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorAPIResponse(e));
//    }
}
