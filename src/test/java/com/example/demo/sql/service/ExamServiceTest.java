package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.sql.model.Exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamServiceTest {

    private static final String EXAM1 = "Exam 1";
    private static final Integer ID_1 = new Integer(1);

    @Autowired
    ExamService examService;

    @BeforeEach
    void before() {
        boolean isDelete = examService.delete(ID_1);
    }

    @Test
    void create() {
        Exam exam = examService.create(getExam()).get();
        assertEquals(EXAM1, exam.getName());
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setId(ID_1);
        exam.setName(EXAM1);
        return exam;
    }
}
