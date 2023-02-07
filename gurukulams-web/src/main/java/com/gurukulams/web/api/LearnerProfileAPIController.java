package com.gurukulams.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.LearnerProfile;
import com.gurukulams.core.service.LearnerProfileService;
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
 * The type Learner profile api controller.
 */
@RestController
@RequestMapping("/api/profile")
@Tag(name = "Learner Profiles",
        description = "Resources to manage Learner Profile")
class LearnerProfileAPIController {

    /**
     * declare a learnerprofile service.
     */
    private final LearnerProfileService learnerProfileService;

    /**
     * Instantiates a new Learner profile api controller.
     *
     * @param aLearnerProfileService the learner profile service
     */
    LearnerProfileAPIController(final LearnerProfileService
                                               aLearnerProfileService) {
        this.learnerProfileService = aLearnerProfileService;
    }

    /**
     * Create response entity.
     *
     * @param principal      the principal
     * @param learnerProfile the learner profile
     * @return the response entity
     */
    @Operation(summary = "creates a new Learner Profile",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "learner profile created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "learner profile is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<LearnerProfile> create(final Principal principal,
                                                 final @RequestBody
                                                 LearnerProfile
                                                         learnerProfile
                                                 ) {
        LearnerProfile created = learnerProfileService
                .create(principal.getName(), learnerProfile);
        return ResponseEntity.created(URI.create("/api/profile" + created.id()))
                .body(created);
    }

    /**
     * Read response entity.
     *
     * @param principal the principal
     * @param id        the id
     * @return the response entity
     */
    @Operation(summary = "Get the LearnerProfile with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting LearnerProfile successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "LearnerProfile not found")})
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<LearnerProfile> read(final Principal principal,
                                        final @PathVariable String id) {
        return ResponseEntity.of(learnerProfileService.read(principal.getName(),
                id));
    }

    /**
     * Update response entity.
     *
     * @param id        the id
     * @param principal the principal
     * @param learner   the learner
     * @return the response entity
     * @throws JsonProcessingException the json processing exception
     */
    @Operation(summary = "Updates the LearnerProfile by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "LearnerProfile updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "LearnerProfile is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "LearnerProfile not found")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<LearnerProfile> update(final @PathVariable String id,
                                          final Principal principal,
                                          final @RequestBody
                                                     LearnerProfile learner)
            throws JsonProcessingException {
        final LearnerProfile updatedLearner = learnerProfileService.update(id,
                principal.getName(), learner);
        return ResponseEntity.ok(updatedLearner);
    }

    /**
     * Delete response entity.
     *
     * @param id        the id
     * @param principal the principal
     * @return the response entity
     */
    @Operation(summary = "Deletes the LearnerProfile by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "LearnerProfile deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "LearnerProfile not found")})
    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(final String id,
                                       final Principal principal) {
        return learnerProfileService.delete(principal.getName(), id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * List response entity.
     *
     * @param principal the principal
     * @return the response entity
     */
    @Operation(summary = "lists the LearnerProfile",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the LearnerProfile"),
            @ApiResponse(responseCode = "204",
                    description = "LearnerProfile are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<LearnerProfile>> list(final
                                                         Principal principal) {
        final List<LearnerProfile> learnerList = learnerProfileService.list(
                principal.getName());
        return learnerList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(learnerList);
    }
}
