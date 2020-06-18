package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Account;
import com.techatpark.gurukulam.eppo.service.AccountService;

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

@Api(value = "Accounts", description = "Resource for Accounts", tags = { "Accounts" })
@RestController
@RequestMapping("/api/accounts")
public class AccountAPIController {

    private final AccountService accountService;

    AccountAPIController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "List all Accounts", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Accounts Listed successfully"),
            @ApiResponse(code = 400, message = "Accounts Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.list());
    }

    @ApiOperation(value = "Creates a new account", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "account created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    @ApiOperation(value = "Finds a Account with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Account with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Account Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountService.read(id));
    }

    @ApiOperation(value = "Updates a Account", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Account updated successfully"),
            @ApiResponse(code = 400, message = "Account Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Integer id, @Valid @RequestBody Account account) {
        return ResponseEntity.ok(accountService.update(id, account));
    }

    @ApiOperation(value = "Deletes a Account with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Account deleted successfully"),
            @ApiResponse(code = 400, message = "Account Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
}