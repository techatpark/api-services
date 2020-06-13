package com.techatpark.gurukulam.eppo.controller;

import java.util.List;
import java.util.Optional;

import com.techatpark.gurukulam.eppo.model.AdminUser;
import com.techatpark.gurukulam.eppo.service.AdminUserService;

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

@Api(value = "AdminUsers", description = "Resource for AdminUsers", tags = { "AdminUsers" })
@RestController
@RequestMapping("/api/adminUsers")
public class AdminUserController {

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    private final AdminUserService adminUserService;

    @ApiOperation(value = "Creates a new Admin_user", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Admin_user created successfully"),
            @ApiResponse(code = 400, message = "Role name already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdminUser create(AdminUser newAdminUser) {
        return adminUserService.create(newAdminUser);
    }

    @GetMapping("/{id}")
    public Optional<AdminUser> read(Integer id) {
        return adminUserService.read(id);
    }

    @PutMapping("{id}")
    public AdminUser update(Integer id, AdminUser newAdminUser) {
        return adminUserService.update(id, newAdminUser);
    }

    @DeleteMapping("{id}")
    public Integer delete(Integer id) {
        return adminUserService.delete(id);
    }

    @GetMapping
    public List<AdminUser> list() {
        return adminUserService.list();
    }

}