package com.gurukulams.core.service;

import com.gurukulams.core.model.*;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstituteServiceTest {

    @Autowired
    private InstituteService instituteService;

    @Autowired
    private BoardService boardService;

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
    void create(){
        final Board board = boardService.create("mani",
                aBoard());
        final Institute institute = instituteService.create(board.id(),"mani",
                anInstitute());
        assertTrue(instituteService.read(board.id(), "mani",institute.id()).isPresent(),
                "Created Institute");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",
                aBoard());
        final Institute institute = instituteService.create(board.id(),"mani",
                anInstitute());
        final Long newInstituteId = institute.id();
        Assertions.assertTrue(instituteService.read(board.id(), "mani",
                        newInstituteId).isPresent(),
                "Institute Created");
    }

    @Test
    void update() {
        final Board board = boardService.create("mani",
                aBoard());
        final Institute institute = instituteService.create(board.id(),"mani",
                anInstitute());
        final Long newInstituteId = institute.id();
        Institute newInstitute = new Institute(null, "Institute", "An " +
                "Institute", null, "tom", null, null);
        Institute updatedInstitute = instituteService
                .update(board.id(), newInstituteId, "manikanta", newInstitute);
        assertEquals("Institute", updatedInstitute.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instituteService
                    .update(board.id(), 10000L, "manikanta", newInstitute);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",
                aBoard());
        final Institute institute = instituteService.create(board.id(),"mani",
                anInstitute());
        instituteService.delete(board.id(), "mani",institute.id());
        assertFalse(instituteService.read(board.id(), "mani",institute.id()).isPresent(),
                "Deleted Institute");

    }

    @Test
    void list() {

        final Board board = boardService.create("mani",
                aBoard());
        final Institute institute = instituteService.create(board.id(),"mani",
                anInstitute());
        Institute newInstitute = new Institute(null, "Institute", "An " +
                "Institute", null, "tom", null, null);
        instituteService.create(board.id(), "manikanta",
                newInstitute);
        List<Institute> listofInstitutes = instituteService.list(board.id(), "manikanta");
        Assertions.assertEquals(2, listofInstitutes.size());

    }

    /**
     * Gets practice.
     *
     * @return the practice
     */
    Institute anInstitute() {

        Institute institute = new Institute(null, "mani Institute", "An " +
                "Institute", null, null, null, null);
        return institute;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Board aBoard() {

        Board board = new Board(null, "State Board",
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}