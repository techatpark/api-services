package com.example.demo.sql.controller;

import com.example.demo.sql.model.UploadResponse;
import com.example.demo.sql.service.FileService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

}