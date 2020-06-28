package com.techatpark.gurukulam.sql.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.techatpark.gurukulam.sql.controller.payload.CreateExamRequest;
import com.techatpark.gurukulam.sql.model.Exam;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.AnswerService;
import com.techatpark.gurukulam.sql.service.QuestionService;
import com.techatpark.gurukulam.sql.service.SQLExamService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "exams", description = "Resource to manage exams")
@RestController
@RequestMapping("/api/exams/sql")
class SQLExamAPIController {

        private final SQLExamService SQLExamService;
        private final QuestionService questionService;
        private final AnswerService answerService;

        SQLExamAPIController(final SQLExamService SQLExamService, final QuestionService questionService,
                        final AnswerService answerService) {
                this.SQLExamService = SQLExamService;
                this.questionService = questionService;
                this.answerService = answerService;
        }

        @Operation(summary = "Creates a new exam", description = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "exam created successfully"),
                        @ApiResponse(responseCode = "400", description = "exam is invalid") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping
        public ResponseEntity<Optional<Exam>> create(HttpServletRequest request,
                        @ModelAttribute CreateExamRequest createExamRequest) throws IOException {
                // return
                // ResponseEntity.status(HttpStatus.CREATED).body(examService.create(createExamRequest.getExam(),
                // null));
                return null;
        }

        @Operation(summary = "Get exam with given id")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "exam"),
                        @ApiResponse(responseCode = "404", description = "exam not found") })
        @GetMapping("/{id}")
        public ResponseEntity<Exam> findById(@PathVariable Integer id) {
                return ResponseEntity.of(SQLExamService.read(id));
        }

        @Operation(summary = "lists all the exam", description = "Can be Invoked by auth users only")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listing all the exam"),
                        @ApiResponse(responseCode = "204", description = "exam are not available") })
        @GetMapping
        public ResponseEntity<Page<Exam>> findAll() {
                Page<Exam> exams = SQLExamService.list(1, 1);
                return exams.isEmpty() ? new ResponseEntity<Page<Exam>>(HttpStatus.NO_CONTENT)
                                : ResponseEntity.ok(exams);
        }

        @Operation(summary = "Updates the exam by given id", description = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "exam updated successfully"),
                        @ApiResponse(responseCode = "400", description = "exam is invalid"),
                        @ApiResponse(responseCode = "404", description = "exam not found") })
        @PutMapping("/{id}")
        public ResponseEntity<Optional<Exam>> update(@PathVariable Integer id, @Valid @RequestBody Exam exam) {
                Optional<Exam> updatedexam = SQLExamService.update(id, exam);
                return updatedexam == null ? new ResponseEntity<Optional<Exam>>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedexam);
        }

        @Operation(summary = "Deletes the exam by given id")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "exam deleted successfully"),
                        @ApiResponse(responseCode = "404", description = "exam not found") })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteExamById(@PathVariable Integer id) {
                return SQLExamService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        @Operation(summary = "Creates a new question", description = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "question created successfully"),
                        @ApiResponse(responseCode = "400", description = "question is invalid") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping("/{examId}/questions")
        public ResponseEntity<Optional<Question>> create(@PathVariable Integer examId,
                        @Valid @RequestBody Question question) {
                return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(examId, question));
        }

        @Operation(summary = "Get question with given id")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "question"),
                        @ApiResponse(responseCode = "404", description = "question not found") })
        @GetMapping("/{examId}/questions/{id}")
        public ResponseEntity<Question> findQuestionById(@PathVariable Integer id) {
                return ResponseEntity.of(questionService.read(id));
        }

        @Operation(summary = "lists all the questions", description = " Can be invoked by auth users only")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listing all the questions"),
                        @ApiResponse(responseCode = "204", description = "questions are not available") })
        @GetMapping("/{examId}/questions")
        public ResponseEntity<List<Question>> findAllQuestions(@PathVariable Integer examId) {
                List<Question> questions = questionService.list(examId);
                return questions.isEmpty() ? new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT)
                                : ResponseEntity.ok(questions);
        }

        @Operation(summary = "Updates the question by given id", description = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "question updated successfully"),
                        @ApiResponse(responseCode = "400", description = "question is invalid"),
                        @ApiResponse(responseCode = "404", description = "question not found") })
        @PutMapping("/{examId}/questions/{id}")
        public ResponseEntity<Optional<Question>> update(@PathVariable Integer id,
                        @Valid @RequestBody Question question) {
                Optional<Question> updatedQuestion = questionService.update(id, question);
                return updatedQuestion == null ? new ResponseEntity<Optional<Question>>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedQuestion);
        }

        @Operation(summary = "Deletes the question by given id")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "question deleted successfully"),
                        @ApiResponse(responseCode = "404", description = "question not found") })
        @DeleteMapping("/{examId}/questions/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
                return questionService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        @Operation(summary = "Answer a question", description = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Answered a question successfully"),
                        @ApiResponse(responseCode = "406", description = "Answer is invalid") })
        @ResponseStatus(HttpStatus.ACCEPTED)
        @PostMapping("/{examId}/questions/{questionId}/answer")
        public ResponseEntity<Void> answer(@PathVariable Integer questionId, @RequestBody String answer) {
                return answerService.answer(questionId, answer) ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

}