package com.techatpark.gurukulam.controller;

import com.techatpark.gurukulam.model.UserNote;
import com.techatpark.gurukulam.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The type Book api controller.
 */
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books",
        description = "Resource to manage Books ")
public class BookAPIController {
    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    /**
     * Instantiates a new Book api controller.
     *
     * @param abookService the book service
     */
    public BookAPIController(
            final BookService abookService) {
        this.bookService = abookService;
    }


    /**
     * Create response entity.
     *
     * @param bookName  the book name
     * @param userNotes the user note
     * @return the response entity
     */
    @Operation(summary = "Creates a new user note", description =
            "Can be called only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description =
            "user note created successfully"),
            @ApiResponse(responseCode = "400", description =
                    "user note is invalid")})
    @PostMapping("/{bookName}/note")
    public ResponseEntity<Optional<UserNote>> createNote(final
                                                             @PathVariable
                                                                     String
                                                                 bookName,
                                                         final @Valid
                                                         @RequestBody
                                                             UserNote
                                                             userNotes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.createNote(bookName, userNotes));
    }

    /**
     * Create response entity.
     *
     * @param chapterName the user note
     * @param bookName  the book name
     * @return the response entity
     */
    @Operation(summary = "Creates a new user note", description =
            "Can be called only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description =
            "user note found successfully"),
            @ApiResponse(responseCode = "404", description =
                    "user note not found")})
    @PostMapping("/{bookName}/note/_search")
    public ResponseEntity<List<UserNote>> searchNotes(final @PathVariable String
                                                                 bookName,
                                                     final @Valid @RequestBody
                                                                 String
                                                                 chapterName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.searchNotes(bookName, chapterName));
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Get note with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "note"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @GetMapping("/{bookName}/note/{id}")
    public ResponseEntity<Optional<UserNote>> findById(
            final @PathVariable Integer id) {
        return ResponseEntity.of(Optional.ofNullable(bookService.readNote(id)));
    }

}
