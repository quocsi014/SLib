package com.example.SLib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SLib.dto.AccountDTO;
import com.example.SLib.entity.Account;
import com.example.SLib.exception.UnauthorizedException;
import com.example.SLib.mapper.AccountMapper;
import com.example.SLib.repository.IAccountRepo;
import com.example.SLib.service.iservice.IAccountService;

@Service
public class AccountService implements IAccountService{

  @Autowired
  private IAccountRepo accountRepo;

  @Autowired
  private AccountMapper accountMapper;

  public AccountDTO Login(AccountDTO accountDTO){

    Account account = accountRepo.findByEmail(accountDTO.getEmail()).orElseThrow(()->new UnauthorizedException("Email or password is incorrect"));
    
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    if(!passwordEncoder.matches(accountDTO.getPassword(), account.getPassword())){
      throw new UnauthorizedException("Email or password is incorrect");
    }

    return accountMapper.toAccountDTO(account);
  }
}
