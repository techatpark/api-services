package com.example.demo.sql.controller;

import com.example.demo.sql.model.UploadResponse;
import com.example.demo.sql.service.FileService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Api(value = "Files", description = "Resource for files", tags = { "Files" })
@RestController
@RequestMapping("/api/files")
class FileAPIController {

    private final FileService fileService;

    FileAPIController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "Uploading a File", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "file uploaded successfully"),
            @ApiResponse(code = 400, message = "file is invalid") })
    @PostMapping
    public ResponseEntity<UploadResponse> upload(@RequestParam("uploadFile") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.upload(file));
    }

    @ApiOperation(value = "Download a file", notes = "download a file by given file id.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "file."),
            @ApiResponse(code = 400, message = "file id is invalid"),
            @ApiResponse(code = 404, message = "file not found") })
    @GetMapping("/{fileid}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileid, HttpServletRequest request) throws IOException {
        Path file = fileService.dowload(fileid);
        String contentType = "application/octet-stream";
        ByteArrayResource  resource = new ByteArrayResource(Files.readAllBytes(file));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+file.getFileName()+"")
                .body(resource);
    }
}