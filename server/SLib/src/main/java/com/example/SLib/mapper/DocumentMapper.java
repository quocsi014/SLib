package com.example.SLib.mapper;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.entity.Document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentMapper {
    public Document toDocument(DocumentDTO dto);

    @Mapping(target = "authors", ignore = true)
    public DocumentDTO toDocumentDTO(Document document);

    public List<DocumentDTO> toDocumentDTOList(List<Document> documents);

    public Set<DocumentDTO> toDocumentDTOSet(Set<Document> documents);

    public Set<Document> toDocumentSet(Set<DocumentDTO> documentDTOs);

    @Mapping(target = "id", ignore = true)
    public void updateDocument(DocumentDTO documentDTO, @MappingTarget Document document);

}
