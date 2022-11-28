package com.gurukulams.web.api;

import com.gurukulams.core.model.Annotation;
import com.gurukulams.core.service.BookService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/annotations")
@Tag(name = "Annotations", description = "Resource to manage Annotations")
class AnnotationAPIController {

    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    /**
     * Builds AnnotationAPIController.
     * @param aBookService
     */
    AnnotationAPIController(final BookService aBookService) {
        this.bookService = aBookService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param bookName  the book name
     * @param annotation the annotation
     * @param locale
     * @return the response entity
     */
    @Operation(summary = "Creates a new annotation",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "annotation created successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "400",
                    description = "annotation is invalid")})
    @PostMapping("/{bookName}")
    public ResponseEntity<Optional<Annotation>> create(
            final Principal principal,
            final @NotBlank @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @RequestBody Annotation annotation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.createNote(bookName, principal.getName(),
                        locale,
                        annotation));
    }

    /**
     * Create response entity.
     *
     * @param principal   the principal
     * @param bookName    the book name
     * @param chapterName the annotation
     * @param locale
     * @return the response entity
     */
    @Operation(summary = "Creates a new annotation",
            description =
                    "Can be called only by users "
                            + "with 'auth management'"
                            + " rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "annotation found successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "annotation not found")})
    @PostMapping("/{bookName}/_search")
    public ResponseEntity<List<Annotation>> list(
            final Principal principal,
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @RequestBody String chapterName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookService.searchAnnotations(bookName, principal.getName(),
                        locale,
                        chapterName));
    }

    /**
     * Update response entity.
     *
     * @param bookName the book name
     * @param id       the id
     * @param annotation the annotation
     * @param locale
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
    @PutMapping("/{bookName}/{id}")
    public ResponseEntity<Optional<Annotation>> update(
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @PathVariable UUID id,
            final @RequestBody Annotation annotation) {
        final Optional<Annotation> updatednote = bookService.updateNote(
                id, locale, annotation);
        return updatednote == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatednote);
    }

    /**
     * Delete note by id response entity.
     *
     * @param bookName the book name
     * @param id       the id
     * @param locale
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
    @DeleteMapping("/{bookName}/{id}")
    public ResponseEntity<Void> delete(
            final @PathVariable String bookName,
            @RequestHeader(
                    name = "Accept-Language",
                    required = false) final Locale locale,
            final @PathVariable UUID id) {
        return bookService.deleteNote(id, locale) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
