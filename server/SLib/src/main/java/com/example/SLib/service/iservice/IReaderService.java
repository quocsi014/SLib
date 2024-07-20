package com.example.SLib.service.iservice;


import com.example.SLib.dto.ReaderCreationDTO;
import com.example.SLib.dto.ReaderDTO;
import java.util.List;

public interface IReaderService {
  public ReaderDTO CreateReader(ReaderCreationDTO readerCreationDTO);
  public ReaderDTO GetReader(String id);
  public List<ReaderDTO> ListReader();
  public void UpdateReader(String id, ReaderDTO readerDTO);
  public void DeleteReader(String id);
}
