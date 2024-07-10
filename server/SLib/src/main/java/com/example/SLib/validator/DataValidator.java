package com.example.SLib.validator;

import com.example.SLib.exception.InvalidDataException;

public class DataValidator {
  
  public static final String NOT_NULL_QUOTE = "%s is required";
  public static final String NOT_BLANK_QUOTE = "%s can not be blank";
  public static final String POSITIVE_QUOTE = "%s must be greater than 0";

  public static final <T> void NotNull(T value, String fieldName){
    if(value == null){
      throw new InvalidDataException(String.format(NOT_NULL_QUOTE, fieldName));
    }
  }

  public static final void NotBlank(String value, String fieldName){
    if(value != null && value.isBlank()){
      throw new InvalidDataException(String.format(NOT_BLANK_QUOTE, fieldName));
    }
  }

  public static final <T extends Number> void Positive(T value, String fieldName){
    if(value != null && value.doubleValue() <= 0){
      throw new InvalidDataException(String.format(POSITIVE_QUOTE, fieldName));
    }
  }

}
