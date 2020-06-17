package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AdminUser;
import com.techatpark.gurukulam.eppo.service.AdminUserService;

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

@Api(value = "AdminUsers", description = "Resource for AdminUsers", tags = { "AdminUsers" })
@RestController
@RequestMapping("/api/admin_users")
public class AdminUserAPIController {

    public AdminUserAPIController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    private final AdminUserService adminUserService;

    @ApiOperation(value = "List all AdminUsers", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AdminUsers Listed successfully"),
            @ApiResponse(code = 400, message = "AdminUsers Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<AdminUser>> findAll() {
        return ResponseEntity.ok(adminUserService.list());
    }

    @ApiOperation(value = "Creates a new AdminUser", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AdminUser created successfully"),
            @ApiResponse(code = 400, message = "AdminUser already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AdminUser> create(@Valid @RequestBody AdminUser adminUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminUserService.create(adminUser));
    }

    @ApiOperation(value = "Finds a AdminUser with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AdminUser with a given ID found successfully"),
            @ApiResponse(code = 400, message = "AdminUser Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<AdminUser> findById(@PathVariable Integer id) {
        return ResponseEntity.of(adminUserService.read(id));
    }

    @ApiOperation(value = "Updates a AdminUser", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "AdminUser updated successfully"),
            @ApiResponse(code = 400, message = "AdminUser Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<AdminUser> update(@PathVariable Integer id, @Valid @RequestBody AdminUser adminUser) {
        return ResponseEntity.ok(adminUserService.update(id, adminUser));
    }

    @ApiOperation(value = "Deletes a BankAccount with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "BankAccount deleted successfully"),
            @ApiResponse(code = 400, message = "BankAccount Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<AdminUser> delete(@PathVariable Integer id) {
        adminUserService.delete(id);
        return ResponseEntity.ok().build();
    }
}