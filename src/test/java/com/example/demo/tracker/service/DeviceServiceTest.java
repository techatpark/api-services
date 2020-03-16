package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.tracker.model.Device;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeviceServiceTest {

	@Autowired
	DeviceService deviceService;

	/**
	* This test case creates a new device and confirm it exists with same code we created with
	*/
	@Test
	void testCreate() {
		Device device = new Device();
		device.setCode("code");
		device = deviceService.create(device);
		assertEquals("code", device.getCode(), "Test Create");
	}

	/**
	 * 
	 */
	@Test
	void testDelete() {
		Device device = new Device();
		device.setCode("code");
		device = deviceService.create(device);

		Integer newDeviceId = device.getId();
		assertNotNull(deviceService.read(newDeviceId), "Created Device exists before delete");
		deviceService.delete(newDeviceId);
		assertNull(deviceService.read(newDeviceId), "Created Device does not exist after delete");

	}
}
