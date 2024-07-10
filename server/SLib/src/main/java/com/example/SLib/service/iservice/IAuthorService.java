package com.example.SLib.service.iservice;

import org.springframework.data.domain.Pageable;

import com.example.SLib.dto.AuthorDTO;
import com.example.SLib.dto.PaginationResponse;

public interface IAuthorService {
  public AuthorDTO addAuthor(AuthorDTO authorDTO);
  public PaginationResponse<?> getAuthors(Pageable pageable);
  public AuthorDTO getAuthor(String id);
  public AuthorDTO updateAuthor(AuthorDTO authorDTO, String id);
  public void deleteAuthor(String id);
}
