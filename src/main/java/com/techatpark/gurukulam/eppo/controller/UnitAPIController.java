package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Unit;
import com.techatpark.gurukulam.eppo.service.UnitService;

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

@Api(value = "Units", description = "Resource for Units", tags = { "Units" })
@RestController
@RequestMapping("/api/units")
public class UnitAPIController {

    private final UnitService unitService;

    UnitAPIController(final UnitService unitService) {
        this.unitService = unitService;
    }

    @ApiOperation(value = "List all Units", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Units Listed successfully"),
            @ApiResponse(code = 400, message = "Units Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Unit>> findAll() {
        return ResponseEntity.ok(unitService.list());
    }

    @ApiOperation(value = "Creates a new Unit", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Unit created successfully"),
            @ApiResponse(code = 400, message = "Unit already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Unit> create(@Valid @RequestBody Unit unit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitService.create(unit));
    }

    @ApiOperation(value = "Finds a Unit with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Unit with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Unit Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Unit> findById(@PathVariable Integer id) {
        return ResponseEntity.of(unitService.read(id));
    }

    @ApiOperation(value = "Updates a Unit", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Unit updated successfully"),
            @ApiResponse(code = 400, message = "Unit Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Unit> update(@PathVariable Integer id, @Valid @RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.update(id, unit));
    }

    @ApiOperation(value = "Deletes a Unit with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Unit deleted successfully"),
            @ApiResponse(code = 400, message = "Unit Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Unit> delete(@PathVariable Integer id) {
        unitService.delete(id);
        return ResponseEntity.ok().build();
    }
}