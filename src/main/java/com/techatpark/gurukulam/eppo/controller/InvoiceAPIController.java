package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.Invoice;
import com.techatpark.gurukulam.eppo.service.InvoiceService;

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

@Api(value = "Invoices", description = "Resource for Invoices", tags = { "Invoices" })
@RestController
@RequestMapping("/api/invoices")

public class InvoiceAPIController {

    private final InvoiceService invoiceService;

    InvoiceAPIController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @ApiOperation(value = "List all Invoices", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Invoices Listed successfully"),
            @ApiResponse(code = 400, message = "Invoices Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Invoice>> findAll() {
        return ResponseEntity.ok(invoiceService.list());
    }

    @ApiOperation(value = "Creates a new Invoice", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Invoice created successfully"),
            @ApiResponse(code = 400, message = "Invoice already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Invoice> create(@Valid @RequestBody Invoice invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.create(invoice));
    }

    @ApiOperation(value = "Finds a Invoice with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Invoice with a given ID found successfully"),
            @ApiResponse(code = 400, message = "Invoice Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Integer id) {
        return ResponseEntity.of(invoiceService.read(id));
    }

    @ApiOperation(value = "Updates a Invoice", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Invoice updated successfully"),
            @ApiResponse(code = 400, message = "Invoice Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Integer id, @Valid @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.update(id, invoice));
    }

    @ApiOperation(value = "Deletes a Invoice with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Invoice deleted successfully"),
            @ApiResponse(code = 400, message = "Invoice Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> delete(@PathVariable Integer id) {
        invoiceService.delete(id);
        return ResponseEntity.ok().build();
    }
}