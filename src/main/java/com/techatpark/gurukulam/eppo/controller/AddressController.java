package com.techatpark.gurukulam.eppo.controller;

import java.util.List;
import java.util.Optional;

import com.techatpark.gurukulam.eppo.model.Address;
import com.techatpark.gurukulam.eppo.service.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class AddressController {

    private final AddressService addressService;

    AddressController(final AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "Creates a new Address", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Address created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Address create(Address newAddress) {
        return addressService.create(newAddress);
    }

    @GetMapping("/{id}")
    public Optional<Address> read(Integer id) {
        return addressService.read(id);
    }

    @PutMapping("/{id}")
    public Address update(Integer id, Address newAddress) {
        return addressService.update(id, newAddress);
    }

    @DeleteMapping("/{id}")
    public Integer delete(Integer id) {
        return addressService.delete(id);
    }

    @DeleteMapping
    public Integer delete() {
        return addressService.delete();
    }

    @GetMapping
    public List<Address> list() {
        return addressService.list();
    }

}