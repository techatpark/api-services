package com.example.demo.sql.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.demo.sql.model.UploadResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    /**
     * logger to log the trace or error.
     */
    private Logger logger = LoggerFactory.getLogger(FileService.class);

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
     * @return tempFile
     * @throws IOException
     */
    public Path dowload(final String fileid) throws IOException {
        final String folderlocation = System.getProperty("java.io.tmpdir");
        Path createdTempFolder = null;
        Path tempFile = null;
        try {
            createdTempFolder = Files.createTempDirectory(Paths.get(folderlocation), "temp");
            tempFile = Files.createTempFile(createdTempFolder, "temp", ".sql");
            Files.write(tempFile, "Sample Data".getBytes());
        } catch (final IOException e) {
            logger.error("Error in creating the file : " + e.getMessage());
        }
        return tempFile;
    }

}
