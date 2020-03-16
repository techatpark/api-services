package com.example.demo.tracker.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("api/devices")
@Api(description = "Resources that is responsible to manage devices")
public class DeviceController {

	@ApiOperation(value = "Post comment for a topic")
	@RequestMapping(value = "/{topic}", method = RequestMethod.POST)
	public String postComment(@PathVariable String topic) {
		return null;
	}

}