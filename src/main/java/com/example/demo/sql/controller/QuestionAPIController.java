package com.example.demo.sql.controller;

import javax.validation.Valid;

import com.example.demo.tracker.model.Namespace;

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

@Api(value = "Questions", description = "REST API for Questions", tags = { "Questions" })
@RestController
@RequestMapping("/api/questions")
class QuestionAPIController {

    @ApiOperation(value = "Creates a new question", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "question created successfully"),
            @ApiResponse(code = 400, message = "question is invalid") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Namespace> create(@Valid @RequestBody Namespace namespace) {
        return null;
    }
}