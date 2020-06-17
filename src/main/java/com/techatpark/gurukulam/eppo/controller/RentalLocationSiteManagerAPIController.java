package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.RentalLocationSiteManager;
import com.techatpark.gurukulam.eppo.service.RentalLocationSiteManagerService;

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

@Api(value = "RentalLocationSiteManagers", description = "Resource for RentalLocationSiteManagers", tags = {
        "RentalLocationSiteManagers" })
@RestController
@RequestMapping("/api/rental_location_site_manager")

public class RentalLocationSiteManagerAPIController {

    private final RentalLocationSiteManagerService rentalLocationSiteManagerService;

    RentalLocationSiteManagerAPIController(final RentalLocationSiteManagerService rentalLocationSiteManagerService) {
        this.rentalLocationSiteManagerService = rentalLocationSiteManagerService;
    }

    @ApiOperation(value = "List all RentalLocationSiteManagers", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationSiteManagers Listed successfully"),
            @ApiResponse(code = 400, message = "RentalLocationSiteManagers Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<RentalLocationSiteManager>> findAll() {
        return ResponseEntity.ok(rentalLocationSiteManagerService.list());
    }

    @ApiOperation(value = "Creates a new RentalLocationSiteManager", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationSiteManager created successfully"),
            @ApiResponse(code = 400, message = "RentalLocationSiteManager already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RentalLocationSiteManager> create(
            @Valid @RequestBody RentalLocationSiteManager rentalLocationSiteManager) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rentalLocationSiteManagerService.create(rentalLocationSiteManager));
    }

    @ApiOperation(value = "Finds a RentalLocationSiteManager with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "RentalLocationSiteManager with a given ID found successfully"),
            @ApiResponse(code = 400, message = "RentalLocationSiteManager Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<RentalLocationSiteManager> findById(@PathVariable Integer id) {
        return ResponseEntity.of(rentalLocationSiteManagerService.read(id));
    }

    @ApiOperation(value = "Updates a RentalLocationSiteManager", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationSiteManager updated successfully"),
            @ApiResponse(code = 400, message = "RentalLocationSiteManager Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<RentalLocationSiteManager> update(@PathVariable Integer id,
            @Valid @RequestBody RentalLocationSiteManager rentalLocationSiteManager) {
        return ResponseEntity.ok(rentalLocationSiteManagerService.update(id, rentalLocationSiteManager));
    }

    @ApiOperation(value = "Deletes a RentalLocationSiteManager with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "RentalLocationSiteManager deleted successfully"),
            @ApiResponse(code = 400, message = "RentalLocationSiteManager Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<RentalLocationSiteManager> delete(@PathVariable Integer id) {
        rentalLocationSiteManagerService.delete(id);
        return ResponseEntity.ok().build();
    }
}