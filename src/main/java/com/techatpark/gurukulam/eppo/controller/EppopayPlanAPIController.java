package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.EppopayPlan;
import com.techatpark.gurukulam.eppo.service.EppopayPlanService;

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

@Api(value = "EppopayPlans", description = "Resource for EppopayPlans", tags = { "EppopayPlans" })
@RestController
@RequestMapping("/api/eppopay_plans")

public class EppopayPlanAPIController {

    private final EppopayPlanService eppopayPlanService;

    EppopayPlanAPIController(final EppopayPlanService eppopayPlanService) {
        this.eppopayPlanService = eppopayPlanService;
    }

    @ApiOperation(value = "List all EppopayPlans", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlans Listed successfully"),
            @ApiResponse(code = 400, message = "EppopayPlans Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<EppopayPlan>> findAll() {
        return ResponseEntity.ok(eppopayPlanService.list());
    }

    @ApiOperation(value = "Creates a new EppopayPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlan created successfully"),
            @ApiResponse(code = 400, message = "EppopayPlan already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<EppopayPlan> create(@Valid @RequestBody EppopayPlan eppopayPlan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eppopayPlanService.create(eppopayPlan));
    }

    @ApiOperation(value = "Finds a EppopayPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlan with a given ID found successfully"),
            @ApiResponse(code = 400, message = "EppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<EppopayPlan> findById(@PathVariable Integer id) {
        return ResponseEntity.of(eppopayPlanService.read(id));
    }

    @ApiOperation(value = "Updates a EppopayPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlan updated successfully"),
            @ApiResponse(code = 400, message = "EppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<EppopayPlan> update(@PathVariable Integer id, @Valid @RequestBody EppopayPlan eppopayPlan) {
        return ResponseEntity.ok(eppopayPlanService.update(id, eppopayPlan));
    }

    @ApiOperation(value = "Deletes a EppopayPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "EppopayPlan deleted successfully"),
            @ApiResponse(code = 400, message = "EppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<EppopayPlan> delete(@PathVariable Integer id) {
        eppopayPlanService.delete(id);
        return ResponseEntity.ok().build();
    }
}