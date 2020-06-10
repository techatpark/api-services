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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Api(value = "SQL Exams", description = "Resource for SQL Exams", tags = { "SQL Exams" })
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

        @ApiOperation(value = "Creates a new exam", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "exam created successfully"),
                        @ApiResponse(code = 400, message = "exam is invalid") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping
        public ResponseEntity<Optional<Exam>> create(HttpServletRequest request,
                        @ModelAttribute CreateExamRequest createExamRequest) throws IOException {
                // return
                // ResponseEntity.status(HttpStatus.CREATED).body(examService.create(createExamRequest.getExam(),
                // null));
                return null;
        }

        @ApiOperation(value = "Get exam with given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "exam"),
                        @ApiResponse(code = 404, message = "exam not found") })
        @GetMapping("/{id}")
        public ResponseEntity<Exam> findById(@PathVariable Integer id) {
                return ResponseEntity.of(SQLExamService.read(id));
        }

        @ApiOperation(value = "lists all the exam", notes = "Can be Invoked by auth users only")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Listing all the exam"),
                        @ApiResponse(code = 204, message = "exam are not available") })
        @GetMapping
        public ResponseEntity<List<Exam>> findAll() {
                List<Exam> exams = SQLExamService.list(1, 1);
                return exams.isEmpty() ? new ResponseEntity<List<Exam>>(HttpStatus.NO_CONTENT)
                                : ResponseEntity.ok(exams);
        }

        @ApiOperation(value = "Updates the exam by given id", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "exam updated successfully"),
                        @ApiResponse(code = 400, message = "exam is invalid"),
                        @ApiResponse(code = 404, message = "exam not found") })
        @PutMapping("/{id}")
        public ResponseEntity<Optional<Exam>> update(@PathVariable Integer id, @Valid @RequestBody Exam exam) {
                Optional<Exam> updatedexam = SQLExamService.update(id, exam);
                return updatedexam == null ? new ResponseEntity<Optional<Exam>>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedexam);
        }

        @ApiOperation(value = "Deletes the exam by given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "exam deleted successfully"),
                        @ApiResponse(code = 404, message = "exam not found") })
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteExamById(@PathVariable Integer id) {
                return SQLExamService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        @ApiOperation(value = "Creates a new question", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "question created successfully"),
                        @ApiResponse(code = 400, message = "question is invalid") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping("/{examId}/questions")
        public ResponseEntity<Optional<Question>> create(@PathVariable Integer examId,
                        @Valid @RequestBody Question question) {
                return ResponseEntity.status(HttpStatus.CREATED).body(questionService.create(examId, question));
        }

        @ApiOperation(value = "Get question with given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question"),
                        @ApiResponse(code = 404, message = "question not found") })
        @GetMapping("/{examId}/questions/{id}")
        public ResponseEntity<Question> findQuestionById(@PathVariable Integer id) {
                return ResponseEntity.of(questionService.read(id));
        }

        @ApiOperation(value = "lists all the questions", notes = " Can be invoked by auth users only")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Listing all the questions"),
                        @ApiResponse(code = 204, message = "questions are not available") })
        @GetMapping("/{examId}/questions")
        public ResponseEntity<List<Question>> findAllQuestions(@PathVariable Integer examId) {
                List<Question> questions = questionService.list(examId);
                return questions.isEmpty() ? new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT)
                                : ResponseEntity.ok(questions);
        }

        @ApiOperation(value = "Updates the question by given id", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question updated successfully"),
                        @ApiResponse(code = 400, message = "question is invalid"),
                        @ApiResponse(code = 404, message = "question not found") })
        @PutMapping("/{examId}/questions/{id}")
        public ResponseEntity<Optional<Question>> update(@PathVariable Integer id,
                        @Valid @RequestBody Question question) {
                Optional<Question> updatedQuestion = questionService.update(id, question);
                return updatedQuestion == null ? new ResponseEntity<Optional<Question>>(HttpStatus.NOT_FOUND)
                                : ResponseEntity.ok(updatedQuestion);
        }

        @ApiOperation(value = "Deletes the question by given id")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "question deleted successfully"),
                        @ApiResponse(code = 404, message = "question not found") })
        @DeleteMapping("/{examId}/questions/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
                return questionService.delete(id) ? ResponseEntity.ok().build()
                                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        @ApiOperation(value = "Answer a question", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Answered a question successfully"),
                        @ApiResponse(code = 406, message = "Answer is invalid") })
        @ResponseStatus(HttpStatus.ACCEPTED)
        @PostMapping("/{examId}/questions/{questionId}/answer")
        public ResponseEntity<Void> answer(@PathVariable Integer questionId, @RequestBody String answer) {
                return answerService.answer(questionId, answer) ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

}