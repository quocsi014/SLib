package com.example.SLib.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

@Entity()
@Table(name = "document_types")

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {

  public static final String OBJ_NAME = "Document Type";

  @Id
  @Column(length = 50)
  private String id;

  @Column
  private String name;

}
