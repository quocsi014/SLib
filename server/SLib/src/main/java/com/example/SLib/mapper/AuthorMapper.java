package com.example.SLib.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.SLib.dto.AuthorDTO;
import com.example.SLib.entity.Author;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

  public Author toAuthor(AuthorDTO authorDTO);

  @Mapping(target = "documents", ignore = true)
  public AuthorDTO toAuthorDTO(Author author);

  public List<AuthorDTO> toAuthorDTOList(List<Author> authors);

  public Set<Author> toAuthorSet(Set<AuthorDTO> authorDTOs);

  public Set<AuthorDTO> toAuthorDTOSet(Set<Author> authors);

  @Mapping(target = "id", ignore = true)
  public void updateAuthor(AuthorDTO authorDTO, @MappingTarget Author author);
}
