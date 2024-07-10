package com.example.SLib.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalDefaultExceptionHandler {
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest req) {
    // Xử lý các loại ngoại lệ không được khai báo một cách cụ thể
    String errorMessage = "Something went wrong from server\n" + e.getMessage();
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorResponse errorResponse = new ErrorResponse(errorMessage, status.toString());
    return new ResponseEntity<>(errorResponse, status);
  }
}
