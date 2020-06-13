package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountCode;
import com.techatpark.gurukulam.eppo.service.AccountCodeService;

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

@Api(value = "AccountCodes", description = "Resource for AccountCodes", tags = { "AccountCodes" })
@RestController
@RequestMapping("/api/account_codes")
public class AccountCodeAPIController {

    private final AccountCodeService accountCodeService;

    AccountCodeAPIController(final AccountCodeService accountCodeService) {
        this.accountCodeService = accountCodeService;
    }

    @ApiOperation(value = "List all AccountCodes", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCodes Listed successfully"),
            @ApiResponse(code = 400, message = "AccountCodes Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AccountCode>> findAll() {
        return ResponseEntity.ok(accountCodeService.list());
    }

    @ApiOperation(value = "Creates a new AccountCode", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCode created successfully"),
            @ApiResponse(code = 400, message = "AccountCode already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountCode> create(@Valid @RequestBody AccountCode accountCode) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountCodeService.create(accountCode));
    }

    @ApiOperation(value = "Finds a AccountCode with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCode with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AccountCode Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AccountCode> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountCodeService.read(id));
    }

    @ApiOperation(value = "Updates a AccountCode", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCode updated successfully"),
            @ApiResponse(code = 400, message = "AccountCode Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AccountCode> update(@PathVariable Integer id, @Valid @RequestBody AccountCode accountCode) {
        return ResponseEntity.ok(accountCodeService.update(id, accountCode));
    }

    @ApiOperation(value = "Deletes a AccountCode with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCode deleted successfully"),
            @ApiResponse(code = 400, message = "AccountCode Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountCode> delete(@PathVariable Integer id) {
        accountCodeService.delete(id);
        return ResponseEntity.ok().build();
    }
}