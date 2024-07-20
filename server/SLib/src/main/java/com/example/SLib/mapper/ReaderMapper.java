package com.example.SLib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.SLib.dto.ReaderCreationDTO;
import com.example.SLib.dto.ReaderDTO;
import com.example.SLib.entity.Reader;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReaderMapper {
  public Reader toReader(ReaderDTO readerDTO);
  
  @Mapping(target = "account.password", ignore = true)
  public ReaderDTO toReaderDTO(Reader reader);
  public Reader toReader(ReaderCreationDTO readerCreationDTO);
}
