package com.techatpark.gurukulam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techatpark.gurukulam.model.Practice;
import com.techatpark.gurukulam.model.Question;
import com.techatpark.gurukulam.service.AnswerService;
import com.techatpark.gurukulam.service.PracticeService;
import com.techatpark.gurukulam.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


abstract class PracticeAPIController<T extends Practice> {
    /**
     * sqlPracticeService.
     */
    private final PracticeService practiceService;
    /**
     * questionService.
     */
    private final QuestionService questionService;
    /**
     * answerService.
     */
    private final AnswerService answerService;

    PracticeAPIController(final PracticeService newPracticeService,
                          final QuestionService newQuestionService,
                          final AnswerService newAnswerService) {
        this.practiceService = newPracticeService;
        this.questionService = newQuestionService;
        this.answerService = newAnswerService;
    }

    @Operation(summary = "Creates a new practice", description =
            "Can be called only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description =
            "practice created successfully"),
            @ApiResponse(responseCode = "400", description =
                    "practice is invalid")})
    @PostMapping
    public ResponseEntity<Optional<T>> create(final Principal principal,
                                              final
                                              @RequestBody
                                              @Valid
                                              @NotNull
                                              @NotBlank T
                                                      practice)
            throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(practiceService.create(getType(),
                        principal.getName(), practice));
    }

    @Operation(summary = "Get practice with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @GetMapping("/{id}")
    public ResponseEntity<T> findById(
            final @PathVariable Integer id) {
        return ResponseEntity.of(practiceService.read(id));
    }

    @Operation(summary = "lists all the practice",
            description = "Can be Invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing all the practice"),
            @ApiResponse(responseCode = "204",
                    description = "practice are not available")})
    @GetMapping
    public ResponseEntity<Page<T>> findAll(
            @NotNull final Pageable pageable) {
        final Page<T> practices = practiceService.page(getType(), pageable);
        return practices.isEmpty() ? new ResponseEntity<Page<T>>(
                HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(practices);
    }

    @Operation(summary = "Updates the practice by given id",
            description = "Can be called only by users with "
                    + "'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "practice is invalid"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Optional<T>> update(final @PathVariable
                                                      Integer id,
                                              final @Valid
                                              @RequestBody
                                                      Practice
                                                      practice)
            throws JsonProcessingException {
        final Optional<T> updatedpractice = (Optional<T>)
                practiceService.update(id, practice);
        return updatedpractice == null ? new ResponseEntity<Optional<T>>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedpractice);
    }

    @Operation(summary = "Deletes the practice by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamById(
            final @PathVariable Integer id) {
        return practiceService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Creates a new question",
            description = "Can be called only by users with"
                    + " 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "question created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "question is invalid")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{practiceId}/questions/{questionType}")
    public ResponseEntity<Optional<Question>> create(final @PathVariable
                                                             Integer practiceId,
                                                     final @PathVariable
                                                             String
                                                             questionType,
                                                     final @Valid
                                                     @RequestBody
                                                             Question
                                                             question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                questionService.create(practiceId, questionType, question));
    }

    @Operation(summary = "Get question with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @GetMapping("/{practiceId}/questions/{id}")
    public ResponseEntity<Question> findQuestionById(final @PathVariable
                                                             Integer id) {
        return ResponseEntity.of(questionService.read(id));
    }

    @Operation(summary = "lists all the questions",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing all the questions"),
            @ApiResponse(responseCode = "204",
                    description = "questions are not available")})
    @GetMapping("/{practiceId}/questions")
    public ResponseEntity<List<Question>> findAllQuestions(final Principal
                                                                   principal,
                                                           final
                                                           @PathVariable
                                                                   Integer
                                                                   practiceId) {
        final List<Question> questions = questionService.list(principal.getName(),
                practiceId);
        return questions.isEmpty() ? new ResponseEntity<List<Question>>(
                HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(questions);
    }

    @Operation(summary = "Updates the question by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "question is invalid"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @PutMapping("/{practiceId}/questions/{id}")
    public ResponseEntity<Optional<Question>> update(final @PathVariable
                                                             Integer practiceId,
                                                     final @PathVariable
                                                             Integer id,
                                                     final @Valid
                                                     @RequestBody
                                                             Question
                                                             question) {
        final Optional<Question> updatedQuestion = questionService.update(
                practiceId, id, question);
        return updatedQuestion == null
                ? new ResponseEntity<Optional<Question>>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedQuestion);
    }

    @Operation(summary = "Deletes the question by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @DeleteMapping("/{practiceId}/questions/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Integer id) {
        return questionService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Answer a question",
            description = "Can be called only by"
                    + " users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "202",
            description = "Answered a question successfully"),
            @ApiResponse(responseCode = "406",
                    description = "Answer is invalid")})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{practiceId}/questions/{questionId}/answer")
    public ResponseEntity<Void> answer(final @PathVariable
                                               Integer questionId,
                                       final @RequestBody
                                               String answer) {
        return answerService.answer(questionId, answer)
                ? ResponseEntity.status(
                HttpStatus.ACCEPTED).build()
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    protected abstract String getType();

}
