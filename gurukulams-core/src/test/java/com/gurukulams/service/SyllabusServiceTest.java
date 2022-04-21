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
        final Syllabus syllabus = syllabusService.create("mani",
                anSyllabus());
        Assertions.assertTrue(syllabusService.read("mani",syllabus.id()).isPresent(),"Created Syllobous");
    }

    @Test
    void read() {
        final Syllabus syllabus=syllabusService.create("mani",
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertTrue(syllabusService.read("mani", newSyllabusId).isPresent(),
                "syllabus Created");
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
        Assertions.assertEquals("MathsSyllabus", updatedSyllabus.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            syllabusService
                    .update(10000L, "manikanta", newSyllabus);
        });
    }

    @Test
    void delete() {

        final Syllabus syllabus = syllabusService.create("mani",
                anSyllabus());
        syllabusService.delete("mani",syllabus.id());
        Assertions.assertFalse(syllabusService.read("mani",syllabus.id()).isPresent(),"Deleted Syllobous");

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

@Test
    void listbyBoardandgrade() {

    final Board board = boardService.create("mani",
            anBoard());
    final Grade grade = gradeService.create("tom", aGrade());
    final Syllabus syllabus = syllabusService.create("tom",anSyllabus());

    Assertions.assertTrue(syllabusService.addToBoardsGrades("tom", board.id(), grade.id(), syllabus.id()),"Unable to add syllabus to grade");

    Assertions.assertEquals(1,syllabusService.list("tom", board.id() , grade.id()).size(),"Unable to list syllabus");


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
