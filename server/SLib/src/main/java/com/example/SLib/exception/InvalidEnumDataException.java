package com.example.SLib.exception;

import java.util.Arrays;

public class InvalidEnumDataException extends RuntimeException {
  public <T> InvalidEnumDataException(String fieldName, T[] values){
    super(fieldName + " must be in " + Arrays.toString(values));
  }
}
