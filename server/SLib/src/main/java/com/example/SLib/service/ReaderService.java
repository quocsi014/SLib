package com.example.SLib.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SLib.dto.ReaderCreationDTO;
import com.example.SLib.dto.ReaderDTO;
import com.example.SLib.entity.Account;
import com.example.SLib.entity.Reader;
import com.example.SLib.mapper.AccountMapper;
import com.example.SLib.mapper.ReaderMapper;
import com.example.SLib.repository.IReaderRepo;
import com.example.SLib.service.iservice.IReaderService;
import com.example.SLib.validation.DataValidator;
import com.example.SLib.validation.ReaderValidator;

@Service
public class ReaderService implements IReaderService {

  @Autowired
  private IReaderRepo readerRepo;

  @Autowired
  private ReaderMapper readerMapper;

  @Autowired
  private AccountMapper accountMapper;

  @Override
  public ReaderDTO CreateReader(ReaderCreationDTO readerCreationDTO) {
    readerCreationDTO.setId(UUID.randomUUID().toString());

    DataValidator.NotNull(readerCreationDTO.getName(), "Name");
    DataValidator.NotBlank(readerCreationDTO.getName(), "Name");

    DataValidator.NotNull(readerCreationDTO.getLibraryNumber(), "Library number");

    DataValidator.NotNull(readerCreationDTO.getBirthday(), "Birthday");
    DataValidator.ValidPastDate(readerCreationDTO.getBirthday(), "Birthday");
    ReaderValidator.ValidBirthday(readerCreationDTO.getBirthday());

    DataValidator.NotNull(readerCreationDTO.getExpiredDate(), "Expired date");
    DataValidator.ValidFutureDate(readerCreationDTO.getExpiredDate(), "Expired date");

    DataValidator.NotNull(readerCreationDTO.getAddress(), "Address");
    DataValidator.NotBlank(readerCreationDTO.getAddress(), "Address");

    DataValidator.NotNull(readerCreationDTO.getEmail(), "Email");
    DataValidator.ValidEmail(readerCreationDTO.getEmail());

    DataValidator.NotNull(readerCreationDTO.getPassword(), "Password");
    DataValidator.NotBlank(readerCreationDTO.getPassword(), "Password");
    
    

    Reader reader = readerMapper.toReader(readerCreationDTO);
    Account account = accountMapper.toAccount(readerCreationDTO);

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    String hashedPassword = passwordEncoder.encode(account.getPassword());
    account.setPassword(hashedPassword);

    account.setReader(reader);
    reader.setAccount(account);
    
    Reader savedReader = readerRepo.save(reader);
    
    return readerMapper.toReaderDTO(savedReader);
  }

  @Override
  public ReaderDTO GetReader(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'GetReader'");
  }

  @Override
  public List<ReaderDTO> ListReader() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ListReader'");
  }

  @Override
  public void UpdateReader(String id, ReaderDTO readerDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'UpdateReader'");
  }

  @Override
  public void DeleteReader(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'DeleteReader'");
  }

}
