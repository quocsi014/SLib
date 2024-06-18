package com.example.SLib.service.iservice;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.dto.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface IDocumentService {
    public PaginationResponse<DocumentDTO> getDocuments(Pageable pageable);
    public DocumentDTO getADocument(String id);
    public DocumentDTO addDocument(DocumentDTO documentDTO);
    public DocumentDTO updateDocument(DocumentDTO documentDTO, String id);
    public void removeDocument(String id);
    
}
