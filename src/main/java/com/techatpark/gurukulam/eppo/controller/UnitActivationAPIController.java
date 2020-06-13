package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.UnitActivation;
import com.techatpark.gurukulam.eppo.service.UnitActivationService;

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

@Api(value = "UnitActivations", description = "Resource for UnitActivations", tags = { "UnitActivations" })
@RestController
@RequestMapping("/api/unit_activations")
public class UnitActivationAPIController {

    private final UnitActivationService unitActivationService;

    UnitActivationAPIController(final UnitActivationService unitActivationService) {
        this.unitActivationService = unitActivationService;
    }

    @ApiOperation(value = "List all UnitActivations", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitActivations Listed successfully"),
            @ApiResponse(code = 400, message = "UnitActivations Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<UnitActivation>> findAll() {
        return ResponseEntity.ok(unitActivationService.list());
    }

    @ApiOperation(value = "Creates a new UnitActivation", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitActivation created successfully"),
            @ApiResponse(code = 400, message = "UnitActivation already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UnitActivation> create(@Valid @RequestBody UnitActivation unitActivation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitActivationService.create(unitActivation));
    }

    @ApiOperation(value = "Finds a UnitActivation with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitActivation with a given ID found successfully"),
            @ApiResponse(code = 400, message = "UnitActivation Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<UnitActivation> findById(@PathVariable Integer id) {
        return ResponseEntity.of(unitActivationService.read(id));
    }

    @ApiOperation(value = "Updates a UnitActivation", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitActivation updated successfully"),
            @ApiResponse(code = 400, message = "UnitActivation Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<UnitActivation> update(@PathVariable Integer id,
            @Valid @RequestBody UnitActivation unitActivation) {
        return ResponseEntity.ok(unitActivationService.update(id, unitActivation));
    }

    @ApiOperation(value = "Deletes a UnitActivation with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "UnitActivation deleted successfully"),
            @ApiResponse(code = 400, message = "UnitActivation Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<UnitActivation> delete(@PathVariable Integer id) {
        unitActivationService.delete(id);
        return ResponseEntity.ok().build();
    }
}