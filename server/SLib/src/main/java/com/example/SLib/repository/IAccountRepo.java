package com.example.SLib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SLib.entity.Account;

@Repository
public interface IAccountRepo extends JpaRepository<Account, String> {
  
  public Optional<Account> findByEmail(String email);

}
