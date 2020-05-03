package com.example.demo.sql.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.service.ExamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @ApiOperation(value = "Get exam with given id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "exam"),
            @ApiResponse(code = 404, message = "exam not found") })
    @GetMapping("/{id}")
    public ResponseEntity<Exam> findById(@PathVariable Integer id) {
        return ResponseEntity.of(examService.read(id));
    }

    @ApiOperation(value = "Updates the exam by given id", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "exam updated successfully"),
            @ApiResponse(code = 400, message = "exam is invalid"),
            @ApiResponse(code = 404, message = "exam not found") })
    @PutMapping("/{id}")
    public ResponseEntity<Exam> update(@PathVariable Integer id, @Valid @RequestBody Exam exam) {
        Exam updatedexam = examService.update(id, exam);
        return updatedexam == null ? new ResponseEntity<Exam>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(updatedexam);
    }

    @ApiOperation(value = "Deletes the exam by given id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "exam deleted successfully"),
            @ApiResponse(code = 404, message = "exam not found") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return examService.delete(id) ? ResponseEntity.ok().build() : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "lists all the exam", notes = "Can be Invoked by auth users only")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Listing all the exam"),
            @ApiResponse(code = 204, message = "exam are not available") })
    @GetMapping
    public ResponseEntity<List<Exam>> findAll() {
        List<Exam> exams = examService.lists(1, 1);
        return exams.isEmpty() ? new ResponseEntity<List<Exam>>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(exams);
    }
}