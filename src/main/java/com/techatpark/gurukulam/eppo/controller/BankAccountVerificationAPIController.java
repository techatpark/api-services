package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.BankAccountVerification;
import com.techatpark.gurukulam.eppo.service.BankAccountVerificationService;

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

@Api(value = "BankAccountVerifications", description = "Resource for BankAccountVerifications", tags = {
        "BankAccountVerifications" })
@RestController
@RequestMapping("/api/bank_account_verifications")

public class BankAccountVerificationAPIController {

    private final BankAccountVerificationService bankAccountVerificationService;

    BankAccountVerificationAPIController(final BankAccountVerificationService bankAccountVerificationService) {
        this.bankAccountVerificationService = bankAccountVerificationService;
    }

    @ApiOperation(value = "List all BankAccountVerifications", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccountVerifications Listed successfully"),
            @ApiResponse(code = 400, message = "BankAccountVerifications Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<BankAccountVerification>> findAll() {
        return ResponseEntity.ok(bankAccountVerificationService.list());
    }

    @ApiOperation(value = "Creates a new BankAccountVerifications", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount created successfully"),
            @ApiResponse(code = 400, message = "BankAccount already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<BankAccountVerification> create(
            @Valid @RequestBody BankAccountVerification bankAccountVerification) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankAccountVerificationService.create(bankAccountVerification));
    }

    @ApiOperation(value = "Finds a BankAccount with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount with a given ID found successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<BankAccountVerification> findById(@PathVariable Integer id) {
        return ResponseEntity.of(bankAccountVerificationService.read(id));
    }

    @ApiOperation(value = "Updates a BankAccount", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccountVerification updated successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<BankAccountVerification> update(@PathVariable Integer id,
            @Valid @RequestBody BankAccountVerification bankAccountVerification) {
        return ResponseEntity.ok(bankAccountVerificationService.update(id, bankAccountVerification));
    }

    @ApiOperation(value = "Deletes a BankAccount with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount deleted successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccountVerification> delete(@PathVariable Integer id) {
        bankAccountVerificationService.delete(id);
        return ResponseEntity.ok().build();
    }
}