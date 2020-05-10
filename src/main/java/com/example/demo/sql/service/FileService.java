package com.example.demo.sql.service;

import com.example.demo.sql.model.UploadResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    /**
     *
     * @param fileid
     * @return
     * @throws IOException
     */
    public Path dowload(String fileid) throws IOException {
        String folderlocation = System.getProperty("java.io.tmpdir");
        Path createdTempFolder = null;
        Path tempFile = null;
        try {
            createdTempFolder = Files.createTempDirectory(Paths.get(folderlocation),"temp");
            tempFile =  Files.createTempFile(createdTempFolder,"temp",".sql");
            Files.write(tempFile,"Sample Data".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

}
