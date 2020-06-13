package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountCustomer;
import com.techatpark.gurukulam.eppo.service.AccountCustomerService;

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

@Api(value = "AccountCustomers", description = "Resource for AccountCustomers", tags = { "AccountCustomers" })
@RestController
@RequestMapping("/api/account_customer")

public class AccountCustomerAPIController {

    private final AccountCustomerService accountCustomerService;

    AccountCustomerAPIController(final AccountCustomerService accountCustomerService) {
        this.accountCustomerService = accountCustomerService;
    }

    @GetMapping
    public ResponseEntity<List<AccountCustomer>> findAll() {
        return ResponseEntity.ok(accountCustomerService.list());
    }

    @ApiOperation(value = "Creates a new AccountCustomer", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountCustomer created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountCustomer> create(@Valid @RequestBody AccountCustomer accountCustomer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountCustomerService.create(accountCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountCustomer> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountCustomerService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountCustomer> update(@PathVariable Integer id,
            @Valid @RequestBody AccountCustomer accountCustomer) {
        return ResponseEntity.ok(accountCustomerService.update(id, accountCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountCustomer> delete(@PathVariable Integer id) {
        accountCustomerService.delete(id);
        return ResponseEntity.ok().build();
    }
}