package com.example.SLib.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidDataException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidDataException(InvalidDataException e, WebRequest req) {
    return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
  }

  @ExceptionHandler(SaveDataException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleSaveDataException(SaveDataException e, WebRequest req) {
    return new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFoundException(ResourceNotFoundException e, WebRequest req) {
    return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.toString());
  }

  @ExceptionHandler(ConflictDataException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse hanldeConflictDataException(ConflictDataException e, WebRequest req) {
    return new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.toString());
  }

  @ExceptionHandler(InvalidEnumDataException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidEnumDataException(InvalidEnumDataException e, WebRequest req) {
    return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
  }

  

}
