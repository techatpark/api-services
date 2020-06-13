package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountEppopayPlan;
import com.techatpark.gurukulam.eppo.service.AccountEppopayPlanService;

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

@Api(value = "AccountEppopayPlans", description = "Resource for AccountEppopayPlans", tags = { "AccountEppopayPlans" })
@RestController
@RequestMapping("/api/account_eppopay_plans")

public class AccountEppopayPlanAPIController {

    private final AccountEppopayPlanService accountEppopayPlanService;

    AccountEppopayPlanAPIController(final AccountEppopayPlanService accountEppopayPlanService) {
        this.accountEppopayPlanService = accountEppopayPlanService;
    }

    @ApiOperation(value = "List all AccountEppopayPlans", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountEppopayPlans Listed successfully"),
            @ApiResponse(code = 400, message = "AccountEppopayPlans Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AccountEppopayPlan>> findAll() {
        return ResponseEntity.ok(accountEppopayPlanService.list());
    }

    @ApiOperation(value = "Creates a new AccountEppopayPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountEppopayPlan created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountEppopayPlan> create(@Valid @RequestBody AccountEppopayPlan accountEppopayPlan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountEppopayPlanService.create(accountEppopayPlan));
    }

    @ApiOperation(value = "Finds a AccountEppopayPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountEppopayPlan with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AccountEppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AccountEppopayPlan> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountEppopayPlanService.read(id));
    }

    @ApiOperation(value = "Updates a AccountEppopayPlan", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountEppopayPlan updated successfully"),
            @ApiResponse(code = 400, message = "AccountEppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AccountEppopayPlan> update(@PathVariable Integer id,
            @Valid @RequestBody AccountEppopayPlan accountEppopayPlan) {
        return ResponseEntity.ok(accountEppopayPlanService.update(id, accountEppopayPlan));
    }

    @ApiOperation(value = "Deletes a AccountEppopayPlan with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountEppopayPlan deleted successfully"),
            @ApiResponse(code = 400, message = "AccountEppopayPlan Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountEppopayPlan> delete(@PathVariable Integer id) {
        accountEppopayPlanService.delete(id);
        return ResponseEntity.ok().build();
    }

}