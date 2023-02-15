package com.gurukulams.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Institute;
import com.gurukulams.core.service.InstituteService;
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

import java.net.URI;
import java.security.Principal;
import java.util.List;

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
            @ApiResponse(responseCode = "400",
                    description = "institute is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Institute> create(final Principal principal,
                                            final @RequestBody
                                                    Institute institute) {
        Institute createdInstitute =
                instituteService.create(principal.getName(), institute);
        return ResponseEntity.created(URI.create("/api/syllabus"
                        + createdInstitute.id()))
                .body(createdInstitute);

    }

    @Operation(summary = "Get the Institute with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting institute successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "institute not found")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Institute> read(final @PathVariable String id,
                                          final Principal principal) {
        return ResponseEntity.of(
                instituteService.read(principal.getName(), id));
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
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<Institute> update(final @PathVariable
                                                    String id,
                                            final Principal
                                                    principal,
                                            final @RequestBody
                                                    Institute
                                                    institute)
            throws JsonProcessingException {
        final Institute updatedInstitute =
                instituteService.update(id, principal.getName(), institute);
        return ResponseEntity.ok(updatedInstitute);
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
                                               String id,
                                       final Principal
                                               principal) {
        return instituteService.delete(principal.getName(), id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
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
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Institute>> list(final Principal
                                                        principal) {
        final List<Institute> instituteList = instituteService.list(
                principal.getName());
        return instituteList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(instituteList);
    }


}
