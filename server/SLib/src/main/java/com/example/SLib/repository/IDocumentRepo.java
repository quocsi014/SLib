package com.example.SLib.repository;

import com.example.SLib.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentRepo extends JpaRepository<Document, String> {

}
