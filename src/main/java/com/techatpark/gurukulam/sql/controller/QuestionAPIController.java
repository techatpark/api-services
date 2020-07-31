package com.techatpark.gurukulam.sql.controller;

import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
class QuestionAPIController {
        private final QuestionService questionService;

        QuestionAPIController(final QuestionService questionService) {
                this.questionService = questionService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Question> findById(@PathVariable Integer id) {
                return ResponseEntity.of(questionService.read(id));
        }

        
        @PutMapping("/{id}")
        public ResponseEntity<Optional<Question>> update(@PathVariable Integer id,
                        @Valid @RequestBody Question question) {
                Optional<Question> updatedQuestion = questionService.update(id, question);
                return updatedQuestion == null ? new ResponseEntity<Optional<Question>>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedQuestion);
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
                return questionService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

}