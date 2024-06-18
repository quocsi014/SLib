package com.example.SLib.service;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.entity.Document;
import com.example.SLib.exception.ResourceNotFoundException;
import com.example.SLib.exception.SaveDataException;
import com.example.SLib.mapper.DocumentMapper;
import com.example.SLib.repository.IDocumentRepo;
import com.example.SLib.service.iservice.IDocumentService;
import com.example.SLib.validation.DocumentValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentRepo documentRepo;

    @Autowired
    private DocumentMapper documentMapper;

    public PaginationResponse<DocumentDTO> getDocuments(Pageable pageable){
        Page<Document> docs = documentRepo.findAll(pageable);

        PaginationResponse<DocumentDTO> paginationResponse = PaginationResponse.create(docs);
        List<DocumentDTO> documentDTOList = documentMapper.toDocumentDTOList(docs.getContent());
        paginationResponse.setItems(documentDTOList);
        return paginationResponse;
    }

    public DocumentDTO getADocument(String id){
        Document document = documentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(Document.OBJ_NAME));
        return documentMapper.toDocumentDTO(document);
    }

    public DocumentDTO addDocument(DocumentDTO documentDTO){

        //generate id automatically 
        documentDTO.setId(UUID.randomUUID().toString());

        //validate data
        DocumentValidation.CheckNullTitle(documentDTO.getTitle());
        DocumentValidation.CheckEmptyTitle(documentDTO.getTitle());
        DocumentValidation.CheckNegativePageNumber(documentDTO.getPages());

        Document document = documentMapper.toDocument(documentDTO);

        try {
            documentRepo.save(document);
        } catch (Exception e) {
            throw new SaveDataException(Document.OBJ_NAME);
        }

        return documentDTO;
    }

    public DocumentDTO updateDocument(DocumentDTO documentDTO, String id){

        //find document to update
        Document document = documentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(Document.OBJ_NAME));
        
        DocumentValidation.CheckEmptyTitle(documentDTO.getTitle());
        DocumentValidation.CheckNegativePageNumber(documentDTO.getPages());

        documentMapper.updateDocument(documentDTO, document);
        
        try {
            documentRepo.save(document);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SaveDataException(e.getMessage());
        }

        DocumentDTO resDocumentDTO = documentMapper.toDocumentDTO(document);
        return resDocumentDTO;
    }

    public void removeDocument(String id){
        if(!documentRepo.existsById(id)){
            throw new ResourceNotFoundException(Document.OBJ_NAME);
        }
        documentRepo.deleteById(id);
    }

}
