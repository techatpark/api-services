package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.tracker.model.Namespace;
import com.example.demo.tracker.model.Status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamespaceServiceTest {

    @Autowired
    private NamespaceService namespaceService;

    @BeforeEach
    void before() {
        namespaceService.delete();
    }

    @AfterEach
    void after() {

    }

    @Test
    void testCreate() {
        Namespace namespace = namespaceService.create(getNamespaceForTesting());
        assertEquals("12a3", namespace.getCode(), "Test Create");
    }

    private Namespace getNamespaceForTesting() {
        Namespace namespace = new Namespace();
        namespace.setStatus(Status.ACTIVE);
        namespace.setCode("12a3");
        namespace.setName("KPN");
        return namespace;
    }

    @Test
    void testDelete() {
        Namespace namespace = namespaceService.create(getNamespaceForTesting());
        Integer newNamespaceId = namespace.getId();
        assertNotNull(namespaceService.read(newNamespaceId), "Created Device exists before delete");
        namespaceService.delete(newNamespaceId);
        assertNull(namespaceService.read(newNamespaceId), "Created Device does not exist after delete");
    }

    @Test
    void testUpdate() {
        Namespace namespace = namespaceService.create(getNamespaceForTesting());
        Integer newNamespaceId = namespace.getId();
        namespace.setName("Updated Name");
        namespaceService.update(newNamespaceId, namespace);
        assertEquals("Updated Name", namespace.getName(), "Updated");
    }

    @Test
    void testList() {
        namespaceService.create(getNamespaceForTesting());
        Namespace namespace2 = getNamespaceForTesting();
        namespace2.setName("KPN");
        namespaceService.create(namespace2);
        assertEquals(2, namespaceService.list().size(), "Test Listing");
    }

}
