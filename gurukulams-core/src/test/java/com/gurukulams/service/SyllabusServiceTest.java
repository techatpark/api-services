package com.gurukulams.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.model.Syllabus;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.SyllabusService;
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
public class SyllabusServiceTest {

    @Autowired
    private SyllabusService syllabusService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private GradeService gradeService;



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
        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        Assertions.assertTrue(syllabusService.read("mani", null,
                syllabus.id()).isPresent(),"Created Syllobous");
    }

    @Test
    void read() {
        final Syllabus syllabus=syllabusService.create("mani", null,
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertTrue(syllabusService.read("mani", null,
                        newSyllabusId).isPresent(),
                "syllabus Created");
    }

    @Test
    void update() {

        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Syllabus newSyllabus = new Syllabus(null, "MathsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        Syllabus updatedSyllabus = syllabusService
                .update(newSyllabusId, "manikanta", null, newSyllabus);
        Assertions.assertEquals("MathsSyllabus", updatedSyllabus.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            syllabusService
                    .update(10000L, "manikanta", null, newSyllabus);
        });
    }

    @Test
    void delete() {

        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        syllabusService.delete("mani",syllabus.id());
        Assertions.assertFalse(syllabusService.read("mani", null,
                              syllabus.id()).isPresent(),"Deleted Syllobous");

    }

    @Test
    void list() {

        final Syllabus syllabus = syllabusService.create("manikanta", null,
                anSyllabus());
        Syllabus newSyllabus = new Syllabus(null, "PhysicsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        syllabusService.create("manikanta", null,
                newSyllabus);
        List<Syllabus> listofsyllabus = syllabusService.list("manikanta", null);
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


    Grade aGrade() {

        Grade grade = new Grade(null, "Student Grade",
                "A " + "Grade", null, null,
                null, null);
        return grade;
    }


    /**
     * Gets board.
     *
     * @return the board
     */
    Board anBoard() {

        Board board = new Board(null, "State Board",
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}
