package com.gurukulams.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.GradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GradeServiceTest {

    @Autowired
    private GradeService gradeService;

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
        gradeService.deleteAllForTestCase();
    }

    @Test
    void create(){
        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("mani", null,
                aGrade());
        Assertions.assertTrue(gradeService.read("mani", null,grade.id()).isPresent(),
                "Created Grade");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("mani", null,
                aGrade());
        final Long newGradeId = grade.id();
        Assertions.assertTrue(gradeService.read("mani", null,
                        newGradeId).isPresent(),
                "Grade Created");
    }

    @Test
    void update() {
        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("mani", null,
                aGrade());
        final Long newGradeId = grade.id();
        Grade newGrade = new Grade(null, "Grade", "A " +
                "Grade", null, "tom", null, null);
        Grade updatedGrade = gradeService
                .update(newGradeId, "manikanta", null,  newGrade);
        Assertions.assertEquals("Grade", updatedGrade.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradeService
                    .update(10000L, "manikanta", null, newGrade);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("mani", null,
                aGrade());
        gradeService.delete("mani",grade.id());
        Assertions.assertFalse(gradeService.read("mani", null,grade.id()).isPresent(),
                "Deleted Grade");

    }

    @Test
    void list() {

        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("manikanta", null,
                aGrade());
        Grade newGrade = new Grade(null, "Grade New", "A " +
                "Grade", null, "tom", null, null);
        gradeService.create("manikanta", null,
                newGrade);
        List<Grade> listofgrade = gradeService.list("manikanta", null);
        Assertions.assertEquals(2, listofgrade.size());

    }

    @Test
    void listByBoard() {
        final Board board = boardService.create("mani",null,
                aBoard());
        final Grade grade = gradeService.create("manikanta", null,
                aGrade());

        Assertions.assertTrue(gradeService.addToBoard("tom",grade.id(),board.id()),"Unable to add grade to board");

        Assertions.assertEquals(1,gradeService.list("tom",null, board.id()).size(),"Unable to list grades");

    }

    @Test
    void testLocalization() {
        // Create a Grade
        final Grade grade = gradeService.create("mani",null,
                aGrade());

        // Update for China Language
        gradeService.update(grade.id(),"mani", Locale.CHINA, aGrade(grade,
                "Chinese Title",
                "Chinese Description"));

        // Get for China Language
        Grade createGrade = gradeService.read("mani",Locale.CHINA,
                grade.id()).get();
        Assertions.assertEquals("Chinese Title", createGrade.title());
        Assertions.assertEquals("Chinese Description", createGrade.description());

        final Long id = createGrade.id();
        createGrade = gradeService.list("mani", Locale.CHINA)
                .stream()
                .filter(grade1 -> grade1.id().equals(id))
                .findFirst().get();
        Assertions.assertEquals("Chinese Title", createGrade.title());
        Assertions.assertEquals("Chinese Description", createGrade.description());

        // Get for France which does not have data
        createGrade = gradeService.read("mani",Locale.FRANCE,
                grade.id()).get();
        Assertions.assertEquals("State Grade", createGrade.title());
        Assertions.assertEquals("State Grade Description", createGrade.description());

        createGrade = gradeService.list("mani",Locale.FRANCE)
                .stream()
                .filter(grade1 -> grade1.id().equals(id))
                .findFirst().get();

        Assertions.assertEquals("State Grade", createGrade.title());
        Assertions.assertEquals("State Grade Description", createGrade.description());

    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    Grade aGrade() {

        Grade grade = new Grade(null, "Student Grade" + new Date().getTime(),
                "A " + "Grade", null, null,
                null, null);
        return grade;
    }

    /**
     * Gets board from reference board.
     *
     * @return the board
     */
    Grade aGrade(final Grade ref,final String title,final String description) {
        return new Grade(ref.id(), title,
                description, ref.created_at(), ref.created_by(),
                ref.modified_at(), ref.modified_by());
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Board aBoard() {

        Board board = new Board(null, "State Board" + new Date().getTime(),
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}
