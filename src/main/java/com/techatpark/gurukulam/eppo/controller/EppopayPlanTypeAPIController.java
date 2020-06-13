package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.EppopayPlanType;
import com.techatpark.gurukulam.eppo.service.EppopayPlanTypeService;

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

@Api(value = "EppopayPlanTypes", description = "Resource for EppopayPlanTypes", tags = { "EppopayPlanTypes" })
@RestController
@RequestMapping("/api/eppopay_plan_types")

public class EppopayPlanTypeAPIController {

    private final EppopayPlanTypeService eppopayPlanTypeService;

    EppopayPlanTypeAPIController(final EppopayPlanTypeService eppopayPlanTypeService) {
        this.eppopayPlanTypeService = eppopayPlanTypeService;
    }

    @ApiOperation(value = "List all EppopayPlanTypes", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlanTypes Listed successfully"),
            @ApiResponse(code = 400, message = "EppopayPlanTypes Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<EppopayPlanType>> findAll() {
        return ResponseEntity.ok(eppopayPlanTypeService.list());
    }

    @ApiOperation(value = "Creates a new EppopayPlanType", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlanType created successfully"),
            @ApiResponse(code = 400, message = "EppopayPlanType already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<EppopayPlanType> create(@Valid @RequestBody EppopayPlanType eppopayPlanType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eppopayPlanTypeService.create(eppopayPlanType));
    }

    @ApiOperation(value = "Finds a EppopayPlanType with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlanType with a given ID found successfully"),
            @ApiResponse(code = 400, message = "EppopayPlanType Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<EppopayPlanType> findById(@PathVariable Integer id) {
        return ResponseEntity.of(eppopayPlanTypeService.read(id));
    }

    @ApiOperation(value = "Updates a EppopayPlanType", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlanType updated successfully"),
            @ApiResponse(code = 400, message = "EppopayPlanType Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<EppopayPlanType> update(@PathVariable Integer id,
            @Valid @RequestBody EppopayPlanType eppopayPlanType) {
        return ResponseEntity.ok(eppopayPlanTypeService.update(id, eppopayPlanType));
    }

    @ApiOperation(value = "Deletes a EppopayPlanType with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlanType deleted successfully"),
            @ApiResponse(code = 400, message = "EppopayPlanType Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<EppopayPlanType> delete(@PathVariable Integer id) {
        eppopayPlanTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}