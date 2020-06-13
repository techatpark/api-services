package com.techatpark.gurukulam.eppo.controller;

import java.util.List;

import com.techatpark.gurukulam.eppo.model.RentalLocation;
import com.techatpark.gurukulam.eppo.service.RentalWorkflowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Flows", description = "Resource for Managing Flows", tags = { "Flows" })

@RestController
@RequestMapping("/api/flows")

public class RentalFlowAPIController {

    @Autowired
    private RentalWorkflowService rentalWorkflowService;

    @PostMapping("/submit")
    public void submit(@RequestBody RentalLocation rentalLocation) {
        rentalWorkflowService.startProcess(rentalLocation);
    }

    @GetMapping("/tasks")
    public List<RentalLocation> getTasks(@RequestParam String location) {
        return rentalWorkflowService.getTasks(location);
    }

}