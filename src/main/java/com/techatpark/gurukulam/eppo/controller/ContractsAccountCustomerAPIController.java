package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.ContractsAccountCustomer;
import com.techatpark.gurukulam.eppo.service.ContractsAccountCustomerService;

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

@Api(value = "ContractsAccountCustomers", description = "Resource for ContractsAccountCustomers", tags = {
        "ContractsAccountCustomers" })
@RestController
@RequestMapping("/api/contracts_account_customers")

public class ContractsAccountCustomerAPIController {

    private final ContractsAccountCustomerService contractsAccountCustomerService;

    ContractsAccountCustomerAPIController(final ContractsAccountCustomerService contractsAccountCustomerService) {
        this.contractsAccountCustomerService = contractsAccountCustomerService;
    }

    @ApiOperation(value = "List all ContractsAccountCustomers", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractsAccountCustomers Listed successfully"),
            @ApiResponse(code = 400, message = "ContractsAccountCustomers Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<ContractsAccountCustomer>> findAll() {
        return ResponseEntity.ok(contractsAccountCustomerService.list());
    }

    @ApiOperation(value = "Creates a new ContractsAccountCustomer", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractsAccountCustomer created successfully"),
            @ApiResponse(code = 400, message = "ContractsAccountCustomer already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ContractsAccountCustomer> create(
            @Valid @RequestBody ContractsAccountCustomer contractsAccountCustomer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contractsAccountCustomerService.create(contractsAccountCustomer));
    }

    @ApiOperation(value = "Finds a ContractsAccountCustomer with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "ContractsAccountCustomer with a given ID found successfully"),
            @ApiResponse(code = 400, message = "ContractsAccountCustomer Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<ContractsAccountCustomer> findById(@PathVariable Integer id) {
        return ResponseEntity.of(contractsAccountCustomerService.read(id));
    }

    @ApiOperation(value = "Updates a ContractsAccountCustomer", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractsAccountCustomer updated successfully"),
            @ApiResponse(code = 400, message = "ContractsAccountCustomer Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<ContractsAccountCustomer> update(@PathVariable Integer id,
            @Valid @RequestBody ContractsAccountCustomer contractsAccountCustomer) {
        return ResponseEntity.ok(contractsAccountCustomerService.update(id, contractsAccountCustomer));
    }

    @ApiOperation(value = "Deletes a ContractsAccountCustomer with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "ContractsAccountCustomer deleted successfully"),
            @ApiResponse(code = 400, message = "ContractsAccountCustomer Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<ContractsAccountCustomer> delete(@PathVariable Integer id) {
        contractsAccountCustomerService.delete(id);
        return ResponseEntity.ok().build();
    }
}