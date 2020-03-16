package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.tracker.model.Device;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeviceServiceTest {

	@Autowired
	DeviceService deviceService;

	@Test
	void testCreate() {
		Device device = new Device();
		device.setCode("code");
		device = deviceService.create(device);
		assertEquals("code", device.getCode(), "Test Create");
	}

}
