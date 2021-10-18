package com.gurukulams.core.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Institute;
import com.gurukulams.core.model.Syllabus;
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
import com.gurukulams.core.service.InstituteService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * The type Institute api controller.
 */
@RestController
@RequestMapping("/api/institutes")
@Tag(name = "Institutes", description = "Resource to manage Institutes")
class InstituteAPIController {

    /**
     * declare a institute service.
     */
    private final InstituteService instituteService;

    InstituteAPIController(final InstituteService anInstituteService) {
        this.instituteService = anInstituteService;
    }

    @Operation(summary = "Creates a new institute",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "institute created successfully"),
            @ApiResponse(responseCode = "401",
                    description = "institute is invalid"),
            @ApiResponse(responseCode = "400",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Optional<Institute>> create(final Principal principal,
                                                      final @RequestBody
                                                              Institute institute) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                instituteService.create(principal.getName(), institute));
    }

    @Operation(summary = "Get the Institute with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting institute successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "institute not found")})
    @GetMapping("/{id}")
    public ResponseEntity<Institute> read(final @PathVariable Long id,
                                         final Principal principal,
                                         final @PathVariable Institute institute) {
        return ResponseEntity.of(instituteService.read(id, principal.getName(),
                institute));
    }

    @Operation(summary = "Updates the institute by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "institute updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "institute is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "institute not found")})
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Institute>> update(final@PathVariable Long id,
                                                     final Principal
                                                             principal,
                                                     final @RequestBody
                                                                  Institute
                                                                  institute)
            throws JsonProcessingException {
        final Optional<Institute> updatedInstitute =
                instituteService.update(id, principal.getName(), institute);
        return updatedInstitute == null ? new ResponseEntity<>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedInstitute);
    }

    @Operation(summary = "Deletes the institute by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "institute deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "institute not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(final @PathVariable
                                               Long id) {
        return instituteService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "lists the institute",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the institute"),
            @ApiResponse(responseCode = "204",
                    description = "syllabus are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping
    public ResponseEntity<List<Institute>> list(final Principal
                                                       principal,
                                               final
                                               @RequestBody
                                                       Institute
                                                       institute) {
        final List<Institute> instituteList = instituteService.list(
                principal.getName(),
                institute);
        return instituteList.isEmpty() ? new ResponseEntity<List<Institute>>(
                HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(instituteList);
    }






}
