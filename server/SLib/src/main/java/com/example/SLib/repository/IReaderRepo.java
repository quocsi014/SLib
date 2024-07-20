package com.example.SLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SLib.entity.Reader;

@Repository
public interface IReaderRepo extends JpaRepository<Reader, String> {

}
