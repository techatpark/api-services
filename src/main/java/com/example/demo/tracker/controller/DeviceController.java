package com.example.demo.tracker.controller;

import javax.validation.Valid;

import com.example.demo.tracker.model.Device;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/devices")
@Api(description = "Resources that is responsible to manage devices")
public class DeviceController {

	@ApiOperation(value = "Create a device")
	@RequestMapping(method = RequestMethod.POST)
	public Device create(@Valid Device device) {
		return null;
	}

	@ApiOperation(value = "Get a device with given ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Device read(@PathVariable("id") Long id) {
		return null;
	}

	@ApiOperation(value = "Update a device with given ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Device update(@PathVariable("id") Long id, Device device) {
		return null;
	}

	@ApiOperation(value = "Delete a device with given ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable("id") Long id) {
		return true;
	}

}