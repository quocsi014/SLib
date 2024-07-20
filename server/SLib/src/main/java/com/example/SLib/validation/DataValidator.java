package com.example.SLib.validation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.SLib.exception.InvalidDataException;

public class DataValidator {
  
  private static final String NOT_NULL_QUOTE = "%s is required";
  private static final String NOT_BLANK_QUOTE = "%s can not be blank";
  private static final String POSITIVE_QUOTE = "%s must be greater than 0";
  private static final String INVALID_EMAIL_QUOTE = "Email is invalid";
  private static final String INVALID_FUTURE_DATE = "%s must be after %s"; 
  private static final String INVALID_PAST_DATE = "%s must be before %s";

  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

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

  public static final void ValidEmail(String email){
    Matcher matcher = EMAIL_PATTERN.matcher(email);
    if(email != null && !matcher.matches()){
      throw new InvalidDataException(INVALID_EMAIL_QUOTE);
    }
  }

  public static final void ValidFutureDate(LocalDate date, String fieldName){
    LocalDate currenDate = LocalDate.now();
    if (date != null && !date.isAfter(currenDate)){
      throw new InvalidDataException(String.format(INVALID_FUTURE_DATE, fieldName, currenDate.toString()));
    }
  }

  public static final void ValidPastDate(LocalDate date, String fieldName){
    LocalDate currentDate = LocalDate.now();
    if(date != null && !date.isBefore(currentDate)){
      throw new InvalidDataException(String.format(INVALID_PAST_DATE, fieldName, currentDate.toString()));
    }
  }

}
