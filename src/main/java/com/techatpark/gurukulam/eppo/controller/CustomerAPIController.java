package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Customer;
import com.techatpark.gurukulam.eppo.service.CustomerService;

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

@Api(value = "Customers", description = "Resource for Customers", tags = { "Customers" })
@RestController
@RequestMapping("/api/customers")

public class CustomerAPIController {

    private final CustomerService customerService;

    CustomerAPIController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "List all Customers", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Customers Listed successfully"),
            @ApiResponse(code = 400, message = "Customers Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.list());
    }

    @ApiOperation(value = "Creates a new Customer", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Customer created successfully"),
            @ApiResponse(code = 400, message = "Customer already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }

    @ApiOperation(value = "Finds a Customer with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Customer with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Customer Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id) {
        return ResponseEntity.of(customerService.read(id));
    }

    @ApiOperation(value = "Updates a Customer", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Customer updated successfully"),
            @ApiResponse(code = 400, message = "Customer Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Integer id, @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

    @ApiOperation(value = "Deletes a Customer with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Customer deleted successfully"),
            @ApiResponse(code = 400, message = "Customer Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}