package com.example.SLib.service.iservice;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.SLib.dto.DocumentTypeDTO;
import com.example.SLib.dto.PaginationResponse;

@Service
public interface IDocumentTypeService {

  public DocumentTypeDTO addDocumentType(DocumentTypeDTO documentTypeDTO);
  public PaginationResponse<DocumentTypeDTO> getDocumentTypes(Pageable pageable);
  public DocumentTypeDTO updateDocumentTypeDTO(DocumentTypeDTO documentTypeDTO, String id);
  public void deleteDocumentTypeDTO(String id);

}
