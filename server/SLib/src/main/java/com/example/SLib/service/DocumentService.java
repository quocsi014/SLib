package com.example.SLib.service;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.entity.Author;
import com.example.SLib.entity.Document;
import com.example.SLib.exception.ResourceNotFoundException;
import com.example.SLib.exception.SaveDataException;
import com.example.SLib.mapper.AuthorMapper;
import com.example.SLib.mapper.DocumentMapper;
import com.example.SLib.repository.IAuthorRepo;
import com.example.SLib.repository.IDocumentRepo;
import com.example.SLib.service.iservice.IDocumentService;
import com.example.SLib.validation.DataValidator;
import com.example.SLib.validation.DocumentValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private IDocumentRepo documentRepo;

    @Autowired
    private IAuthorRepo authorRepo;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public PaginationResponse<DocumentDTO> getDocuments(Pageable pageable){
        Page<Document> docs = documentRepo.findAll(pageable);
        PaginationResponse<DocumentDTO> paginationResponse = PaginationResponse.create(docs);
        List<DocumentDTO> documentDTOList = documentMapper.toDocumentDTOList(docs.getContent());
        paginationResponse.setItems(documentDTOList);
        return paginationResponse;
    }

    @Override
    public DocumentDTO getADocument(String id){
        Document document = documentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(Document.OBJ_NAME));

        DocumentDTO documentDTO = documentMapper.toDocumentDTO(document);
        documentDTO.setAuthors(authorMapper.toAuthorDTOSet(document.getAuthors()));


        return documentDTO;

    }

    @Override
    public DocumentDTO addDocument(DocumentDTO documentDTO){

        //generate id automatically 
        documentDTO.setId(UUID.randomUUID().toString());

        //validation
        DataValidator.NotNull(documentDTO.getTitle(), "Title");
        DataValidator.NotBlank(documentDTO.getTitle(), "Title");
        DataValidator.Positive(documentDTO.getPages(), "Page number");

        DataValidator.Positive(documentDTO.getPublicationYear(), "Year");
        DocumentValidator.ValidPublicationYear(documentDTO.getPublicationYear());

        Document document = documentMapper.toDocument(documentDTO);

        Set<Author> authors = new HashSet<>(authorRepo.findAllById(documentDTO.getAuthorIds()));
        
        document.setAuthors(authors);

        try {
            documentRepo.save(document);
        } catch (Exception e) {
            throw new SaveDataException(Document.OBJ_NAME);
        }

        return documentDTO;
    }

    @Override
    public DocumentDTO updateDocument(DocumentDTO documentDTO, String id){

        //find document to update
        Document document = documentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(Document.OBJ_NAME));

        //validation
        DataValidator.NotBlank(documentDTO.getTitle(), "Title");
        DataValidator.Positive(documentDTO.getPages(), "Page number");
        
        DataValidator.Positive(documentDTO.getPublicationYear(), "Year");
        DocumentValidator.ValidPublicationYear(documentDTO.getPublicationYear());

        documentMapper.updateDocument(documentDTO, document);

        Set<Author> authors = new HashSet<>(authorRepo.findAllById(documentDTO.getAuthorIds()));
        
        document.setAuthors(authors);

        try {
            documentRepo.save(document);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SaveDataException(e.getMessage());
        }

        DocumentDTO resDocumentDTO = documentMapper.toDocumentDTO(document);
        return resDocumentDTO;
    }

    @Override
    public void removeDocument(String id){
        if(!documentRepo.existsById(id)){
            throw new ResourceNotFoundException(Document.OBJ_NAME);
        }
        documentRepo.deleteById(id);
    }

}
