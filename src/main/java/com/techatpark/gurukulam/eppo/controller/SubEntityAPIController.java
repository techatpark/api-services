package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.SubEntity;
import com.techatpark.gurukulam.eppo.service.SubEntityService;

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

@Api(value = "SubEntities", description = "Resource for SubEntities", tags = { "SubEntities" })
@RestController
@RequestMapping("/api/sub_entities")
public class SubEntityAPIController {

    private final SubEntityService subEntityService;

    SubEntityAPIController(final SubEntityService subEntityService) {
        this.subEntityService = subEntityService;
    }

    @ApiOperation(value = "List all SubEntities", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntities Listed successfully"),
            @ApiResponse(code = 400, message = "SubEntities Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<SubEntity>> findAll() {
        return ResponseEntity.ok(subEntityService.list());
    }

    @ApiOperation(value = "Creates a new subEntity", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "subEntity created successfully"),
            @ApiResponse(code = 400, message = "SubEntity already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<SubEntity> create(@Valid @RequestBody SubEntity subEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subEntityService.create(subEntity));
    }

    @ApiOperation(value = "Finds a SubEntity with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntity with a given ID found successfully"),
            @ApiResponse(code = 400, message = "SubEntity Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<SubEntity> findById(@PathVariable Integer id) {
        return ResponseEntity.of(subEntityService.read(id));
    }

    @ApiOperation(value = "Updates a SubEntity", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntity updated successfully"),
            @ApiResponse(code = 400, message = "SubEntity Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<SubEntity> update(@PathVariable Integer id, @Valid @RequestBody SubEntity subEntity) {
        return ResponseEntity.ok(subEntityService.update(id, subEntity));
    }

    @ApiOperation(value = "Deletes a SubEntity with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "SubEntity deleted successfully"),
            @ApiResponse(code = 400, message = "SubEntity Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<SubEntity> delete(@PathVariable Integer id) {
        subEntityService.delete(id);
        return ResponseEntity.ok().build();
    }
}