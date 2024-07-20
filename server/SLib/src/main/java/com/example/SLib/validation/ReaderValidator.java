package com.example.SLib.validation;

import java.time.LocalDate;
import java.time.Period;

import com.example.SLib.exception.InvalidDataException;

public class ReaderValidator {

  private static final int MIN_AGE = 7; 
  private static final String INVALID_BIRTHDAY_QUOTE = "Reader must is %s";

  public static final void ValidBirthday(LocalDate date){
    LocalDate currentDate = LocalDate.now();
    int age = Period.between(date, currentDate).getYears();
    if(date != null && age < MIN_AGE){
      throw new InvalidDataException(String.format(INVALID_BIRTHDAY_QUOTE, MIN_AGE));
    }
  }
}
