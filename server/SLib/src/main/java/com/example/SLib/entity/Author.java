package com.example.SLib.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.SLib.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    public static final String OBJ_NAME = "Author";

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String birthday;

    @Column
    private String bio;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "authors")
    private Set<Document> documents = new HashSet<>();

}
