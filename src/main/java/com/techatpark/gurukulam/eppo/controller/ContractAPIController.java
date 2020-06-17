package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Contract;
import com.techatpark.gurukulam.eppo.service.ContractService;

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

@Api(value = "Contracts", description = "Resource for Contracts", tags = { "Contracts" })
@RestController
@RequestMapping("/api/contracts")
public class ContractAPIController {

    private final ContractService contractService;

    ContractAPIController(final ContractService contractService) {
        this.contractService = contractService;
    }

    @ApiOperation(value = "List all Contracts", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Contracts Listed successfully"),
            @ApiResponse(code = 400, message = "Contracts Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Contract>> findAll() {
        return ResponseEntity.ok(contractService.list());
    }

    @ApiOperation(value = "Creates a new Contract", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Contract created successfully"),
            @ApiResponse(code = 400, message = "Contract already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Contract> create(@Valid @RequestBody Contract contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.create(contract));
    }

    @ApiOperation(value = "Finds a Contract with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Contract with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Contract Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Contract> findById(@PathVariable Integer id) {
        return ResponseEntity.of(contractService.read(id));
    }

    @ApiOperation(value = "Updates a Contract", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Contract updated successfully"),
            @ApiResponse(code = 400, message = "Contract Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Contract> update(@PathVariable Integer id, @Valid @RequestBody Contract contract) {
        return ResponseEntity.ok(contractService.update(id, contract));
    }

    @ApiOperation(value = "Deletes a Contract with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Contract deleted successfully"),
            @ApiResponse(code = 400, message = "Contract Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Contract> delete(@PathVariable Integer id) {
        contractService.delete(id);
        return ResponseEntity.ok().build();
    }
}