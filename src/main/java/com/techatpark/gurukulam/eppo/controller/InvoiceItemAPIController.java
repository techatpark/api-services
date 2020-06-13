package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.InvoiceItem;
import com.techatpark.gurukulam.eppo.service.InvoiceItemService;

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

@Api(value = "InvoiceItems", description = "Resource for InvoiceItems", tags = { "InvoiceItems" })
@RestController
@RequestMapping("/api/invoice_items")

public class InvoiceItemAPIController {

    private final InvoiceItemService invoiceItemService;

    InvoiceItemAPIController(final InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @ApiOperation(value = "List all InvoiceItems", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "InvoiceItems Listed successfully"),
            @ApiResponse(code = 400, message = "InvoiceItems Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<InvoiceItem>> findAll() {
        return ResponseEntity.ok(invoiceItemService.list());
    }

    @ApiOperation(value = "Creates a new InvoiceItem", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "InvoiceItem created successfully"),
            @ApiResponse(code = 400, message = "InvoiceItem already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<InvoiceItem> create(@Valid @RequestBody InvoiceItem invoiceItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceItemService.create(invoiceItem));
    }

    @ApiOperation(value = "Finds a InvoiceItem with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "InvoiceItem with a given ID found successfully"),
            @ApiResponse(code = 400, message = "InvoiceItem Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> findById(@PathVariable Integer id) {
        return ResponseEntity.of(invoiceItemService.read(id));
    }

    @ApiOperation(value = "Updates a InvoiceItem", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "InvoiceItem updated successfully"),
            @ApiResponse(code = 400, message = "InvoiceItem Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> update(@PathVariable Integer id, @Valid @RequestBody InvoiceItem invoiceItem) {
        return ResponseEntity.ok(invoiceItemService.update(id, invoiceItem));
    }

    @ApiOperation(value = "Deletes a InvoiceItem with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "InvoiceItem deleted successfully"),
            @ApiResponse(code = 400, message = "InvoiceItem Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceItem> delete(@PathVariable Integer id) {
        invoiceItemService.delete(id);
        return ResponseEntity.ok().build();
    }
}