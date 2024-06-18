package com.example.SLib.validation;

import com.example.SLib.exception.InvalidDataException;

public class DocumentValidation {

  public static void CheckNullTitle(String title) {
    if (title == null) {
      throw new InvalidDataException("Title is required");
    }
  }

  public static void CheckEmptyTitle(String title) {
    if (title != null && title.isEmpty()) {
      throw new InvalidDataException("Title can not be blank");
    }
  }

  public static void CheckNegativePageNumber(int pageNumber){
    if(pageNumber < 0){
      throw new InvalidDataException("Page number must be positive");
    }
  }

}
