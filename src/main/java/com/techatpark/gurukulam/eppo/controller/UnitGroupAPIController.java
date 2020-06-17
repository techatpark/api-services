package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.UnitGroup;
import com.techatpark.gurukulam.eppo.service.UnitGroupService;

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

@Api(value = "UnitGroups", description = "Resource for UnitGroups", tags = { "UnitGroups" })
@RestController
@RequestMapping("/api/unit_groups")
public class UnitGroupAPIController {

    private final UnitGroupService unitGroupService;

    UnitGroupAPIController(final UnitGroupService unitGroupService) {
        this.unitGroupService = unitGroupService;
    }

    @ApiOperation(value = "List all UnitGroups", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitGroups Listed successfully"),
            @ApiResponse(code = 400, message = "UnitGroups Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<UnitGroup>> findAll() {
        return ResponseEntity.ok(unitGroupService.list());
    }

    @ApiOperation(value = "Creates a new UnitGroup", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitGroup created successfully"),
            @ApiResponse(code = 400, message = "UnitGroup already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UnitGroup> create(@Valid @RequestBody UnitGroup unitGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitGroupService.create(unitGroup));
    }

    @ApiOperation(value = "Finds a UnitGroup with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitGroup with a given ID found successfully"),
            @ApiResponse(code = 400, message = "UnitGroup Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<UnitGroup> findById(@PathVariable Integer id) {
        return ResponseEntity.of(unitGroupService.read(id));
    }

    @ApiOperation(value = "Updates a UnitGroup", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitGroup updated successfully"),
            @ApiResponse(code = 400, message = "UnitGroup Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<UnitGroup> update(@PathVariable Integer id, @Valid @RequestBody UnitGroup unitGroup) {
        return ResponseEntity.ok(unitGroupService.update(id, unitGroup));
    }

    @ApiOperation(value = "Deletes a UnitGroup with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitGroup deleted successfully"),
            @ApiResponse(code = 400, message = "UnitGroup Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<UnitGroup> delete(@PathVariable Integer id) {
        unitGroupService.delete(id);
        return ResponseEntity.ok().build();
    }
}