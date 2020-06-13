package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.CustomerPayment;
import com.techatpark.gurukulam.eppo.service.CustomerPaymentService;

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

@Api(value = "CustomerPayment", description = "Resource for CustomerPayment", tags = { "CustomerPayment" })
@RestController
@RequestMapping("/api/customer_payment")
public class CustomerPaymentAPIController {

    private final CustomerPaymentService customerPaymentService;

    CustomerPaymentAPIController(final CustomerPaymentService customerPaymentService) {
        this.customerPaymentService = customerPaymentService;
    }

    @ApiOperation(value = "List all CustomerPayments", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "CustomerPayments Listed successfully"),
            @ApiResponse(code = 400, message = "CustomerPayments Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<CustomerPayment>> findAll() {
        return ResponseEntity.ok(customerPaymentService.list());
    }

    @ApiOperation(value = "Creates a new CustomerPayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "CustomerPayment created successfully"),
            @ApiResponse(code = 400, message = "CustomerPayment already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CustomerPayment> create(@Valid @RequestBody CustomerPayment customerPayment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerPaymentService.create(customerPayment));
    }

    @ApiOperation(value = "Finds a CustomerPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "CustomerPayment with a given ID found successfully"),
            @ApiResponse(code = 400, message = "CustomerPayment Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<CustomerPayment> findById(@PathVariable Integer id) {
        return ResponseEntity.of(customerPaymentService.read(id));
    }

    @ApiOperation(value = "Updates a CustomerPayment", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "CustomerPayment updated successfully"),
            @ApiResponse(code = 400, message = "CustomerPayment Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<CustomerPayment> update(@PathVariable Integer id,
            @Valid @RequestBody CustomerPayment customerPayment) {
        return ResponseEntity.ok(customerPaymentService.update(id, customerPayment));
    }

    @ApiOperation(value = "Deletes a CustomerPayment with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "CustomerPayment deleted successfully"),
            @ApiResponse(code = 400, message = "CustomerPayment Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerPayment> delete(@PathVariable Integer id) {
        customerPaymentService.delete(id);
        return ResponseEntity.ok().build();
    }
}