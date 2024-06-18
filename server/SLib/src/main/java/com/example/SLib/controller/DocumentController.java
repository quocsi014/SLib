package com.example.SLib.controller;

import com.example.SLib.dto.DocumentDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.service.iservice.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;


    @GetMapping("")
    public ResponseEntity<?> getDocuments(@RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of( page-1, limit);
        PaginationResponse<DocumentDTO> response = documentService.getDocuments(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getADocument(@PathVariable String id){
        DocumentDTO documentDTO = documentService.getADocument(id);
        return ResponseEntity.ok().body(documentDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> addDocument(@RequestBody DocumentDTO documentDTO){
        DocumentDTO resDocumentDTO = documentService.addDocument(documentDTO); 

         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resDocumentDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(resDocumentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocument(@PathVariable String id, @RequestBody DocumentDTO documentDTO){
        DocumentDTO resDocumentDTO = documentService.updateDocument(documentDTO, id);
        return ResponseEntity.ok().body(resDocumentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeDocument(@PathVariable String id){
        documentService.removeDocument(id);
        return ResponseEntity.noContent().build();
    }

}
