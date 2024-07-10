package com.example.SLib.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @Column(length = 50)
    private String id;

    @Column(name = "type_id", length = 50)
    private String typeId;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private DocumentType documentType;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column(name = "iamge_url")
    private String imageUrl;

    @Column
    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column
    private Integer pages;

    @Column
    private String language;

    @Column
    private String description;

    @Column
    private Integer amount;

    @Column
    private String format;

    @ManyToMany
    @JoinTable(name = "document_authors", joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

}
