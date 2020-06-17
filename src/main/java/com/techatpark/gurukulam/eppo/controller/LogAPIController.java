package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Log;
import com.techatpark.gurukulam.eppo.service.LogService;

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

@Api(value = "Logs", description = "Resource for Logs", tags = { "Logs" })
@RestController
@RequestMapping("/api/logs")
public class LogAPIController {
    private final LogService logService;

    LogAPIController(final LogService logService) {
        this.logService = logService;
    }

    @ApiOperation(value = "List all Logs", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Logs Listed successfully"),
            @ApiResponse(code = 400, message = "Logs Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Log>> findAll() {
        return ResponseEntity.ok(logService.list());
    }

    @ApiOperation(value = "Creates a new Log", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Log created successfully"),
            @ApiResponse(code = 400, message = "Log already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Log> create(@Valid @RequestBody Log log) {
        return ResponseEntity.status(HttpStatus.CREATED).body(logService.create(log));
    }

    @ApiOperation(value = "Finds a Log with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Log with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Log Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Log> findById(@PathVariable Integer id) {
        return ResponseEntity.of(logService.read(id));
    }

    @ApiOperation(value = "Updates a Log", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Log updated successfully"),
            @ApiResponse(code = 400, message = "Log Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Log> update(@PathVariable Integer id, @Valid @RequestBody Log log) {
        return ResponseEntity.ok(logService.update(id, log));
    }

    @ApiOperation(value = "Deletes a Log with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Log deleted successfully"),
            @ApiResponse(code = 400, message = "Log Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Log> delete(@PathVariable Integer id) {
        logService.delete(id);
        return ResponseEntity.ok().build();
    }

}