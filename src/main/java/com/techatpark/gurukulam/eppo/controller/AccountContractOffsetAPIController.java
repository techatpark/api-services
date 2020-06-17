package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountContractOffset;
import com.techatpark.gurukulam.eppo.service.AccountContractOffsetService;

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

@Api(value = "AccountContractOffsets", description = "Resource for AccountContractOffsets", tags = {
        "AccountContractOffsets" })
@RestController
@RequestMapping("/api/account_contract_offsets")

public class AccountContractOffsetAPIController {

    private final AccountContractOffsetService accountContractOffsetService;

    AccountContractOffsetAPIController(final AccountContractOffsetService accountContractOffsetService) {
        this.accountContractOffsetService = accountContractOffsetService;
    }

    @ApiOperation(value = "List all AccountContractOffsets", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountContractOffsets Listed successfully"),
            @ApiResponse(code = 400, message = "AccountContractOffsets Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AccountContractOffset>> findAll() {
        return ResponseEntity.ok(accountContractOffsetService.list());
    }

    @ApiOperation(value = "Creates a new AccountContractOffsets", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountContractOffsets created successfully"),
            @ApiResponse(code = 400, message = "AccountContractOffsets already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountContractOffset> create(
            @Valid @RequestBody AccountContractOffset accountContractOffset) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountContractOffsetService.create(accountContractOffset));
    }

    @ApiOperation(value = "Finds a AccountContractOffsets with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "AccountContractOffsets with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AccountContractOffsets Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AccountContractOffset> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountContractOffsetService.read(id));
    }

    @ApiOperation(value = "Updates a AccountContractOffsets", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountContractOffsets updated successfully"),
            @ApiResponse(code = 400, message = "AccountContractOffsets Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AccountContractOffset> update(@PathVariable Integer id,
            @Valid @RequestBody AccountContractOffset accountContractOffset) {
        return ResponseEntity.ok(accountContractOffsetService.update(id, accountContractOffset));
    }

    @ApiOperation(value = "Deletes a AccountContractOffsets with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountContractOffsets deleted successfully"),
            @ApiResponse(code = 400, message = "AccountContractOffsets Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountContractOffset> delete(@PathVariable Integer id) {
        accountContractOffsetService.delete(id);
        return ResponseEntity.ok().build();
    }

}