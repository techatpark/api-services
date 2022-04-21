package com.gurukulams.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.model.Subject;
import com.gurukulams.core.model.Syllabus;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.SubjectService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class SubjectsServiceTest {

    @Autowired
    private SubjectService subjectService;

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
        subjectService.deleteAll();
    }

    @Test
    void create() {
        final Subject subject = subjectService.create("mani",
                anSubject());
        Assertions.assertTrue(subjectService.read("mani", subject.id()).isPresent(),
                "Created Syllabous");
    }

    @Test
    void read() {
        final Subject subject = subjectService.create("mani",
                anSubject());
        final Long newSubjectId = subject.id();
        Assertions.assertTrue(subjectService.read("mani", newSubjectId).isPresent(),
                "subject Created");
    }

    @Test
    void update() {

        final Subject subject = subjectService.create("mani",
                anSubject());
        final Long newSubjectId = subject.id();
        Subject newSubject = new Subject(null, "MathsSubject", "An " +
                "Syllabus", null, "tom", null, null);
        Subject updateSubject = subjectService
                .update(newSubjectId, "manikanta", newSubject);
        Assertions.assertEquals("MathsSubject", updateSubject.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            subjectService
                    .update(10000L, "manikanta", newSubject);
        });
    }

    @Test
    void delete() {

        final Subject subject = subjectService.create("mani",
                anSubject());
        subjectService.delete("mani", subject.id());
        Assertions.assertFalse(subjectService.read("mani", subject.id()).isPresent(), "Deleted Subject");

    }

    @Test
    void list() {

        final Subject subject = subjectService.create("mani",
                anSubject());
        Subject newSubject = new Subject(null, "Physicssubject", "An " +
                "Syllabus", null, "tom", null, null);
        subjectService.create("manikanta",
                newSubject);
        List<Subject> listofsyllabus = subjectService.list("manikanta");
        Assertions.assertEquals(2, listofsyllabus.size());

    }


    @Test
    void listbyBoardandgrade() {

        final Board board = boardService.create("mani",
                anBoard());
        final Grade grade = gradeService.create("tom", aGrade());
        final Subject subject = subjectService.create("tom",anSubject());

        Assertions.assertTrue(subjectService.addToBoardsGrades("tom", board.id(), grade.id(), subject.id()),"Unable to add subject ");

        Assertions.assertEquals(1,subjectService.list("tom", board.id(),
                grade.id()).size(),"Unable to list subjects");


    }

    /**
     * Get subject.
     *
     * @return the subject
     */
    Subject anSubject() {

        Subject subject = new Subject(null, "MathsSubject",
                "An " + "Syllabus", null, null,
                null, null);
        return subject;
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
