package com.example.SLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SLib.entity.DocumentType;
@Repository
public interface IDocumentTypeRepo extends JpaRepository<DocumentType, String> {
  
}
