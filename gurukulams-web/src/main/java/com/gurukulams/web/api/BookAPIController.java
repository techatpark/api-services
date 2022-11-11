package com.gurukulams.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Book;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import com.gurukulams.core.model.UserNote;
import com.gurukulams.core.service.AnswerService;
import com.gurukulams.core.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Book api controller.
 */
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Resource to manage Books")
class BookAPIController {
    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    /**
     * answerService.
     */
    private final AnswerService answerService;

    /**
     * Instantiates a new Book api controller.
     *
     * @param abookService   the book service
     * @param aAnswerService a Answer Service
     */
    BookAPIController(final BookService abookService,
                      final AnswerService aAnswerService) {
        this.bookService = abookService;
        this.answerService = aAnswerService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param book      the book name
     * @param locale    the locale
     * @return the response entity
     */
    @Operation(summary = "Creates a new book",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "book created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "book is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> create(final Principal principal,
                                       @RequestHeader(name = "Accept-Language",
                                       required = false) final Locale locale,
                                       @RequestBody final Book book) {
        Book created = bookService.create(principal.getName(), locale, book);
        return ResponseEntity.created(URI.create("/api/book" + created.id()))
                .body(created);
    }

    /**
     * Read a book.
     *
     * @param id
     * @param principal
     * @param locale    the locale
     * @return a book
     */
    @Operation(summary = "Get the Book with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting book successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})

    @GetMapping("/{id}")
    public ResponseEntity<Book> read(@PathVariable final UUID id,
                                     @RequestHeader(name = "Accept-Language",
                                     required = false) final Locale locale,
                                     final Principal principal) {
        return ResponseEntity.of(bookService.read(principal.getName(),
                locale, id));
    }

    /**
     * Update a Book.
     *
     * @param id
     * @param principal
     * @param locale
     * @param book
     * @return a book
     */
    @Operation(summary = "Updates the book by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "book updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "book is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<Book> update(@PathVariable final UUID id,
                                       final Principal
                                               principal,
                                       @RequestHeader(name = "Accept-Language",
                                       required = false) final Locale locale,
                                       @RequestBody final Book
                                               book) {
        final Book updatedBook =
                bookService.update(id, principal.getName(), locale, book);
        return updatedBook == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedBook);
    }

    /**
     * Delete a Book.
     *
     * @param id
     * @param principal
     * @return book
     */
    @Operation(summary = "Deletes the book by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "book deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "book not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final
                                       UUID id,
                                       final Principal principal) {
        return bookService.delete(principal.getName(),
                id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * List the Books.
     *
     * @param principal
     * @param locale
     * @return list of book
     */
    @Operation(summary = "lists the book",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the book"),
            @ApiResponse(responseCode = "204",
                    description = "book are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> list(final Principal
                                                   principal,
                                           @RequestHeader(
                                                   name = "Accept-Language",
                                   required = false) final Locale locale) {
        final List<Book> bookList = bookService.list(
                principal.getName(), locale);
        return bookList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(bookList);
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param bookName  the book name
     * @param userNotes the user note
     * @return the response entity
     */
    @Operation(summary = "Creates a new user note",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "user note created successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "400",
                    description = "user note is invalid")})
    @PostMapping("/{bookName}/note")
    public ResponseEntity<Optional<UserNote>> createNote(
            final Principal principal,
            final @NotBlank @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @RequestBody UserNote userNotes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.createNote(bookName, principal.getName(),
                        locale,
                        userNotes));
    }

    /**
     * Create response entity.
     *
     * @param principal   the principal
     * @param bookName    the book name
     * @param chapterName the user note
     * @return the response entity
     */
    @Operation(summary = "Creates a new user note",
            description =
                    "Can be called only by users "
                            + "with 'auth management'"
                            + " rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "user note found successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "user note not found")})
    @PostMapping("/{bookName}/note/_search")
    public ResponseEntity<List<UserNote>> searchNotes(
            final Principal principal,
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @RequestBody String chapterName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.searchNotes(bookName, principal.getName(),
                        locale,
                        chapterName));
    }

    /**
     * Update response entity.
     *
     * @param bookName the book name
     * @param id       the id
     * @param userNote the user note
     * @return the response entity
     */
    @Operation(summary = "Updates the note by given id",
            description = "Can be called only by users with "
                    + "'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "note updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "note is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "note not found")})
    @PutMapping("/{bookName}/note/{id}")
    public ResponseEntity<Optional<UserNote>> update(
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @PathVariable UUID id,
            final @RequestBody UserNote userNote) {
        final Optional<UserNote> updatednote = bookService.updateNote(
                id, locale, userNote);
        return updatednote == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatednote);
    }

