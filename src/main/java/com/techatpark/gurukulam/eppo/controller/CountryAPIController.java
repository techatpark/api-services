package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Country;
import com.techatpark.gurukulam.eppo.service.CountryService;

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

@Api(value = "Countries", description = "Resource for Countries", tags = { "Countries" })
@RestController
@RequestMapping("/api/countries")
public class CountryAPIController {

    private final CountryService countryService;

    CountryAPIController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "List all Countries", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Countries Listed successfully"),
            @ApiResponse(code = 400, message = "Countries Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryService.list());
    }

    @ApiOperation(value = "Creates a new Country", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Country created successfully"),
            @ApiResponse(code = 400, message = "Country already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Country> create(@Valid @RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(country));
    }

    @ApiOperation(value = "Finds a Country with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Country with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Country Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Integer id) {
        return ResponseEntity.of(countryService.read(id));
    }

    @ApiOperation(value = "Updates a Country", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Country updated successfully"),
            @ApiResponse(code = 400, message = "Country Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable Integer id, @Valid @RequestBody Country country) {
        return ResponseEntity.ok(countryService.update(id, country));
    }

    @ApiOperation(value = "Deletes a Country with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Country deleted successfully"),
            @ApiResponse(code = 400, message = "Country Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Country> delete(@PathVariable Integer id) {
        countryService.delete(id);
        return ResponseEntity.ok().build();
    }
}