package com.techatpark.gurukulam.eppo.controller;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountCodes;
import com.techatpark.gurukulam.eppo.service.AccountCodesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Account Codes", description = "Resource for Account Codes", tags = { "Account Codes" })
@RestController
@RequestMapping("/api/exams/sql")
public class AccountCodesAPIController {

    private final AccountCodesService accountCodesService;

    public AccountCodesAPIController(AccountCodesService accountCodesService) {
        this.accountCodesService = accountCodesService;
    }

    @ApiOperation(value = "Creates a new accountcodes", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "accountcodes created successfully"),
            @ApiResponse(code = 400, message = "accountcodes is invalid") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AccountCodes> create(@Valid @RequestBody AccountCodes accountCodes) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountCodesService.create(accountCodes));
    }

    @ApiOperation(value = "Deletes accountcodes by given id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "accountcodes deleted successfully"),
                    @ApiResponse(code = 404, message = "accountcodes not found") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamById(@PathVariable Integer id) {
            return AccountCodesService.delete(id) ? ResponseEntity.ok().build()
                            : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


}
