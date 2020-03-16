package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.tracker.model.Device;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeviceServiceTest {

	@Autowired
	DeviceService deviceService;

	@BeforeEach
	void before() {
		deviceService.delete();
	}

	@AfterEach
	void after() {

	}

	/**
	 * This test case creates a new device and confirm it exists with same code we
	 * created with
	 */
	@Test
	void testCreate() {
		Device device = deviceService.create(getDeviceForTesting());
		assertEquals("09ABC", device.getCode(), "Test Create");
	}

	/**
	 * 
	 */
	@Test
	void testDelete() {
		Device device = deviceService.create(getDeviceForTesting());

		Integer newDeviceId = device.getId();
		assertNotNull(deviceService.read(newDeviceId), "Created Device exists before delete");
		deviceService.delete(newDeviceId);
		assertNull(deviceService.read(newDeviceId), "Created Device does not exist after delete");

	}

	@Test
	void testUpdate() {
		Device device = deviceService.create(getDeviceForTesting());
		Integer deviceId = device.getId();
		device.setName("Updated Name");
		deviceService.update(deviceId, device);
		assertEquals("Updated Name", deviceService.read(deviceId).getName(), "Test Update");
	}

	@Test
	void testList() {
		deviceService.create(getDeviceForTesting());
		deviceService.create(getDeviceForTesting());
		assertEquals(2, deviceService.list().size(), "Test Listing");
	}

	private Device getDeviceForTesting() {
		Device device = new Device();
		device.setActiveFlag(1);
		device.setApiFlag(1);
		device.setCode("09ABC");
		device.setDeviceIMEICode("csjh4jkj2");
		device.setGsmCode("fwfee");
		device.setName("sensor1");
		device.setRemarks("this is a remark");
		device.setSensor("efdd");
		return device;
	}
}
