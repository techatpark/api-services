package com.example.demo.sql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Answers", description = "REST API for Answers", tags = { "Answers" })
@RestController
@RequestMapping("/api/answers")
public class AnswerAPIController {
    @ApiOperation(value = "Answer a question", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Answered a question successfully"),
            @ApiResponse(code = 400, message = "Answer is invalid") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{questionId}")
    public ResponseEntity<Void> answer(@PathVariable Integer questionId, @RequestBody String answer) {
        return null;
    }
}