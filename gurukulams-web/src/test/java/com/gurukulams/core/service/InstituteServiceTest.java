package com.gurukulams.core.service;

import com.gurukulams.core.model.Institute;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        System.out.println(institute.title());
        assertEquals("HariInstitute", institute.title(), "Created Successfully");
    }

    @Test
    void read() {
        final Institute institute = instituteService.create("hari",
                anInstitute());
        final Long newInstituteId = institute.id();
        Assertions.assertNotNull(instituteService.read("hari", newInstituteId),
                "Assert Created");
    }

    @Test
    void update() {

        final Institute institute = instituteService.create("hari",
                anInstitute());
        final Long newInstituteId = institute.id();
        Institute newInstitute = new Institute(null, "HansiInstitute", "An " +
                "Institute", null, null, null, null);
        Optional<Institute> updatedInstitute = instituteService
                .update(newInstituteId, "priya", newInstitute);
        assertEquals("HansiInstitute", updatedInstitute.get().title(), "Updated");
    }

    @Test
    void delete() {

            final Institute institute = instituteService.create("hari",
                    anInstitute());
            final Long newInstituteId = institute.id();
        Assertions.assertTrue(instituteService.delete("hari", newInstituteId));
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            instituteService.read("hari", newInstituteId);
//        });

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