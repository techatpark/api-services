package com.gurukulams.service;

import com.gurukulams.core.model.Institute;
import com.gurukulams.core.service.InstituteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;

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
        instituteService.deleteAll();
    }


    @Test
    void create() {
        final Institute institute = instituteService.create("hari",
                anInstitute());
        Assertions.assertTrue(instituteService.read("hari",institute.id()).isPresent(),"Created Institute");
    }

    @Test
    void read() {
        final Institute institute = instituteService.create("hari",
                anInstitute());
        final Long newInstituteId = institute.id();
        Assertions.assertTrue(instituteService.read("hari",institute.id()).isPresent(),
                "Created Institute");
    }

    @Test
    void update() {

        final Institute institute = instituteService.create("hari",
                anInstitute());
        final Long newInstituteId = institute.id();
        Institute newInstitute = new Institute(null, "HansiInstitute", "An " +
                "Institute", null, null, null, null);
        Institute updatedInstitute = instituteService
                .update(newInstituteId, "priya", newInstitute);
        Assertions.assertEquals("HansiInstitute", updatedInstitute.title(), "Updated");

                Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    instituteService
                            .update(10000L, "priya", newInstitute);
        });
    }

    @Test
    void delete() {

            final Institute institute = instituteService.create("hari",
                    anInstitute());
        instituteService.delete("mani",institute.id());
        Assertions.assertFalse(instituteService.read("mani",institute.id()).isPresent(),"Deleted Institute");
    }

    @Test
    void list() {

        final Institute institute = instituteService.create("hari",
                anInstitute());
        Institute newInstitute = new Institute(null, "HansiInstitute", "An " +
                "Institute", null, null, null, null);
        instituteService.create("hari",
                newInstitute);
        List<Institute> listofinstitutes = instituteService.list("hari");
        Assertions.assertEquals(2, listofinstitutes.size());

    }

    /**
     * Gets practice.
     *
     * @return the practice
     */
    Institute anInstitute() {

        Institute institute = new Institute(null, "HariInstitute", "An " +
                "Institute", null, null, null, null);
        return institute;
    }


}