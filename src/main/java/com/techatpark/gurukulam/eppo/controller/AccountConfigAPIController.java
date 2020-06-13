package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountConfig;
import com.techatpark.gurukulam.eppo.service.AccountConfigService;

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

@Api(value = "AccountConfigs", description = "Resource for AccountConfigs", tags = { "AccountConfigs" })
@RestController
@RequestMapping("/api/account_configs")
public class AccountConfigAPIController {

    private final AccountConfigService accountConfigService;

    AccountConfigAPIController(final AccountConfigService accountConfigService) {
        this.accountConfigService = accountConfigService;
    }

    @GetMapping
    public ResponseEntity<List<AccountConfig>> findAll() {
        return ResponseEntity.ok(accountConfigService.list());
    }

    @ApiOperation(value = "Creates a new accountconfig", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "accountconfig created successfully"),
            @ApiResponse(code = 400, message = "Accountconfig already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountConfig> create(@Valid @RequestBody AccountConfig accountConfig) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountConfigService.create(accountConfig));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountConfig> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountConfigService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountConfig> update(@PathVariable Integer id,
            @Valid @RequestBody AccountConfig accountConfig) {
        return ResponseEntity.ok(accountConfigService.update(id, accountConfig));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountConfig> delete(@PathVariable Integer id) {
        accountConfigService.delete(id);
        return ResponseEntity.ok().build();
    }

}