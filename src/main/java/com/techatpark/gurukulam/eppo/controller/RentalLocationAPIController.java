package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.RentalLocation;
import com.techatpark.gurukulam.eppo.service.RentalLocationService;

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

@Api(value = "RentalLocations", description = "Resource for RentalLocations", tags = { "RentalLocations" })
@RestController
@RequestMapping("/api/rental_location")
public class RentalLocationAPIController {

    private final RentalLocationService rentalLocationService;

    RentalLocationAPIController(final RentalLocationService rentalLocationService) {
        this.rentalLocationService = rentalLocationService;
    }

    @ApiOperation(value = "List all RentalLocations", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocations Listed successfully"),
            @ApiResponse(code = 400, message = "RentalLocations Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<RentalLocation>> findAll() {
        return ResponseEntity.ok(rentalLocationService.list());
    }

    @ApiOperation(value = "Creates a new RentalLocation", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocation created successfully"),
            @ApiResponse(code = 400, message = "RentalLocation already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RentalLocation> create(@Valid @RequestBody RentalLocation rentalLocation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalLocationService.create(rentalLocation));
    }

    @ApiOperation(value = "Finds a RentalLocation with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocation with a given ID found successfully"),
            @ApiResponse(code = 400, message = "RentalLocation Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<RentalLocation> findById(@PathVariable Integer id) {
        return ResponseEntity.of(rentalLocationService.read(id));
    }

    @ApiOperation(value = "Updates a RentalLocation", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocation updated successfully"),
            @ApiResponse(code = 400, message = "RentalLocation Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<RentalLocation> update(@PathVariable Integer id,
            @Valid @RequestBody RentalLocation rentalLocation) {
        return ResponseEntity.ok(rentalLocationService.update(id, rentalLocation));
    }

    @ApiOperation(value = "Deletes a RentalLocation with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocation deleted successfully"),
            @ApiResponse(code = 400, message = "RentalLocation Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<RentalLocation> delete(@PathVariable Integer id) {
        rentalLocationService.delete(id);
        return ResponseEntity.ok().build();
    }
}