package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.MerchantSiteManager;
import com.techatpark.gurukulam.eppo.service.MerchantSiteManagerService;

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

@Api(value = "MerchantSiteManagers", description = "Resource for MerchantSiteManagers", tags = {
        "MerchantSiteManagers" })
@RestController
@RequestMapping("/api/merchant_site_managers")

public class MerchantSiteManagerAPIController {

    private final MerchantSiteManagerService merchantSiteManagerService;

    MerchantSiteManagerAPIController(final MerchantSiteManagerService merchantSiteManagerService) {
        this.merchantSiteManagerService = merchantSiteManagerService;
    }

    @ApiOperation(value = "List all MerchantSiteManagers", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantSiteManagers Listed successfully"),
            @ApiResponse(code = 400, message = "MerchantSiteManagers Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<MerchantSiteManager>> findAll() {
        return ResponseEntity.ok(merchantSiteManagerService.list());
    }

    @ApiOperation(value = "Creates a new MerchantSiteManager", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantSiteManager created successfully"),
            @ApiResponse(code = 400, message = "MerchantSiteManager already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MerchantSiteManager> create(@Valid @RequestBody MerchantSiteManager merchantSiteManager) {
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantSiteManagerService.create(merchantSiteManager));
    }

    @ApiOperation(value = "Finds a MerchantSiteManager with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "MerchantSiteManager with a given ID found successfully"),
            @ApiResponse(code = 400, message = "MerchantSiteManager Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<MerchantSiteManager> findById(@PathVariable Integer id) {
        return ResponseEntity.of(merchantSiteManagerService.read(id));
    }

    @ApiOperation(value = "Updates a MerchantSiteManager", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantSiteManager updated successfully"),
            @ApiResponse(code = 400, message = "MerchantSiteManager Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<MerchantSiteManager> update(@PathVariable Integer id,
            @Valid @RequestBody MerchantSiteManager merchantSiteManager) {
        return ResponseEntity.ok(merchantSiteManagerService.update(id, merchantSiteManager));
    }

    @ApiOperation(value = "Deletes a MerchantSiteManager with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantSiteManager deleted successfully"),
            @ApiResponse(code = 400, message = "MerchantSiteManager Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<MerchantSiteManager> delete(@PathVariable Integer id) {
        merchantSiteManagerService.delete(id);
        return ResponseEntity.ok().build();
    }
}