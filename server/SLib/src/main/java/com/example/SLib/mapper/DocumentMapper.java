package com.example.SLib.mapper;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper{
    public Document toDocument(DocumentDTO dto);
    public DocumentDTO toDocumentDTO(Document document);
    public List<DocumentDTO> toDocumentDTOList(List<Document> documents);

    @Mapping(target = "id", ignore = true)
    public void updateDocument(DocumentDTO documentDTO, @MappingTarget Document document);

}
