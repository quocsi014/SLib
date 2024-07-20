package com.example.SLib.dto;

import java.time.LocalDate;

import com.example.SLib.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderCreationDTO {
  private String id;

  @JsonProperty("library_number")
  private Long libraryNumber;

  private String name;

  private LocalDate birthday;

  private Gender gender;

  private String address;

  @JsonProperty("number_phone")
  private String numberPhone;

  @JsonProperty("expired_date")
  private LocalDate expiredDate;

  private String status;

  private AccountDTO account;

  private String email;

  private String password;
  
}
