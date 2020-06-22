package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.GlobalPayment;
import com.techatpark.gurukulam.eppo.service.GlobalPaymentService;

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

@Api(value = "GlobalPayment", description = "Resource for GlobalPayment", tags = { "GlobalPayment" })
@RestController
@RequestMapping("/api/global_payment")
public class GlobalPaymentAPIController {

    private final GlobalPaymentService globalPaymentService;

    GlobalPaymentAPIController(final GlobalPaymentService globalPaymentService) {
        this.globalPaymentService = globalPaymentService;
    }

    @ApiOperation(value = "List all GlobalPayments", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "GlobalPayments Listed successfully"),
            @ApiResponse(code = 400, message = "GlobalPayments Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<GlobalPayment>> findAll() {
        return ResponseEntity.ok(globalPaymentService.list());
    }

    @ApiOperation(value = "Creates a new globalpayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "GlobalPayment created successfully"),
            @ApiResponse(code = 400, message = "GlobalPayment already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<GlobalPayment> create(@Valid @RequestBody GlobalPayment globalPayment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(globalPaymentService.create(globalPayment));
    }

    @ApiOperation(value = "Finds a GlobalPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "GlobalPayment with a given ID found successfully"),
            @ApiResponse(code = 400, message = "GlobalPayment Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<GlobalPayment> findById(@PathVariable Integer id) {
        return ResponseEntity.of(globalPaymentService.read(id));
    }

    @ApiOperation(value = "Updates a GlobalPayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "GlobalPayment updated successfully"),
            @ApiResponse(code = 400, message = "GlobalPayment Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<GlobalPayment> update(@PathVariable Integer id,
            @Valid @RequestBody GlobalPayment globalPayment) {
        return ResponseEntity.ok(globalPaymentService.update(id, globalPayment));
    }

    @ApiOperation(value = "Deletes a GlobalPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "GlobalPayment deleted successfully"),
            @ApiResponse(code = 400, message = "GlobalPayment Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalPayment> delete(@PathVariable Integer id) {
        globalPaymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}