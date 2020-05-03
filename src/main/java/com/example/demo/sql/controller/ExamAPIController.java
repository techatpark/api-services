package com.example.demo.sql.controller;

import javax.validation.Valid;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.service.ExamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Exams", description = "Resource for Exams", tags = { "Exams" })
@RestController
@RequestMapping("/api/exams")
class ExamAPIController {

    private final ExamService examService;

    ExamAPIController(final ExamService examService) {
        this.examService = examService;
    }

    @ApiOperation(value = "Creates a new exam", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "exam created successfully"),
            @ApiResponse(code = 400, message = "exam is invalid") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Exam> create(@Valid @RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.create(exam));
    }

}