package com.example.SLib.dto;

import java.util.Set;

import com.example.SLib.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {

  private String id;

  private String name;

  private String birthday;

  private String bio;

  private Gender gender;

  private Set<DocumentDTO> documents;


  public void setGender(String gender) {
    this.gender = Gender.fromString(gender);
  }
}
