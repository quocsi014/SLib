package com.example.SLib.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.SLib.dto.DocumentTypeDTO;
import com.example.SLib.entity.DocumentType;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentTypeMapper {
  public DocumentType toDocumentType(DocumentTypeDTO documentTypeDTO);
  public DocumentTypeDTO toDocumentTypeDTO(DocumentType documentType);
  public List<DocumentTypeDTO> toDocumentTypeDTOList(List<DocumentType> documentTypes);
}
