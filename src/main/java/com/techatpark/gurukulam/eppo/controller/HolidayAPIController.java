package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Holiday;
import com.techatpark.gurukulam.eppo.service.HolidayService;

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

@Api(value = "Holidays", description = "Resource for Holidays", tags = { "Holidays" })
@RestController
@RequestMapping("/api/holidays")
public class HolidayAPIController {

    private final HolidayService holidayService;

    HolidayAPIController(final HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @ApiOperation(value = "List all Holidays", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Holidays Listed successfully"),
            @ApiResponse(code = 400, message = "Holidays Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Holiday>> findAll() {
        return ResponseEntity.ok(holidayService.list());
    }

    @ApiOperation(value = "Creates a new Holiday", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Holiday created successfully"),
            @ApiResponse(code = 400, message = "Holiday already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Holiday> create(@Valid @RequestBody Holiday holiday) {
        return ResponseEntity.status(HttpStatus.CREATED).body(holidayService.create(holiday));
    }

    @ApiOperation(value = "Finds a Holiday with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Holiday with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Holiday Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Holiday> findById(@PathVariable Integer id) {
        return ResponseEntity.of(holidayService.read(id));
    }

    @ApiOperation(value = "Updates a Holiday", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Holiday updated successfully"),
            @ApiResponse(code = 400, message = "Holiday Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Holiday> update(@PathVariable Integer id, @Valid @RequestBody Holiday holiday) {
        return ResponseEntity.ok(holidayService.update(id, holiday));
    }

    @ApiOperation(value = "Deletes a Holiday with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Holiday deleted successfully"),
            @ApiResponse(code = 400, message = "Holiday Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Holiday> delete(@PathVariable Integer id) {
        holidayService.delete(id);
        return ResponseEntity.ok().build();
    }
}