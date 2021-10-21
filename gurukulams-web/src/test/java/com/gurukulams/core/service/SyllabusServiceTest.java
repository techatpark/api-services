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
        final Syllabus syllabus=syllabusService.create("mani",
                anSyllabus());
        assertEquals("MathsSyllabus", syllabus.title(), "Created Successfully");

    }

    @Test
    void read() {
        final Syllabus syllabus=syllabusService.create("mani",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertNotNull(syllabusService.read("mani", newSyllabusId),
                "syllabus Created");
        Assertions.assertNull(syllabusService.read("mani", 10000L),
                "invalid syllabus unavailable");
    }

    @Test
    void update() {

        final Syllabus syllabus = syllabusService.create("mani",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Syllabus newSyllabus = new Syllabus(null, "MathsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        Syllabus updatedSyllabus = syllabusService
                .update(newSyllabusId, "manikanta", newSyllabus);
        assertEquals("MathsSyllabus", updatedSyllabus.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            syllabusService
                    .update(10000L, "manikanta", newSyllabus);
        });
    }

    @Test
    void delete() {

        final Syllabus syllabus = syllabusService.create("mani",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertTrue(syllabusService.delete("manikanta", newSyllabusId));
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            syllabusService.read("manikanta", newSyllabusId);
//        });

    }

    @Test
    void list() {

        final Syllabus syllabus = syllabusService.create("manikanta",
                anSyllabus());
        Syllabus newSyllabus = new Syllabus(null, "PhysicsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        syllabusService.create("manikanta",
                newSyllabus);
        List<Syllabus> listofsyllabus = syllabusService.list("manikanta");
        Assertions.assertEquals(2, listofsyllabus.size());

    }

    /**
     * Gets syllabus.
     *
     * @return the syllabus
     */
    Syllabus anSyllabus() {

        Syllabus syllabus = new Syllabus(null, "MathsSyllabus",
                "An " + "Syllabus", null, "tom",
                                        null, null);
        return syllabus;
    }
}
