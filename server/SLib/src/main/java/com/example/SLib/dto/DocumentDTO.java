package com.example.SLib.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentDTO {

    private String id;

    @JsonProperty("type_id")
    private String typeId;

    @JsonProperty("document_type")
    private DocumentTypeDTO documentType;

    private String isbn;

    private String title;

    @JsonProperty("image_url")
    private String imageUrl;

    private String publisher;

    @JsonProperty("publication_year")
    private Integer publicationYear;

    private Integer pages;

    private String language;

    private String description;

    private Integer amount;

    private String format;

    @JsonProperty("authors")
    private Set<AuthorDTO> authors;

    @JsonProperty("author_ids")
    private Set<String> authorIds;

}
