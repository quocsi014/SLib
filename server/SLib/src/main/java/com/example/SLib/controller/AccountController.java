package com.example.SLib.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SLib.dto.AccountDTO;
import com.example.SLib.dto.LoginResponse;
import com.example.SLib.service.iservice.IAccountService;

@RestController
@RequestMapping("api/v1/auth")
public class AccountController {
  
  @Autowired IAccountService accountService;

  @PostMapping("/login")
  public ResponseEntity<?> Login(@RequestBody AccountDTO accountDTO){
    accountService.Login(accountDTO);
    return ResponseEntity.ok().body(new LoginResponse("token"));
  }
}
