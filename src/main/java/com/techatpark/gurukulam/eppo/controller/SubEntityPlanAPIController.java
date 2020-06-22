package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.SubEntityPlan;
import com.techatpark.gurukulam.eppo.service.SubEntityPlanService;

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

@Api(value = "SubEntityPlan", description = "Resource for SubEntityPlan", tags = { "SubEntityPlan" })
@RestController
@RequestMapping("/api/sub_entity_plans")
public class SubEntityPlanAPIController {

    private final SubEntityPlanService subEntityPlanService;

    SubEntityPlanAPIController(final SubEntityPlanService subEntityPlanService) {
        this.subEntityPlanService = subEntityPlanService;
    }

    @ApiOperation(value = "List all SubEntityPlans", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntityPlans Listed successfully"),
            @ApiResponse(code = 400, message = "SubEntityPlans Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<SubEntityPlan>> findAll() {
        return ResponseEntity.ok(subEntityPlanService.list());
    }

    @ApiOperation(value = "Creates a new subEntityPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntityPlan created successfully"),
            @ApiResponse(code = 400, message = "SubEntityPlan already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<SubEntityPlan> create(@Valid @RequestBody SubEntityPlan subEntityPlan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subEntityPlanService.create(subEntityPlan));
    }

    @ApiOperation(value = "Finds a SubEntityPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntityPlan with a given ID found successfully"),
            @ApiResponse(code = 400, message = "SubEntityPlan Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<SubEntityPlan> findById(@PathVariable Integer id) {
        return ResponseEntity.of(subEntityPlanService.read(id));
    }

    @ApiOperation(value = "Updates a SubEntityPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntityPlan updated successfully"),
            @ApiResponse(code = 400, message = "SubEntityPlan Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<SubEntityPlan> update(@PathVariable Integer id,
            @Valid @RequestBody SubEntityPlan subEntityPlan) {
        return ResponseEntity.ok(subEntityPlanService.update(id, subEntityPlan));
    }

    @ApiOperation(value = "Deletes a SubEntityPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntityPlan deleted successfully"),
            @ApiResponse(code = 400, message = "SubEntityPlan Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<SubEntityPlan> delete(@PathVariable Integer id) {
        subEntityPlanService.delete(id);
        return ResponseEntity.ok().build();
    }
}