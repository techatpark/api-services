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

    @ApiOperation(value = "List all AccountConfigs", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountConfigs Listed successfully"),
            @ApiResponse(code = 400, message = "AccountConfigs Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AccountConfig>> findAll() {
        return ResponseEntity.ok(accountConfigService.list());
    }

    @ApiOperation(value = "Creates a new AccountConfigs", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountConfigs created successfully"),
            @ApiResponse(code = 400, message = "AccountConfigs already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountConfig> create(@Valid @RequestBody AccountConfig accountConfig) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountConfigService.create(accountConfig));
    }

    @ApiOperation(value = "Finds a AccountConfig with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountConfig with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AccountConfig Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AccountConfig> findById(@PathVariable Integer id) {
        return ResponseEntity.of(accountConfigService.read(id));
    }

    @ApiOperation(value = "Updates a AccountConfig", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountConfig updated successfully"),
            @ApiResponse(code = 400, message = "AccountConfig Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AccountConfig> update(@PathVariable Integer id,
            @Valid @RequestBody AccountConfig accountConfig) {
        return ResponseEntity.ok(accountConfigService.update(id, accountConfig));
    }

    @ApiOperation(value = "Deletes a AccountConfig with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AccountConfig deleted successfully"),
            @ApiResponse(code = 400, message = "AccountConfig Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountConfig> delete(@PathVariable Integer id) {
        accountConfigService.delete(id);
        return ResponseEntity.ok().build();
    }

}