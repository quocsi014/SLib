package com.example.SLib.dto;

import com.example.SLib.exception.InvalidDataException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO{

    private String id;

    @JsonProperty("type_id")
    private String typeId;

    private String isbn;

    private String title;

    @JsonProperty("image_url")
    private String imageUrl;

    private String publisher;

    @JsonProperty("publication_year")
    private int publicationYear;

    private int pages;

    private String language;

    private String description;

    private int amount;

    private String format;

    public void checkNullTitle(){
        if(this.getTitle() == null){
            throw new InvalidDataException("Title is required");
        }
    }

    

}
