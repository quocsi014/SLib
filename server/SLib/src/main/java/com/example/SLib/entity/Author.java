package com.example.SLib.entity;

import com.example.SLib.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Author {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column
    private String birthday;

    @Column
    private String bio;

    @Column(nullable = false)
    private Gender gender;
}