    /**
     * Delete note by id response entity.
     *
     * @param bookName the book name
     * @param id       the id
     * @return the response entity
     */
    @Operation(summary = "Deletes the note by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "note deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "note not found")})
    @DeleteMapping("/{bookName}/note/{id}")
    public ResponseEntity<Void> deleteNoteById(
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @PathVariable UUID id) {
        return bookService.deleteNote(id, locale) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Is the created_by of the book current user.
     *
     * @param principal the principal
     * @param bookName  the practice id
     * @return the response entity
     */
    @Operation(summary = "Am I the owner the given book ?",
            description = "Can be called only by"
                    + " users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "202",
            description = "Current user is the owner"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "406",
                    description = "Current user is not the created_by")})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{bookName}/owner")
    public ResponseEntity<Void> isOwner(final Principal
                                                principal,
                                        final @PathVariable
                                                String bookName) {
        return bookService.isOwner(principal.getName(), bookName)
                ? ResponseEntity.status(
                HttpStatus.ACCEPTED).build()
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


    /**
     * Create response entity.
     *
     * @param questionType the question type
     * @param question     the question
     * @param bookName     the bookName
     * @param request      the request
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
    @PostMapping("/{bookName}/questions/{questionType}/**")
    public ResponseEntity<Optional<Question>> create(final @PathVariable
                                                             String bookName,
                                                     final @PathVariable
                                                             QuestionType
                                                             questionType,
                                                     final
                                                     @RequestBody
                                                             Question
                                                             question,
                                                     @RequestHeader(
                                                     name = "Accept-Language",
                                         required = false) final Locale locale,
                                                     final Principal principal,
                                             final HttpServletRequest request)
            throws ServletException, IOException {
        String chapterPath = request.getRequestURI().replaceFirst("/api"
                + "/books/" + bookName
                + "/questions/" + questionType + "/", "");

        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.createAQuestion(bookName, questionType, locale,
                        principal.getName(), question,
                        List.of(chapterPath.split("/"))));
    }

    /**
     * Update response entity.
     *
     * @param bookName     the bookname
     * @param questionType the question type
     * @param questionId   the questionId
     * @param question     the question
     * @param request      the request
     * @return the response entity
     */
    @Operation(summary = "Updates the question by given questionId",
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
    @PutMapping("/{bookName}/questions/{questionType}/{questionId}/**")
    public ResponseEntity<Optional<Question>> update(final @PathVariable
                                                             String bookName,
                                                     final @PathVariable
                                                             UUID questionId,
                                                     @RequestHeader(
                                                     name = "Accept-Language",
                                         required = false) final Locale locale,
                                                     final @PathVariable
                                                             QuestionType
                                                             questionType,
                                                     final
                                                     @RequestBody
                                                             Question
                                                             question,
                                                     final
                                                     HttpServletRequest request)
            throws JsonProcessingException {
        String chapterPath = request.getRequestURI().replaceFirst("/api"
                + "/books/" + bookName
                + "/questions/" + questionType
                + "/" + questionId + "/", "");
        final Optional<Question> updatedQuestion =
                bookService.updateQuestion(
                        questionId, locale, questionType,
                        question);
        return updatedQuestion == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedQuestion);
    }

    /**
     * Delete a question from the given question bank.
     *
     * @param bookName the bookname
     * @return the response entity
     */
    @Operation(summary = "Deletes the question bank for the book",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "question deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "question not found")})
    @DeleteMapping("/{bookName}/questions")
    public ResponseEntity<Void> deleteQuestionBank(final @PathVariable
                                                           String bookName)
            throws JsonProcessingException {

        boolean isDeleted = bookService.deleteQuestionBank(bookName);
        return isDeleted ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Delete a question from the given question bank.
     *
     * @param id           the id
     * @param questionType the question type
     * @param bookName     the bookname
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
    @DeleteMapping("/{bookName}/questions/{questionType}/{id}/**")
    public ResponseEntity<Void> deleteAQuestionById(final @PathVariable
                                                            String bookName,
                                                    final @PathVariable UUID id,
                                                    final @PathVariable
                                                            QuestionType
                                                            questionType) {
        bookService.deleteAQuestion(id, QuestionType.CHOOSE_THE_BEST);
        return null;
    }


    /**
     * Find all questions response entity.
     *
     * @param principal the principal
     * @param bookName  the bookName
     * @param request   the request
     * @return the response entity
     */
    @Operation(summary = "lists all the questions for given book and give "
            + "chapter",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing all the questions"),
            @ApiResponse(responseCode = "204",
                    description = "questions are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping("/{bookName}/questions/**")
    public ResponseEntity<List<Question>>
    findAllQuestionsByChap(final Principal
                                   principal,
                           @RequestHeader(
                                   name = "Accept-Language",
                                   required = false) final Locale locale,
                           final
                           @PathVariable
                                   String
                                   bookName, final HttpServletRequest request)
            throws JsonProcessingException {

        String chapterPath = request.getRequestURI().replaceFirst("/api"
                + "/books/" + bookName
                + "/questions/", "");
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.listAllQuestions(principal.getName(),
                        bookName, locale, chapterPath));
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
    @PostMapping("/{bookName}/questions/{questionId}/answer")
    public ResponseEntity<Void> answer(final @PathVariable
                                               UUID questionId,
                                       final @RequestBody
                                               String answer) {
        return answerService.answer(questionId, answer)
                ? ResponseEntity.status(
                HttpStatus.ACCEPTED).build()
                : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
