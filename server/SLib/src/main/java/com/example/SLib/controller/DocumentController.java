package com.example.SLib.controller;

import com.example.SLib.entity.Document;
import com.example.SLib.service.iservice.IDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    @Autowired
    private IDocument documentService;

    @GetMapping("/")
    public List<Document> getDocuments(@RequestParam(required = false) int limit, @RequestParam(required = false) int offset) {
        Pageable pageable = PageRequest.of( (int)Math.floor(offset/limit) + 1, limit);
        documentService.getDocuments(pageable);
    }
}
