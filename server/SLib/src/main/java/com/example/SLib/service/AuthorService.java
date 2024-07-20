package com.example.SLib.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SLib.dto.AuthorDTO;
import com.example.SLib.dto.PaginationResponse;
import com.example.SLib.entity.Author;
import com.example.SLib.exception.ResourceNotFoundException;
import com.example.SLib.exception.SaveDataException;
import com.example.SLib.mapper.AuthorMapper;
import com.example.SLib.repository.IAuthorRepo;
import com.example.SLib.service.iservice.IAuthorService;
import com.example.SLib.validation.DataValidator;

@Service
public class AuthorService implements IAuthorService {

  @Autowired
  private IAuthorRepo authorRepo;

  @Autowired
  private AuthorMapper authorMapper;

  @Override
  public AuthorDTO addAuthor(AuthorDTO authorDTO) {
    authorDTO.setId(UUID.randomUUID().toString());

    DataValidator.NotNull(authorDTO.getName(), Author.OBJ_NAME);
    DataValidator.NotBlank(authorDTO.getName(), Author.OBJ_NAME);

    Author author = authorMapper.toAuthor(authorDTO);
    try {
      Author saveAuthor = authorRepo.save(author);
      return authorMapper.toAuthorDTO(saveAuthor);
    } catch (Exception e) {
      throw new SaveDataException(Author.OBJ_NAME);
    }
  }

  @Override
  public PaginationResponse<AuthorDTO> getAuthors(Pageable pageable) {

    Page<Author> authors = authorRepo.findAll(pageable);
    PaginationResponse<AuthorDTO> paginationResponse = PaginationResponse.create(authors);
    paginationResponse.setItems(authorMapper.toAuthorDTOList(authors.getContent()));

    return paginationResponse;
  }

  @Override
  public AuthorDTO updateAuthor(AuthorDTO authorDTO, String id) {

    DataValidator.NotBlank(authorDTO.getName(), "Name");
    Author author = authorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Author.OBJ_NAME));
    authorMapper.updateAuthor(authorDTO, author);

    try {
      authorRepo.save(author);
    } catch (Exception e) {
      throw new SaveDataException(Author.OBJ_NAME);
    }
    
    return authorMapper.toAuthorDTO(author);
  }

  @Override
  public void deleteAuthor(String id){
    if(!authorRepo.existsById(id)){
      throw new ResourceNotFoundException(Author.OBJ_NAME);
    }
    authorRepo.deleteById(id);

  }

  @Override
  public AuthorDTO getAuthor(String id) {
    Author author = authorRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(Author.OBJ_NAME));

    AuthorDTO authorDTO = authorMapper.toAuthorDTO(author);
    return authorDTO;
  }

}
