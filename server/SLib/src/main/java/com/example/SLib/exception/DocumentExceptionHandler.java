package com.example.SLib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class DocumentExceptionHandler {
  
  @ExceptionHandler(InvalidDataException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleInvalidDataException(InvalidDataException e, WebRequest req){
    return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
  }

}
