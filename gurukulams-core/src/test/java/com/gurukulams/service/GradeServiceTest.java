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
        final Grade grade = gradeService.create("mani",
                aGrade());
        Assertions.assertTrue(gradeService.read("mani",grade.id()).isPresent(),
                "Created Grade");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create("mani",
                aGrade());
        final Long newGradeId = grade.id();
        Assertions.assertTrue(gradeService.read("mani",
                        newGradeId).isPresent(),
                "Grade Created");
    }

    @Test
    void update() {
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create("mani",
                aGrade());
        final Long newGradeId = grade.id();
        Grade newGrade = new Grade(null, "Grade", "A " +
                "Grade", null, "tom", null, null);
        Grade updatedGrade = gradeService
                .update(newGradeId, "manikanta", newGrade);
        Assertions.assertEquals("Grade", updatedGrade.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradeService
                    .update(10000L, "manikanta", newGrade);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create("mani",
                aGrade());
        gradeService.delete("mani",grade.id());
        Assertions.assertFalse(gradeService.read("mani",grade.id()).isPresent(),
                "Deleted Grade");

    }

    @Test
    void list() {

        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create("manikanta",
                aGrade());
        Grade newGrade = new Grade(null, "Grade New", "A " +
                "Grade", null, "tom", null, null);
        gradeService.create("manikanta",
                newGrade);
        List<Grade> listofgrade = gradeService.list("manikanta");
        Assertions.assertEquals(2, listofgrade.size());

    }

    @Test
    void listByBoard() {
        final Board board = boardService.create("mani",
                aBoard());
        final Grade grade = gradeService.create("manikanta",
                aGrade());

        Assertions.assertTrue(gradeService.addToBoard("tom",grade.id(),board.id()),"Unable to add grade to board");

        Assertions.assertEquals(1,gradeService.list("tom",board.id()).size(),"Unable to list grades");

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
