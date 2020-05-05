package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.NoSuchElementException;

import com.example.demo.sql.model.Exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamServiceTest {

    private static final String EXAM1 = "Exam 1";

    @Autowired
    ExamService examService;

    @BeforeEach
    void before() {
        examService.delete();
    }

    @Test
    void testCreate() {
        Exam exam = examService.create(getExam()).get();
        assertEquals(EXAM1, exam.getName());
    }

    @Test
    void testUpdate() {
        Exam exam = examService.create(getExam()).get();
        exam.setName("Updated Name");
        Integer newExamId = exam.getId();
        exam = examService.update(newExamId, exam).get();
        assertEquals("Updated Name", exam.getName(), "Updated");
    }

    @Test
    void testRead() {
        Exam exam = examService.create(getExam()).get();
        Integer newExamId = exam.getId();
        assertNotNull(examService.read(newExamId).get(), "Assert Created");
    }

    @Test
    void testDelete() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Exam exam = examService.create(getExam()).get();
            Integer newExamId = exam.getId();
            examService.delete(newExamId);
            examService.read(newExamId).get();
        });
    }

    @Test
    void testList() {
        examService.create(getExam()).get();
        Exam exam2 = getExam();
        examService.create(exam2);
        assertEquals(2, examService.list(1, 2).size(), "Test Listing");
        assertEquals(1, examService.list(1, 1).size(), "Test Listing with restricted page");
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName(EXAM1);
        return exam;
    }
}
