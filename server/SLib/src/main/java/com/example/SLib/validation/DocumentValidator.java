package com.example.SLib.validation;

import java.time.LocalDate;

import com.example.SLib.exception.InvalidDataException;

public class DocumentValidator {
  public static final void ValidPublicationYear(Integer year){
    if(year != null && year > LocalDate.now().getYear()){
      throw new InvalidDataException("Publication year can not be greater than current year");
    }
  }
}
