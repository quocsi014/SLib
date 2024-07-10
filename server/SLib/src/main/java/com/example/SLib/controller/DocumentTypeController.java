package com.example.SLib.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.SLib.dto.DocumentTypeDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.service.DocumentTypeService;
import com.example.SLib.utils.OneBasedPageRequest;

@RestController()
@RequestMapping("/api/v1/document_types")
public class DocumentTypeController {

  @Autowired
  private DocumentTypeService documentTypeService;

  @PostMapping
  public ResponseEntity<DocumentTypeDTO> addDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
    DocumentTypeDTO savedDocumentTypeDTO = documentTypeService.addDocumentType(documentTypeDTO);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedDocumentTypeDTO.getId())
        .toUri();

    return ResponseEntity.created(location).body(savedDocumentTypeDTO);
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<DocumentTypeDTO>> getDocumentTypes(@RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "1") int page ){
    Pageable pageable = new OneBasedPageRequest(page, limit);

    PaginationResponse<DocumentTypeDTO> paginationResponse = documentTypeService.getDocumentTypes(pageable);

    return ResponseEntity.ok().body(paginationResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DocumentTypeDTO> updateDocumentType(@PathVariable String id, @RequestBody DocumentTypeDTO documentTypeDTO){

    DocumentTypeDTO updatedDocumentTypeDTO = documentTypeService.updateDocumentTypeDTO(documentTypeDTO, id);
    
    return ResponseEntity.ok().body(updatedDocumentTypeDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteDocumentType(@PathVariable String id){
    documentTypeService.deleteDocumentTypeDTO(id);
    return ResponseEntity.noContent().build();
  } 


}
