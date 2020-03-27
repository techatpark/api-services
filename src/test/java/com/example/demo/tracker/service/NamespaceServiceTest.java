package com.example.demo.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testDelete() {
        Namespace namespace = namespaceService.create(getNamespaceForTesting());
        Integer newNamespaceId = namespace.getId();
        Boolean successFlag = namespaceService.delete(newNamespaceId);
        assertTrue(successFlag, "success flag deleted");
        assertTrue(namespaceService.read(newNamespaceId).isEmpty(), "Created Device does not exist after delete");
    }

    @Test
    void testUpdate() {
        Namespace namespace = namespaceService.create(getNamespaceForTesting());

        namespace.setName("Updated Name");

        Integer newNamespaceId = namespace.getId();

        namespace = namespaceService.update(newNamespaceId, namespace);

        assertEquals("Updated Name", namespace.getName(), "Updated");
    }

    @Test
    void testList() {
        namespaceService.create(getNamespaceForTesting());
        Namespace namespace2 = getNamespaceForTesting();
        namespace2.setCode("12ee4");
        namespaceService.create(namespace2);
        assertEquals(2, namespaceService.list().size(), "Test Listing");
    }

    private Namespace getNamespaceForTesting() {
        Namespace namespace = new Namespace();
        namespace.setStatus(Status.ACTIVE);
        namespace.setCode("12a3");
        namespace.setName("KPN");
        return namespace;
    }

}
