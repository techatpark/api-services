package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.UnitType;
import com.techatpark.gurukulam.eppo.service.UnitTypeService;

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

@Api(value = "UnitTypes", description = "Resource for UnitTypes", tags = { "UnitTypes" })
@RestController
@RequestMapping("/api/unit_types")

public class UnitTypeAPIController {

    private final UnitTypeService unitTypeService;

    UnitTypeAPIController(final UnitTypeService unitTypeService) {
        this.unitTypeService = unitTypeService;
    }

    @ApiOperation(value = "List all UnitTypes", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "UnitTypes Listed successfully"),
            @ApiResponse(code = 400, message = "UnitTypes Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<UnitType>> findAll() {
        return ResponseEntity.ok(unitTypeService.list());
    }

    @ApiOperation(value = "Creates a new UnitType", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitType created successfully"),
            @ApiResponse(code = 400, message = "UnitType already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UnitType> create(@Valid @RequestBody UnitType unitType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitTypeService.create(unitType));
    }

    @ApiOperation(value = "Finds a UnitType with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitType with a given ID found successfully"),
            @ApiResponse(code = 400, message = "UnitType Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<UnitType> findById(@PathVariable Integer id) {
        return ResponseEntity.of(unitTypeService.read(id));
    }

    @ApiOperation(value = "Updates a UnitType", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitType updated successfully"),
            @ApiResponse(code = 400, message = "UnitType Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<UnitType> update(@PathVariable Integer id, @Valid @RequestBody UnitType unitType) {
        return ResponseEntity.ok(unitTypeService.update(id, unitType));
    }

    @ApiOperation(value = "Deletes a UnitType with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitType deleted successfully"),
            @ApiResponse(code = 400, message = "UnitType Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<UnitType> delete(@PathVariable Integer id) {
        unitTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}