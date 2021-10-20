package com.gurukulams.core.service;

import com.gurukulams.core.model.Syllabus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SyllabusServiceTest {

    @Autowired
    private SyllabusService syllabusService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    private void cleanUp() {
        syllabusService.deleteAll();
    }

    @Test
    void create(){
        final Syllabus syllabus=syllabusService.create("tom",
                anSyllabus());
        assertEquals("MathsSyllabus", syllabus.title(), "Created Successfully");

    }

    @Test
    void read() {
        final Syllabus syllabus=syllabusService.create("tom",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertNotNull(syllabusService.read("tom", newSyllabusId),
                "Assert Created");
    }

    @Test
    void update() {

        final Syllabus syllabus = syllabusService.create("tom",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Syllabus newSyllabus = new Syllabus(null, "MathsSyllabus", "An " +
                "Syllabus", null, null, null, null);
        Syllabus updatedSyllabus = syllabusService
                .update(newSyllabusId, "jerry", newSyllabus);
        assertEquals("MathsSyllabus", updatedSyllabus.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            syllabusService
                    .update(10000L, "jerry", newSyllabus);
        });
    }

    @Test
    void delete() {

        final Syllabus syllabus = syllabusService.create("tom",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertTrue(syllabusService.delete("jerry", newSyllabusId));
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            syllabusService.read("jerry", newSyllabusId);
//        });

    }

    @Test
    void list() {

        final Syllabus syllabus = syllabusService.create("jerry",
                anSyllabus());
        Syllabus newSyllabus = new Syllabus(null, "PhysicsSyllabus", "An " +
                "Syllabus", null, null, null, null);
        syllabusService.create("jerry",
                newSyllabus);
        List<Syllabus> listofsyllabus = syllabusService.list("jerry");
        Assertions.assertEquals(2, listofsyllabus.size());

    }

    /**
     * Gets syllabus.
     *
     * @return the syllabus
     */
    Syllabus anSyllabus() {

        Syllabus syllabus = new Syllabus(null, "MathsSyllabus",
                "An " + "Syllabus", null, null,
                                        null, null);
        return syllabus;
    }
}
