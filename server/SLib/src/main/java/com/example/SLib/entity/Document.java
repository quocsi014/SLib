package com.example.SLib.entity;

import com.example.SLib.exception.InvalidDataException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "documents")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    public static final String OBJ_NAME = "Document";
    
    @Id
    private String id;

    @Column(name="type_id")
    private String typeId;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column(name = "iamge_url")
    private String imageUrl;

    @Column
    private String publisher;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column
    private int pages;

    @Column
    private String language;

    @Column
    private String description;

    @Column
    private int amount;

    @Column
    private String format;

   

}
