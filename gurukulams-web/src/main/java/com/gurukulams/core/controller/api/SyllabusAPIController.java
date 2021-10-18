package com.gurukulams.core.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Syllabus;
import com.gurukulams.core.service.SyllabusService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/syllabus")
@Tag(name = "Syllabus", description = "Resource to manage Syllabus")
class SyllabusAPIController {
    /**
     * declare a syllabus service.
     */
    private final SyllabusService syllabusService;

    SyllabusAPIController(final SyllabusService asyllabusService) {
        this.syllabusService = asyllabusService;
    }


    @Operation(summary = "Creates a new syllabus",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "syllabus created successfully"),
            @ApiResponse(responseCode = "401",
                    description = "syllabus is invalid"),
            @ApiResponse(responseCode = "400",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Optional<Syllabus>> create(final Principal principal,
                                        final @RequestBody Syllabus syllabus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                syllabusService.create(principal.getName(), syllabus));
    }

    @Operation(summary = "Get the Syllabus with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting syllabus successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})

    @GetMapping("/{id}")
    public ResponseEntity<Syllabus> read(final @PathVariable Long id,
                                         final Principal principal,
                                   final @PathVariable Syllabus syllabus) {
        return ResponseEntity.of(syllabusService.read(id, principal.getName(),
                                                                syllabus));
    }

   @Operation(summary = "Updates the syllabus by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "syllabus updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "syllabus is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Syllabus>> update(final@PathVariable Long id,
                                                     final Principal
                                                        principal,
                                                     final @RequestBody
                                                             Syllabus
                                                             syllabus)
            throws JsonProcessingException {
        final Optional<Syllabus> updatedSyllabus =
                syllabusService.update(id, principal.getName(), syllabus);
        return updatedSyllabus == null ? new ResponseEntity<>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedSyllabus);
    }

    @Operation(summary = "Deletes the syllabus by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "syllabus deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(final @PathVariable
                                               Long id) {
        return syllabusService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "lists the syllabus",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the syllabus"),
            @ApiResponse(responseCode = "204",
                    description = "syllabus are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping
    public ResponseEntity<List<Syllabus>> list(final Principal
                                                     principal,
                                                           final
                                                           @RequestBody
                                                                   Syllabus
                                                                   syllabus) {
        final List<Syllabus> syllabusList = syllabusService.list(
                principal.getName(),
                syllabus);
        return syllabusList.isEmpty() ? new ResponseEntity<List<Syllabus>>(
                HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(syllabusList);
    }
}


