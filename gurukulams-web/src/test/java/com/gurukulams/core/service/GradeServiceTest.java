package com.gurukulams.core.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Grade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

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
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create(board.id(),"mani",
                aGrade());
        assertTrue(gradeService.read(board.id(), "mani",grade.id()).isPresent(),
                "Created Grade");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create(board.id(), "mani",
                aGrade());
        final Long newGradeId = grade.id();
        Assertions.assertTrue(gradeService.read(board.id(), "mani",
                        newGradeId).isPresent(),
                "Grade Created");
    }

    @Test
    void update() {
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create(board.id(), "mani",
                aGrade());
        final Long newGradeId = grade.id();
        Grade newGrade = new Grade(null, "Grade", "A " +
                "Grade", null, "tom", null, null);
        Grade updatedGrade = gradeService
                .update(board.id(), newGradeId, "manikanta", newGrade);
        assertEquals("Grade", updatedGrade.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradeService
                    .update(board.id(), 10000L, "manikanta", newGrade);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create(board.id(), "mani",
                aGrade());
        gradeService.delete(board.id(), "mani",grade.id());
        assertFalse(gradeService.read(board.id(), "mani",grade.id()).isPresent(),
                "Deleted Grade");

    }

    @Test
    void list() {

        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create(board.id(), "manikanta",
                aGrade());
        Grade newGrade = new Grade(null, "Grade New", "A " +
                "Grade", null, "tom", null, null);
        gradeService.create(board.id(), "manikanta",
                newGrade);
        List<Grade> listofgrade = gradeService.list(board.id(), "manikanta");
        Assertions.assertEquals(2, listofgrade.size());

    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
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
    Board aBoard() {

        Board board = new Board(null, "State Board",
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}
