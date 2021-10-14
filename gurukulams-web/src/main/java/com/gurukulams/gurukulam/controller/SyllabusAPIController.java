package com.gurukulams.gurukulam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Syllabus;
import com.gurukulams.gurukulam.service.SyllabusService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/syllabus")
@Tag(name = "Syllabus", description = "Resource to manage Syllabus")
class SyllabusAPIController {
    /**
     * declare a bookservice.
     */
    private final SyllabusService syllabusService;

    /**
     * Instantiates a new Book api controller.
     * @param asyllabusService a syllabus service
     */
    SyllabusAPIController(final SyllabusService asyllabusService) {
        this.syllabusService = asyllabusService;
    }

    /**
     * Create response entity.
     * @param id the id
     * @param title the title
     * @param description the description.
     * @return the response entity
     */
    @Operation(summary = "Creates a new syllabus",
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
    @PostMapping("/{id}/{title}/description")
    public ResponseEntity<Optional<Syllabus>> create(final @PathVariable
                                                             long id,
                                                     final @PathVariable
                                                           String title,
                                                     final String
                                                             description)
            throws ServletException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                syllabusService.create(id, title, description));
    }

    /**
     * Find syllabus by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Get syllabus with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "syllabus"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @GetMapping("/title/description/{id}")
    public ResponseEntity<Syllabus> findQuestionById(final @PathVariable
                                                             Integer id) {
        return ResponseEntity.of(syllabusService.read(id));
    }

    /**
     * Update response entity.
     * @param id the id
     * @param title the title
     * @param description the description.
     * @return the response entity
     */
    @Operation(summary = "Updates the syllabus by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "syllabus updated successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @PutMapping("/{id}/{title}/description")
    public ResponseEntity<Optional<Syllabus>> update(final @PathVariable
                                                             long id,
                                                     final @PathVariable
                                                             String title,
                                                     final String description)
            throws JsonProcessingException {

        final Optional<Syllabus> updateSyllabus = syllabusService.update(
                                           id, title, description);
        return updateSyllabus == null
                ? new ResponseEntity<Optional<Syllabus>>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updateSyllabus);
    }

    /**
     * Delete exam by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "Deletes the syllabus by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "syllabus deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSyllabusById(
            final @PathVariable Integer id) {
        return syllabusService.deleteById(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}


