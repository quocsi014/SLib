package com.example.SLib.service.iservice;

import com.example.SLib.dto.DocumentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDocument {
    public List<DocumentDTO> getDocuments(Pageable pageable);
}
