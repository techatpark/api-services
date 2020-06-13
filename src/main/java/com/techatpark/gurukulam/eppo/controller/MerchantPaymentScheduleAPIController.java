package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.MerchantPaymentSchedule;
import com.techatpark.gurukulam.eppo.service.MerchantPaymentScheduleService;

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

@Api(value = "MerchantPaymentSchedules", description = "Resource for MerchantPaymentSchedules", tags = {
        "MerchantPaymentSchedules" })
@RestController
@RequestMapping("/api/merchant_payment_schedules")
public class MerchantPaymentScheduleAPIController {

    private final MerchantPaymentScheduleService merchantPaymentScheduleService;

    MerchantPaymentScheduleAPIController(final MerchantPaymentScheduleService merchantPaymentScheduleService) {
        this.merchantPaymentScheduleService = merchantPaymentScheduleService;
    }

    @ApiOperation(value = "List all MerchantPaymentSchedules", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentSchedules Listed successfully"),
            @ApiResponse(code = 400, message = "MerchantPaymentSchedules Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<MerchantPaymentSchedule>> findAll() {
        return ResponseEntity.ok(merchantPaymentScheduleService.list());
    }

    @ApiOperation(value = "Creates a new MerchantPaymentSchedule", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentSchedule created successfully"),
            @ApiResponse(code = 400, message = "MerchantPaymentSchedule already in use") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MerchantPaymentSchedule> create(
            @Valid @RequestBody MerchantPaymentSchedule merchantPaymentSchedule) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantPaymentScheduleService.create(merchantPaymentSchedule));
    }

    @ApiOperation(value = "Finds a MerchantPaymentSchedule with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "MerchantPaymentSchedule with a given ID found successfully"),
            @ApiResponse(code = 400, message = "MerchantPaymentSchedule Not Available") })
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<MerchantPaymentSchedule> findById(@PathVariable Integer id) {
        return ResponseEntity.of(merchantPaymentScheduleService.read(id));
    }

    @ApiOperation(value = "Updates a MerchantPaymentSchedule", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentSchedule updated successfully"),
            @ApiResponse(code = 400, message = "MerchantPaymentSchedule Not Available") })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<MerchantPaymentSchedule> update(@PathVariable Integer id,
            @Valid @RequestBody MerchantPaymentSchedule merchantPaymentSchedule) {
        return ResponseEntity.ok(merchantPaymentScheduleService.update(id, merchantPaymentSchedule));
    }

    @ApiOperation(value = "Deletes a MerchantPaymentSchedule with a given ID", notes = "Can be called only by users with 'auth management' rights.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "MerchantPaymentSchedule deleted successfully"),
            @ApiResponse(code = 400, message = "MerchantPaymentSchedule Not Available") })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<MerchantPaymentSchedule> delete(@PathVariable Integer id) {
        merchantPaymentScheduleService.delete(id);
        return ResponseEntity.ok().build();
    }
}