package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.RentalLocationUnit;
import com.techatpark.gurukulam.eppo.service.RentalLocationUnitService;

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

@Api(value = "RentalLocationUnits", description = "Resource for RentalLocationUnits", tags = { "RentalLocationUnits" })
@RestController
@RequestMapping("/api/rental_loction_unit")
public class RentalLocationUnitAPIController {

    private final RentalLocationUnitService rentalLocationUnitService;

    RentalLocationUnitAPIController(final RentalLocationUnitService rentalLocationUnitService) {
        this.rentalLocationUnitService = rentalLocationUnitService;
    }

    @ApiOperation(value = "List all RentalLocationUnits", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationUnits Listed successfully"),
            @ApiResponse(code = 400, message = "RentalLocationUnits Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<RentalLocationUnit>> findAll() {
        return ResponseEntity.ok(rentalLocationUnitService.list());
    }

    @ApiOperation(value = "Creates a new RentalLocationUnit", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationUnit created successfully"),
            @ApiResponse(code = 400, message = "RentalLocationUnit already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RentalLocationUnit> create(@Valid @RequestBody RentalLocationUnit rentalLocationUnit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalLocationUnitService.create(rentalLocationUnit));
    }

    @ApiOperation(value = "Finds a RentalLocationUnit with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationUnit with a given ID found successfully"),
            @ApiResponse(code = 400, message = "RentalLocationUnit Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<RentalLocationUnit> findById(@PathVariable Integer id) {
        return ResponseEntity.of(rentalLocationUnitService.read(id));
    }

    @ApiOperation(value = "Updates a RentalLocationUnit", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationUnit updated successfully"),
            @ApiResponse(code = 400, message = "RentalLocationUnit Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<RentalLocationUnit> update(@PathVariable Integer id,
            @Valid @RequestBody RentalLocationUnit rentalLocationUnit) {
        return ResponseEntity.ok(rentalLocationUnitService.update(id, rentalLocationUnit));
    }

    @ApiOperation(value = "Deletes a RentalLocationUnit with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationUnit deleted successfully"),
            @ApiResponse(code = 400, message = "RentalLocationUnit Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<RentalLocationUnit> delete(@PathVariable Integer id) {
        rentalLocationUnitService.delete(id);
        return ResponseEntity.ok().build();
    }

}