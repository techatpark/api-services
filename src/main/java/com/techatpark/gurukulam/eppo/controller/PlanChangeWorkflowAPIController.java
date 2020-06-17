package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.PlanChangeWorkflow;
import com.techatpark.gurukulam.eppo.service.PlanChangeWorkflowService;

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

@Api(value = "PlanChangeWorkflows", description = "Resource for PlanChangeWorkflows", tags = { "PlanChangeWorkflows" })
@RestController
@RequestMapping("/api/plan_change_workflow")

public class PlanChangeWorkflowAPIController {

    private final PlanChangeWorkflowService planChangeWorkflowService;

    PlanChangeWorkflowAPIController(final PlanChangeWorkflowService planChangeWorkflowService) {
        this.planChangeWorkflowService = planChangeWorkflowService;
    }

    @ApiOperation(value = "List all PlanChangeWorkflows", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "PlanChangeWorkflows Listed successfully"),
            @ApiResponse(code = 400, message = "PlanChangeWorkflows Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<PlanChangeWorkflow>> findAll() {
        return ResponseEntity.ok(planChangeWorkflowService.list());
    }

    @ApiOperation(value = "Creates a new PlanChangeWorkflow", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "PlanChangeWorkflow created successfully"),
            @ApiResponse(code = 400, message = "PlanChangeWorkflow already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<PlanChangeWorkflow> create(@Valid @RequestBody PlanChangeWorkflow planChangeWorkflow) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planChangeWorkflowService.create(planChangeWorkflow));
    }

    @ApiOperation(value = "Finds a PlanChangeWorkflow with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "PlanChangeWorkflow with a given ID found successfully"),
            @ApiResponse(code = 400, message = "PlanChangeWorkflow Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<PlanChangeWorkflow> findById(@PathVariable Integer id) {
        return ResponseEntity.of(planChangeWorkflowService.read(id));
    }

    @ApiOperation(value = "Updates a PlanChangeWorkflow", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "PlanChangeWorkflow updated successfully"),
            @ApiResponse(code = 400, message = "PlanChangeWorkflow Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<PlanChangeWorkflow> update(@PathVariable Integer id,
            @Valid @RequestBody PlanChangeWorkflow planChangeWorkflow) {
        return ResponseEntity.ok(planChangeWorkflowService.update(id, planChangeWorkflow));
    }

    @ApiOperation(value = "Deletes a PlanChangeWorkflow with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "PlanChangeWorkflow deleted successfully"),
            @ApiResponse(code = 400, message = "PlanChangeWorkflow Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<PlanChangeWorkflow> delete(@PathVariable Integer id) {
        planChangeWorkflowService.delete(id);
        return ResponseEntity.ok().build();
    }
}