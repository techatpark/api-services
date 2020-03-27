package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.tracker.model.Menu;
import com.example.demo.tracker.model.Status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @BeforeEach
    void before() {
        menuService.delete();
    }

    @AfterEach
    void after() {

    }

    @Test
    void testCreate() {
        Menu menu = menuService.create(getMenuForTesting());
        assertNotNull(menu.getUpdatedAt(), "Test Create");

    }

    @Test
    void testDelete() {

        Menu menu = menuService.create(getMenuForTesting());
        Integer newMenuId = menu.getId();
        Boolean successFlag = menuService.delete(newMenuId);
        assertTrue(successFlag, "success flag deleted");
        assertTrue(menuService.read(newMenuId).isEmpty(), "Created Device does not exist after delete");
    }

    @Test
    void testUpdate() {
        Menu menu = menuService.create(getMenuForTesting());
        menu.setName("Updated Name");
        Integer newMenuId = menu.getId();
        menu = menuService.update(newMenuId, menu);
        assertEquals("Updated Name", menu.getName(), "Updated");
    }

    private Menu getMenuForTesting() {
        Menu menu = new Menu();
        menu.setCode("21233");
        menu.setName("hari");
        menu.setLink("happy.com");
        menu.setActionCode("fqjg");
        menu.setLookupId(345261);
        menu.setDefaultFlag(1);
        menu.setDisplayFlag(1);
        menu.setProductTypeId(3);
        menu.setStatus(Status.ACTIVE);
        menu.setUpdatedBy(1);
        return menu;
    }
}
