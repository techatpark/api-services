package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountFee;
import com.techatpark.gurukulam.eppo.service.AccountFeeService;

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

@Api(value = "AccountFees", description = "Resource for AccountFees", tags = { "AccountFees" })
@RestController
@RequestMapping("/api/account_fees")
public class AccountFeeAPIController {

    private final AccountFeeService accountFeeService;

    AccountFeeAPIController(final AccountFeeService accountFeeService) {
        this.accountFeeService = accountFeeService;
    }

    @ApiOperation(value = "List all AccountFees", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountFees Listed successfully"),
            @ApiResponse(code = 400, message = "AccountFees Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AccountFee>> findAll() {
        return ResponseEntity.ok(accountFeeService.list());
    }

    @ApiOperation(value = "Creates a new AccountFee", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "account created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountFee> create(@Valid @RequestBody AccountFee accountFee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountFeeService.create(accountFee));
    }

    @ApiOperation(value = "Finds a AccountFee with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountFee with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AccountFee Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AccountFee> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountFeeService.read(id));
    }

    @ApiOperation(value = "Updates a AccountFee", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountFee updated successfully"),
            @ApiResponse(code = 400, message = "AccountFee Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AccountFee> update(@PathVariable Integer id, @Valid @RequestBody AccountFee accountFee) {
        return ResponseEntity.ok(accountFeeService.update(id, accountFee));
    }

    @ApiOperation(value = "Deletes a AccountFee with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountFee deleted successfully"),
            @ApiResponse(code = 400, message = "AccountFee Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountFee> delete(@PathVariable Integer id) {
        accountFeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}