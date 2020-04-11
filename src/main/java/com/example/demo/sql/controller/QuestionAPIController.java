package com.example.demo.sql.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.QuestionService;

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

@Api(value = "Questions", description = "REST API for Questions", tags = { "Questions" })
@RestController
@RequestMapping("/api/questions")
class QuestionAPIController {
        private final QuestionService questionService;

        QuestionAPIController(final QuestionService questionService) {
                this.questionService = questionService;
        }

        @ApiOperation(value = "Creates a new question", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "question created successfully"),
                        @ApiResponse(code = 400, message = "question is invalid") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping
        public ResponseEntity<Question> create(@Valid @RequestBody Question question) {
                return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(question));
        }

        @ApiOperation(value = "Get question with given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question"),
                        @ApiResponse(code = 404, message = "question not found") })
        @GetMapping("/{id}")
        public ResponseEntity<Question> findById(@PathVariable Integer id) {
                return ResponseEntity.ok(questionService.read(id));
        }

        @ApiOperation(value = "Updates the question by given id", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question updated successfully"),
                        @ApiResponse(code = 400, message = "question is invalid"),
                        @ApiResponse(code = 404, message = "question not found") })
        @PutMapping("/{id}")
        public ResponseEntity<Question> update(@PathVariable Integer id, @Valid @RequestBody Question question) {
                Question updatedQuestion = questionService.update(id, question);
                return updatedQuestion == null ? new ResponseEntity<Question>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedQuestion);
        }

        @ApiOperation(value = "Deletes the question by given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question deleted successfully"),
                        @ApiResponse(code = 404, message = "question not found") })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
                return questionService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        @ApiOperation(value = "lists all the question", notes = "Can be Invoked by auth users only")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Listing all the question"),
                        @ApiResponse(code = 204, message = "question are not available") })
        @GetMapping
        public ResponseEntity<List<Question>> findAll() {
                List<Question> questions = questionService.lists(1, 1);
                return questions.isEmpty() ? new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT)
                                : ResponseEntity.ok(questions);
        }

}