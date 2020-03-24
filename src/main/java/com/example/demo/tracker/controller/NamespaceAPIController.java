package com.example.demo.tracker.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.tracker.model.Namespace;
import com.example.demo.tracker.service.NamespaceService;

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

@Api(value = "Namespaces", description = "REST API for Namespaces", tags = { "Namespaces" })
@RestController
@RequestMapping("/api/namespaces")
public class NamespaceAPIController {

    private final NamespaceService namespaceService;

    NamespaceAPIController(final NamespaceService namespaceService) {
        this.namespaceService = namespaceService;
    }

    @ApiOperation(value = "lists all the namespace", notes = "Can be Invoked by auth users only")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Listing all the namespaces"),
            @ApiResponse(code = 404, message = "Requested page Not found") })
    @GetMapping
    public ResponseEntity<List<Namespace>> findAll() {
        return ResponseEntity.ok(namespaceService.list());
    }

    @ApiOperation(value = "Creates a new namespace", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "namespace created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Namespace> create(@Valid @RequestBody Namespace namespace) {
        return ResponseEntity.status(HttpStatus.CREATED).body(namespaceService.create(namespace));
    }

    @ApiOperation(value = "Get namespace with given id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "namespace"),
            @ApiResponse(code = 404, message = "namespace not found") })
    @GetMapping("/{id}")
    public ResponseEntity<Namespace> findById(@PathVariable Integer id) {
        return ResponseEntity.of(namespaceService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Namespace> update(@PathVariable Integer id, @Valid @RequestBody Namespace namespace) {
        return ResponseEntity.ok(namespaceService.update(id, namespace));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        namespaceService.delete(id);
        return ResponseEntity.ok().build();
    }
}