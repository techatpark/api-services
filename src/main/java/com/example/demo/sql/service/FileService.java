package com.example.demo.sql.service;

import com.example.demo.sql.model.UploadResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    /**
     * This will store the file.
     * 
     * @param file
     * @return stored file path
     */
    public UploadResponse upload(final MultipartFile file) {
        return null;
    }
}
