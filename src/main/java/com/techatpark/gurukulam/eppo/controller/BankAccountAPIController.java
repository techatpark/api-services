package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.BankAccount;
import com.techatpark.gurukulam.eppo.service.BankAccountService;

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

@Api(value = "BankAccounts", description = "Resource for BankAccounts", tags = { "BankAccounts" })
@RestController
@RequestMapping("/api/bank_accounts")

public class BankAccountAPIController {

    private final BankAccountService bankAccountService;

    BankAccountAPIController(final BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @ApiOperation(value = "List all BankAccounts", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccounts Listed successfully"),
            @ApiResponse(code = 400, message = "BankAccounts Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<BankAccount>> findAll() {
        return ResponseEntity.ok(bankAccountService.list());
    }

    @ApiOperation(value = "Creates a new BankAccount", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount created successfully"),
            @ApiResponse(code = 400, message = "BankAccount already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BankAccount> create(@Valid @RequestBody BankAccount bankAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.create(bankAccount));
    }

    @ApiOperation(value = "Finds a BankAccount with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount with a given ID found successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> findById(@PathVariable Integer id) {
        return ResponseEntity.of(bankAccountService.read(id));
    }

    @ApiOperation(value = "Updates a BankAccount", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount updated successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> update(@PathVariable Integer id, @Valid @RequestBody BankAccount bankAccount) {
        return ResponseEntity.ok(bankAccountService.update(id, bankAccount));
    }

    @ApiOperation(value = "Deletes a BankAccount with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount deleted successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccount> delete(@PathVariable Integer id) {
        bankAccountService.delete(id);
        return ResponseEntity.ok().build();
    }
}