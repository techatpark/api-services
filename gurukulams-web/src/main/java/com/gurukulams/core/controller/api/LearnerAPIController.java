package com.gurukulams.core.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.LearnerService;
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
 * The type Learner api controller.
 */
@RestController
@RequestMapping("/api/learner")
@Tag(name = "Learners", description = "Resources to manage Learner")
class LearnerAPIController {

    /**
     * declare a learner service.
     */
    private final LearnerService learnerService;

    /**
     * @param alearnerService a learner service
     */
    LearnerAPIController(final LearnerService alearnerService) {
        this.learnerService = alearnerService;
    }

    @Operation(summary = "creates a new Learner",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
                        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
                              description = "learner created successfully"),
                            @ApiResponse(responseCode = "400",
                            description = "learner is invalid"),
                            @ApiResponse(responseCode = "401",
                            description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Learner> create(final Principal principal,
                                          final @RequestBody Learner learner) {
       Learner created = learnerService.create(principal.getName(), learner);
       return ResponseEntity.created(URI.create("/api/learner" + created.id()))
                                                               .body(created);
    }

    @Operation(summary = "Get the Learner with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting learner successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "learner not found")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Learner> read(final Principal principal,
                                  final @PathVariable Long id) {
        return ResponseEntity.of(learnerService.read(principal.getName(),
                                                        id));
    }

    @Operation(summary = "Updates the learner by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "learner updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "learner is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "learner not found")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<Learner> update(final @PathVariable Long id,
                                          final Principal principal,
                                          final @RequestBody Learner learner)
            throws JsonProcessingException {
       final Learner updatedLearner = learnerService.update(id,
                                       principal.getName(), learner);
        return ResponseEntity.ok(updatedLearner);
    }

    @Operation(summary = "Deletes the learner by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "learner deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "learner not found")})
    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(final Long id,
                                       final Principal principal) {
    return learnerService.delete(principal.getName(), id)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }

    @Operation(summary = "lists the learner",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the learner"),
            @ApiResponse(responseCode = "204",
                    description = "learner are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Learner>> list(final Principal principal) {
        final List<Learner> learnerList = learnerService.list(
                                            principal.getName());
        return learnerList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(learnerList);
    }
}
