package com.example.SLib.controller;

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

import com.example.SLib.dto.AuthorDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.service.AuthorService;
import com.example.SLib.utils.OneBasedPageRequest;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
  
  @Autowired
  private AuthorService authorService;

  @PostMapping
  public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO){

    AuthorDTO saveAuthorDTO = authorService.addAuthor(authorDTO);
    
    return ResponseEntity.ok().body(saveAuthorDTO);
  }

  @GetMapping
  public ResponseEntity<?> getAuthors(@RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "1") int page){

    Pageable pageable = new OneBasedPageRequest(page, limit);

    PaginationResponse<AuthorDTO> paginationResponse = authorService.getAuthors(pageable);
    return ResponseEntity.ok().body(paginationResponse);

  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDTO> getAuthor(@PathVariable String id){
    AuthorDTO authorDTO = authorService.getAuthor(id);
    return ResponseEntity.ok().body(authorDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable String id){
    AuthorDTO updatedAuthorDTO = authorService.updateAuthor(authorDTO, id);    
    return ResponseEntity.ok().body(updatedAuthorDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAuthor(@PathVariable String id){
    authorService.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}
