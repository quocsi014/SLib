package com.example.SLib.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class DatabaseExceptionHandler {

  @ExceptionHandler(SaveDataException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleSaveDataException(SaveDataException e, WebRequest req){
    return new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFoundException(ResourceNotFoundException e, WebRequest req){
    return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.toString());
  }
  
}
