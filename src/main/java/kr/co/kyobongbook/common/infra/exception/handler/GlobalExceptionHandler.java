package kr.co.kyobongbook.common.infra.exception.handler;

import java.security.InvalidParameterException;
import kr.co.kyobongbook.common.infra.exception.KyobongException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


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
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleExceptionForInvalidParam(Exception ex) {
        log.error("handleExceptionForInvalidParam error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException ex) {
        log.error("handleDuplicateKeyException error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("handleDuplicateKeyException error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        log.error("handleHttpRequestMethodNotSupportedException error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("handleValidationExceptions error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindingExceptions(BindException ex) {
        log.error("handleValidationExceptions error occurred message {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        log.error("Unexpected error occurred message {}", ex.getMessage(), ex);
//        // 모든 예외에 대한 처리 로직
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Internal Server Error");
//    }
}
