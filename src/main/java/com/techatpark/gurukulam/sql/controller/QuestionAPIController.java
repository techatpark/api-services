package com.techatpark.gurukulam.sql.controller;

import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
@Tag(name = "Questions", description = "Resource to manage Questions")
class QuestionAPIController {
    private final QuestionService questionService;

    QuestionAPIController(final QuestionService questionService) {
        this.questionService = questionService;
    }
    @Operation(summary = "Answer the questions",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<Question> findById(@PathVariable Integer id) {
        return ResponseEntity.of(questionService.read(id));
    }

    @Operation(summary = "Updating the questions",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Question>> update(@PathVariable Integer id,
                                                     @Valid @RequestBody Question question) {
        Optional<Question> updatedQuestion = questionService.update(id, question);
        return updatedQuestion == null ? new ResponseEntity<Optional<Question>>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedQuestion);
    }
    @Operation(summary = "Delete the questions",
            security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return questionService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

}