package com.gurukulams.core.service;

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
        gradeService.deleteAll();
    }

    @Test
    void create(){
        final Grade grade = gradeService.create("mani",
                anGrade());
        assertTrue(gradeService.read("mani",grade.id()).isPresent(),
                "Created Grade");
    }

    @Test
    void read() {
        final Grade grade = gradeService.create("mani",
                anGrade());
        final Long newGradeId = grade.id();
        Assertions.assertTrue(gradeService.read("mani", newGradeId).isPresent(),
                "Grade Created");
    }

    @Test
    void update() {

        final Grade grade = gradeService.create("mani",
                anGrade());
        final Long newGradeId = grade.id();
        Grade newGrade = new Grade(null, "Grade", "A " +
                "Grade", null, "tom", null, null);
        Grade updatedGrade = gradeService
                .update(newGradeId, "manikanta", newGrade);
        assertEquals("Grade", updatedGrade.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradeService
                    .update(10000L, "manikanta", newGrade);
        });
    }

    @Test
    void delete() {

        final Grade grade = gradeService.create("mani",
                anGrade());
        gradeService.delete("mani",grade.id());
        assertFalse(gradeService.read("mani",grade.id()).isPresent(),
                "Deleted Grade");

    }

    @Test
    void list() {

        final Grade grade = gradeService.create("manikanta",
                anGrade());
        Grade newGrade = new Grade(null, "Grade New", "A " +
                "Grade", null, "tom", null, null);
        gradeService.create("manikanta",
                newGrade);
        List<Grade> listofgrade = gradeService.list("manikanta");
        Assertions.assertEquals(2, listofgrade.size());

    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    Grade anGrade() {

        Grade grade = new Grade(null, "Student Grade",
                "A " + "Grade", null, null,
                null, null);
        return grade;
    }
}
