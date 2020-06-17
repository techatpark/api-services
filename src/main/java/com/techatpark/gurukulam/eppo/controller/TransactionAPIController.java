package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Transaction;
import com.techatpark.gurukulam.eppo.service.TransactionService;

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

@Api(value = "Transactions", description = "Resource for Transactions", tags = { "Transactions" })
@RestController
@RequestMapping("/api/transactions")
public class TransactionAPIController {

    private final TransactionService transactionService;

    TransactionAPIController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "List all Transactions", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Transactions Listed successfully"),
            @ApiResponse(code = 400, message = "Transactions Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(transactionService.list());
    }

    @ApiOperation(value = "Creates a new Transaction", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction created successfully"),
            @ApiResponse(code = 400, message = "Transaction already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.create(transaction));
    }

    @ApiOperation(value = "Finds a Transaction with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Transaction Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Integer id) {
        return ResponseEntity.of(transactionService.read(id));
    }

    @ApiOperation(value = "Updates a Transactions", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction updated successfully"),
            @ApiResponse(code = 400, message = "Transactions Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Integer id, @Valid @RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.update(id, transaction));
    }

    @ApiOperation(value = "Deletes a Transaction with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Transaction deleted successfully"),
            @ApiResponse(code = 400, message = "Transaction Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> delete(@PathVariable Integer id) {
        transactionService.delete(id);
        return ResponseEntity.ok().build();
    }
}