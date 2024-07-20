package com.example.SLib.entity;

import java.time.LocalDate;

import com.example.SLib.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "readers", uniqueConstraints = @UniqueConstraint(columnNames = { "library_number" }))
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader {

  @Id
  @Column(length = 50)
  private String id;

  @Column(name = "library_number")
  private Long libraryNumber;

  @Column
  private String name;

  @Column
  private LocalDate birthday;

  @Column
  private Gender gender;

  @Column
  private String address;

  @Column(name = "number_phone")
  private String numberPhone;

  @Column(name = "registration_date")
  private LocalDate registraionDate;

  @Column(name = "expired_date")
  private LocalDate expiredDate;

  @Column
  private String status;

  @OneToOne(mappedBy = "reader", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Account account;

}
