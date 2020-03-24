package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals("21233", menu.getCode(), "Test Create");
    }

    @Test
    void testDelete() {
        Menu menu = menuService.create(getMenuForTesting());
        Integer newMenuId = menu.getId();
        assertFalse(menuService.read(newMenuId).isEmpty(), "Created Device exists before delete");
        menuService.delete(newMenuId);
        assertTrue(menuService.read(newMenuId).isEmpty(), "Created Device does not exist after delete");
    }

    private Menu getMenuForTesting() {
        Menu menu = new Menu();

        menu.setCode("21233");

        return menu;
    }
}
