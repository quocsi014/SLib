package com.example.SLib.entity;

import com.example.SLib.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  public static String OBJ_NAME = "Account";

  @Id
  private String id;

  @Column
  private String email;

  @Column
  private String password;

  @Enumerated(EnumType.STRING)
  @Column
  private Role role;

}

