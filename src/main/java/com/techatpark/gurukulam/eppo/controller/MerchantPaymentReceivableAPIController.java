package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.MerchantPaymentReceivable;
import com.techatpark.gurukulam.eppo.service.MerchantPaymentReceivableService;

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

@Api(value = "MerchantPaymentReceivables", description = "Resource for MerchantPaymentReceivables", tags = {
                "MerchantPaymentReceivables" })
@RestController
@RequestMapping("/api/merchant_payment_receivables")
public class MerchantPaymentReceivableAPIController {

        private final MerchantPaymentReceivableService merchantPaymentReceivableService;

        MerchantPaymentReceivableAPIController(
                        final MerchantPaymentReceivableService merchantPaymentReceivableService) {
                this.merchantPaymentReceivableService = merchantPaymentReceivableService;
        }

        @ApiOperation(value = "List all MerchantPaymentReceivables", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentReceivables Listed successfully"),
                        @ApiResponse(code = 400, message = "MerchantPaymentReceivables Not Available") })
        @ResponseStatus(HttpStatus.OK)
        @GetMapping
        public ResponseEntity<List<MerchantPaymentReceivable>> findAll() {
                return ResponseEntity.ok(merchantPaymentReceivableService.list());
        }

        @ApiOperation(value = "Creates a new MerchantPaymentReceivable", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentReceivable created successfully"),
                        @ApiResponse(code = 400, message = "MerchantPaymentReceivable already in use") })
        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping
        public ResponseEntity<MerchantPaymentReceivable> create(
                        @Valid @RequestBody MerchantPaymentReceivable merchantPaymentReceivable) {
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(merchantPaymentReceivableService.create(merchantPaymentReceivable));
        }

        @ApiOperation(value = "Finds a MerchantPaymentReceivable with a given ID", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "MerchantPaymentReceivable with a given ID found successfully"),
                        @ApiResponse(code = 400, message = "MerchantPaymentReceivable Not Available") })
        @ResponseStatus(HttpStatus.FOUND)
        @GetMapping("/{id}")
        public ResponseEntity<MerchantPaymentReceivable> findById(@PathVariable Integer id) {
                return ResponseEntity.of(merchantPaymentReceivableService.read(id));
        }

        @ApiOperation(value = "Updates a MerchantPaymentReceivable", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentReceivable updated successfully"),
                        @ApiResponse(code = 400, message = "MerchantPaymentReceivable Not Available") })
        @ResponseStatus(HttpStatus.CREATED)
        @PutMapping("/{id}")
        public ResponseEntity<MerchantPaymentReceivable> update(@PathVariable Integer id,
                        @Valid @RequestBody MerchantPaymentReceivable merchantPaymentReceivable) {
                return ResponseEntity.ok(merchantPaymentReceivableService.update(id, merchantPaymentReceivable));
        }

        @ApiOperation(value = "Deletes a MerchantPaymentReceivable with a given ID", notes = "Can be called only by users with 'auth management' rights.")
        @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentReceivable deleted successfully"),
                        @ApiResponse(code = 400, message = "MerchantPaymentReceivable Not Available") })
        @ResponseStatus(HttpStatus.OK)
        @DeleteMapping("/{id}")
        public ResponseEntity<MerchantPaymentReceivable> delete(@PathVariable Integer id) {
                merchantPaymentReceivableService.delete(id);
                return ResponseEntity.ok().build();
        }
}