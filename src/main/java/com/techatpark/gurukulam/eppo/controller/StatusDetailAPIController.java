package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.StatusDetail;
import com.techatpark.gurukulam.eppo.service.StatusDetailService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "StatusDetails", description = "Resource for StatusDetails", tags = { "StatusDetails" })
@RestController
@RequestMapping("/api/status_details")
public class StatusDetailAPIController {

    private final StatusDetailService statusDetailService;

    StatusDetailAPIController(final StatusDetailService statusDetailService) {
        this.statusDetailService = statusDetailService;
    }

    @ApiOperation(value = "List all StatusDetails", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "StatusDetails Listed successfully"),
            @ApiResponse(code = 400, message = "StatusDetails Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<StatusDetail>> findAll() {
        return ResponseEntity.ok(statusDetailService.list());
    }

    @ApiOperation(value = "Creates a new StatusDetail", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "StatusDetail created successfully"),
            @ApiResponse(code = 400, message = "StatusDetail already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<StatusDetail> create(@Valid @RequestBody StatusDetail statusDetail) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statusDetailService.create(statusDetail));
    }

    @ApiOperation(value = "Finds a StatusDetail with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "StatusDetail with a given ID found successfully"),
            @ApiResponse(code = 400, message = "StatusDetail Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<StatusDetail> findById(@PathVariable Integer id) {
        return ResponseEntity.of(statusDetailService.read(id));
    }

    @ApiOperation(value = "Updates a StatusDetail", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "StatusDetail updated successfully"),
            @ApiResponse(code = 400, message = "StatusDetail Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<StatusDetail> update(@PathVariable Integer id,
            @Valid @RequestBody StatusDetail statusDetail) {
        return ResponseEntity.ok(statusDetailService.update(id, statusDetail));
    }

    @ApiOperation(value = "Deletes a StatusDetail with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "StatusDetail deleted successfully"),
            @ApiResponse(code = 400, message = "StatusDetail Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusDetail> delete(@PathVariable Integer id) {
        statusDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}