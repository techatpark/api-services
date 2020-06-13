package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.ContractPayment;
import com.techatpark.gurukulam.eppo.service.ContractPaymentService;

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

@Api(value = "ContractPayments", description = "Resource for ContractPayments", tags = { "ContractPayments" })
@RestController
@RequestMapping("/api/contract_payments")

public class ContractPaymentAPIController {

    private final ContractPaymentService contractPaymentService;

    ContractPaymentAPIController(final ContractPaymentService contractPaymentService) {
        this.contractPaymentService = contractPaymentService;
    }

    @ApiOperation(value = "List all ContractPayments", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractPayments Listed successfully"),
            @ApiResponse(code = 400, message = "ContractPayments Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ContractPayment>> findAll() {
        return ResponseEntity.ok(contractPaymentService.list());
    }

    @ApiOperation(value = "Creates a new ContractPayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractPayment created successfully"),
            @ApiResponse(code = 400, message = "ContractPayment already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ContractPayment> create(@Valid @RequestBody ContractPayment contractPayment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractPaymentService.create(contractPayment));
    }

    @ApiOperation(value = "Finds a ContractPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractPayment with a given ID found successfully"),
            @ApiResponse(code = 400, message = "ContractPayment Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<ContractPayment> findById(@PathVariable Integer id) {
        return ResponseEntity.of(contractPaymentService.read(id));
    }

    @ApiOperation(value = "Updates a ContractPayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractPayment updated successfully"),
            @ApiResponse(code = 400, message = "ContractPayment Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<ContractPayment> update(@PathVariable Integer id,
            @Valid @RequestBody ContractPayment contractPayment) {
        return ResponseEntity.ok(contractPaymentService.update(id, contractPayment));
    }

    @ApiOperation(value = "Deletes a ContractPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractPayment deleted successfully"),
            @ApiResponse(code = 400, message = "ContractPayment Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<ContractPayment> delete(@PathVariable Integer id) {
        contractPaymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}