package com.example.SLib.controller;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SLib.dto.AccountDTO;
import com.example.SLib.dto.LoginResponse;
import com.example.SLib.service.iservice.IAccountService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

@RestController
@RequestMapping("api/v1/auth")
public class AccountController {
  
  @Autowired IAccountService accountService;

  private static final String SIGNED_KEY = "Qj/cKXCVVJ2DMJmTPyK4efOuhQbRSv6238flxeKu27jrO82uT19t4OnIDtDSd41L"; 

  @PostMapping("/login")
  public ResponseEntity<?> Login(@RequestBody AccountDTO accountDTO){
    AccountDTO takedAccount = accountService.Login(accountDTO);
    String token = generateToken(takedAccount.getId(), takedAccount.getRole().toString());
    return ResponseEntity.ok().body(new LoginResponse(token));
  }

  private String generateToken(String user, String role){
    JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
    JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
    .issueTime(new Date())
    .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
    .claim("role", role)
    .subject(user)
    .build();
    JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwtClaimsSet.toJSONObject()));

    try {
      jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));
      return jwsObject.serialize();
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
  }
}
