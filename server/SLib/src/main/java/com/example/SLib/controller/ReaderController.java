package com.example.SLib.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.SLib.dto.ReaderCreationDTO;
import com.example.SLib.dto.ReaderDTO;
import com.example.SLib.service.iservice.IReaderService;

@RestController
@RequestMapping("api/v1/readers")
public class ReaderController {
  
  @Autowired
  private IReaderService readerService;

  @PostMapping("")
  public ResponseEntity<?> CreateReader(@RequestBody ReaderCreationDTO readerCreationDTO){
    ReaderDTO savedReader = readerService.CreateReader(readerCreationDTO);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedReader.getId())
                .toUri();

    return ResponseEntity.created(location).body(savedReader);
  }

}
