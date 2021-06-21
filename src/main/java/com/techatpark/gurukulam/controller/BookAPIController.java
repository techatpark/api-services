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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * The type Book api controller.
 */
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Resource to manage Books ")
class BookAPIController {
    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    /**
     * Instantiates a new Book api controller.
     *
     * @param abookService the book service
     */
    BookAPIController(final BookService abookService) {
        this.bookService = abookService;
    }

    /**
     * Create response entity.
     *
     * @param principal
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
            final @RequestBody UserNote userNotes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.createNote(bookName, principal.getName(),
                        userNotes));
    }

    /**
     * Create response entity.
     *
     * @param principal
     * @param bookName    the book name
     * @param chapterName the user note
     * @return the response entity
     */
    @Operation(summary = "Creates a new user note",
            description =
                    "Can be called only by users with 'auth management'"
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
            final @RequestBody String chapterName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.searchNotes(bookName, principal.getName(),
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
            final @PathVariable Integer id,
            final @RequestBody UserNote userNote) {
        final Optional<UserNote> updatednote = bookService.updateNote(
                id, userNote);
        return updatednote == null ? new ResponseEntity<>(
                HttpStatus.NOT_FOUND)
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
            final @PathVariable Integer id) {
        return bookService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(
                HttpStatus.NOT_FOUND);
    }

}
