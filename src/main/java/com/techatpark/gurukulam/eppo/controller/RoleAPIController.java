package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Role;
import com.techatpark.gurukulam.eppo.service.RoleService;

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

@Api(value = "Roles", description = "Resource for Roles", tags = { "Roles" })
@RestController
@RequestMapping("/api/roles")
public class RoleAPIController {

    private final RoleService roleService;

    RoleAPIController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "List all Roles", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Roles Listed successfully"),
            @ApiResponse(code = 400, message = "Roles Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(roleService.list());
    }

    @ApiOperation(value = "Creates a new Role", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Role created successfully"),
            @ApiResponse(code = 400, message = "Role already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(role));
    }

    @ApiOperation(value = "Finds a Role with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Role with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Role Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Integer id) {
        return ResponseEntity.of(roleService.read(id));
    }

    @ApiOperation(value = "Updates a Role", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Role updated successfully"),
            @ApiResponse(code = 400, message = "Role Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Integer id, @Valid @RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(id, role));
    }

    @ApiOperation(value = "Deletes a Role with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Role deleted successfully"),
            @ApiResponse(code = 400, message = "Role Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}