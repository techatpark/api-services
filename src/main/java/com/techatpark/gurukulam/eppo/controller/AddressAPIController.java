package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Address;
import com.techatpark.gurukulam.eppo.service.AddressService;

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

@Api(value = "Address", description = "Resource for Address", tags = { "Address" })
@RestController
@RequestMapping("/api/address")
public class AddressAPIController {

    private final AddressService addressService;

    AddressAPIController(final AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "List all Addresses", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Addresses Listed successfully"),
            @ApiResponse(code = 400, message = "Addresses Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok(addressService.list());
    }

    @ApiOperation(value = "Creates a new Address", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address created successfully"),
            @ApiResponse(code = 400, message = "Address already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Address> create(@Valid @RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(address));
    }

    @ApiOperation(value = "Finds a Address with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Address Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Integer id) {
        return ResponseEntity.of(addressService.read(id));
    }

    @ApiOperation(value = "Updates a Address", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address updated successfully"),
            @ApiResponse(code = 400, message = "Address Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Integer id, @Valid @RequestBody Address address) {
        return ResponseEntity.ok(addressService.update(id, address));
    }

    @ApiOperation(value = "Deletes a Address with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address deleted successfully"),
            @ApiResponse(code = 400, message = "Address Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public Integer delete() {
        return addressService.delete();
    }
}