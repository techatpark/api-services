package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.model.Syllabus;
import com.gurukulams.gurukulam.service.SyllabusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

/**
 * The type Syllabus api controller.
 */
@RestController
@RequestMapping("/api/syllabus")
@Tag(name = "Syllabus", description = "Resource to manage Syllabus")
class SyllabusAPIController {
    /**
     * declare a syllabusService.
     */
    private final SyllabusService syllabusService;

    SyllabusAPIController(final SyllabusService asyllabusService) {
        this.syllabusService = asyllabusService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param syllabus  the syllabus
     * @return the response entity
     * @throws IOException the io exception
     */
    @Operation(summary = "Creates a new syllabus", description =
            "Can be called only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Optional<Syllabus>> create(final Principal principal,
                                              final
                                              @RequestBody
                                                      Syllabus
                                                      syllabus)
            throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(syllabusService.create(principal.getName(), syllabus));
    }

    /**
     * Create response entity.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return the response entity
     */
    @Operation(summary = "Creates a new Syllabus",
            description = "Can be called only by users with"
                    + " 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "syllabus created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "syllabus is not invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/userName/syllabus")
    public ResponseEntity<Optional<Syllabus>> create(final @RequestBody
                                                             String userName,
                                                     final @RequestBody
                                                         Syllabus syllabus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                syllabusService.create(userName, syllabus));
    }
}


