package com.gurukulams.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import com.gurukulams.core.service.AnswerService;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import com.gurukulams.core.service.UserNoteService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


/**
 * The type Practice api controller.
 *
 * @param <T> the type parameter
 */
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


    /**
     * Instantiates a new Practice api controller.
     *
     * @param newPracticeService the new practice service
     * @param newQuestionService the new question service
     * @param newAnswerService   the new answer service
     * @param userNotesService
     */
    PracticeAPIController(final PracticeService newPracticeService,
                          final QuestionService newQuestionService,
                          final AnswerService newAnswerService,
                          final UserNoteService userNotesService) {
        this.practiceService = newPracticeService;
        this.questionService = newQuestionService;
        this.answerService = newAnswerService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param practice  the practice
     * @param locale locale
     * @return the response entity
     * @throws IOException the io exception
     */
    @Operation(summary = "Creates a new practice", description =
            "Can be called only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Optional<T>> create(final Principal principal,
                                              @RequestHeader(
                                              name = "Accept-Language",
                                              required = false)
                                              final Locale locale,
                                              final
                                              @RequestBody
                                                      T
                                                      practice)
            throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(practiceService.create(getType(),
                        principal.getName(), locale, practice));
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Get practice with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @GetMapping("/{id}")
    public ResponseEntity<T> findById(
            final @PathVariable Integer id) {
        return ResponseEntity.of(practiceService.read(id));
    }

    /**
     * Find all response entity.
     *
     * @param pageable the pageable
     * @return the response entity
     */
    @Operation(summary = "lists all the practice",
            description = "Can be Invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing all the practice"),
            @ApiResponse(responseCode = "204",
                    description = "practice are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping
    public ResponseEntity<Page<T>> findAll(
            @NotNull final Pageable pageable) {
        final Page<T> practices = practiceService.page(getType(), pageable);
        return practices.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(practices);
    }

    /**
     * Update response entity.
     *
     * @param id       the id
     * @param practice the practice
     * @return the response entity
     * @throws JsonProcessingException the json processing exception
     */
    @Operation(summary = "Updates the practice by given id",
            description = "Can be called only by users with "
                    + "'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "practice is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Optional<T>> update(final @PathVariable
                                                      Integer id,
                                              final
                                              @RequestBody
                                                      Practice
                                                      practice)
            throws JsonProcessingException {
        final Optional<T> updatedpractice = (Optional<T>)
                practiceService.update(id, practice);
        return updatedpractice == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedpractice);
    }

    /**
     * Delete exam by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Deletes the practice by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamById(
            final @PathVariable Integer id) {
        return practiceService.delete(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Create response entity.
     *
     * @param principal   the user principal
     * @param practiceId   the practice id
     * @param questionType the question type
     * @param question     the question
     * @param locale locale
     * @return the response entity
     */
    @Operation(summary = "Creates a new question",
            description = "Can be called only by users with"
                    + " 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "question created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "question is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{practiceId}/questions/{questionType}")
    public ResponseEntity<Optional<Question>> create(final Principal
                                                                 principal,
                                                     final @PathVariable
                                                             Integer practiceId,
                                                     @RequestHeader(
                                                     name = "Accept-Language",
                                                     required = false)
                                                     final Locale locale,
                                                     final @PathVariable
                                                             QuestionType
                                                             questionType,
                                                     final
                                                     @RequestBody
                                                             Question
                                                             question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                              questionService.create(practiceId,
                                      questionType, locale,
                                                       question,
                                      principal.getName()));
    }


    /**
     * Find question by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Get question with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @GetMapping("/{practiceId}/questions/{id}")
    public ResponseEntity<Question> findQuestionById(final @PathVariable
                                                             Integer id) {
        return ResponseEntity.of(questionService.read(id));
    }

    /**
     * Find all questions response entity.
     *
     * @param principal  the principal
     * @param practiceId the practice id
     * @return the response entity
     */
    @Operation(summary = "lists all the questions",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing all the questions"),
            @ApiResponse(responseCode = "204",
                    description = "questions are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping("/{practiceId}/questions")
    public ResponseEntity<List<Question>> findAllQuestions(final Principal
                                                                   principal,
                                                           final
                                                           @PathVariable
                                                                   Integer
                                                                   practiceId) {
        final List<Question> questions = questionService.list(
                principal.getName(),
                practiceId);
        return questions.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(questions);
    }

    /**
     * Update response entity.
     *
     * @param practiceId   the practice id
     * @param questionType the question type
     * @param id           the id
     * @param question     the question
     * @return the response entity
     */
    @Operation(summary = "Updates the question by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "question is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @PutMapping("/{practiceId}/questions/{questionType}/{id}")
    public ResponseEntity<Optional<Question>> update(final @PathVariable
                                                             Integer practiceId,
                                                     final @PathVariable
                                                             Integer id,
                                                     final @PathVariable
                                                             QuestionType
                                                             questionType,
                                                     final
                                                     @RequestBody
                                                             Question
                                                             question) {
        final Optional<Question> updatedQuestion = questionService.update(
                practiceId, questionType, id, question);
        return updatedQuestion == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedQuestion);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Deletes the question by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @DeleteMapping("/{practiceId}/questions/{id}")
    public ResponseEntity<Void> delete(final @PathVariable Integer id) {
        return questionService.delete(id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Answer response entity.
     *
     * @param questionId the question id
     * @param answer     the answer
     * @return the response entity
     */
    @Operation(summary = "Answer a question",
            description = "Can be called only by"
                    + " users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "202",
            description = "Answered a question successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
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

    /**
     * Gets type.
     *
     * @return the type
     */
    protected abstract String getType();

}
