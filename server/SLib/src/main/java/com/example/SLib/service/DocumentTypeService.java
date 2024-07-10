package com.example.SLib.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SLib.dto.DocumentTypeDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.entity.DocumentType;
import com.example.SLib.exception.ConflictDataException;
import com.example.SLib.exception.ResourceNotFoundException;
import com.example.SLib.exception.SaveDataException;
import com.example.SLib.mapper.DocumentTypeMapper;
import com.example.SLib.repository.IDocumentTypeRepo;
import com.example.SLib.service.iservice.IDocumentTypeService;
import com.example.SLib.validator.DataValidator;

@Service
public class DocumentTypeService implements IDocumentTypeService {

  @Autowired
  private IDocumentTypeRepo documentTypeRepo;

  @Autowired
  private DocumentTypeMapper documentTypeMapper;

  @Override
  public DocumentTypeDTO addDocumentType(DocumentTypeDTO documentTypeDTO) {

    documentTypeDTO.setId(UUID.randomUUID().toString());

    //validation
    DataValidator.NotNull(documentTypeDTO.getName(), "Name");
    DataValidator.NotBlank(documentTypeDTO.getName(), "Name");

    DocumentType documentType = documentTypeMapper.toDocumentType(documentTypeDTO);
    
    try {
      DocumentType savedDocumentType = documentTypeRepo.save(documentType);
      return documentTypeMapper.toDocumentTypeDTO(savedDocumentType);
    } catch (DataIntegrityViolationException e) {
      throw new ConflictDataException("Name is exist");
    }
  }

  @Override
  public PaginationResponse<DocumentTypeDTO> getDocumentTypes(Pageable pageable) {
    Page<DocumentType> documentTypes = documentTypeRepo.findAll(pageable); 

    PaginationResponse<DocumentTypeDTO> paginationResponse = PaginationResponse.create(documentTypes);
    paginationResponse.setItems(documentTypeMapper.toDocumentTypeDTOList(documentTypes.getContent()));
    return paginationResponse;
  }

  @Override
  public DocumentTypeDTO updateDocumentTypeDTO(DocumentTypeDTO documentTypeDTO, String id) {

    DocumentType documentType = documentTypeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(DocumentType.OBJ_NAME));

    DataValidator.NotNull(documentTypeDTO.getName(), "Name");
    DataValidator.NotBlank(documentTypeDTO.getName(), "Name");

    documentType.setName(documentTypeDTO.getName());

    try{
      DocumentType updatedDocumentType = documentTypeRepo.save(documentType);
      return documentTypeMapper.toDocumentTypeDTO(updatedDocumentType);
    }catch (DataIntegrityViolationException e) {
      throw new ConflictDataException("Name is exist");
    }
    catch(Exception e){
      throw new SaveDataException(DocumentType.OBJ_NAME);
    }

  }

  @Override
  public void deleteDocumentTypeDTO(String id) {
    if(!documentTypeRepo.existsById(id)){
      throw new ResourceNotFoundException(DocumentType.OBJ_NAME);
    }
    documentTypeRepo.deleteById(id);   
  }
  
}
