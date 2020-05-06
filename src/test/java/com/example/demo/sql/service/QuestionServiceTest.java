package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.NoSuchElementException;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionServiceTest {

    private static final String Query1 = "Query for all tables.";
    private static final String Answer1 = "SELECT * FROM exams;";

    private Exam exam;
    @Autowired
    QuestionService questionService;

    @Autowired
    ExamService examService;

    @BeforeEach
    void before() {

        questionService.delete();
        examService.delete();
        exam = examService.create(getExam()).get();
    }

    @AfterEach
    void after() {
        questionService.delete();
        examService.delete();
    }

    @Test
    void testCreate() {
        Question question = questionService.create(exam.getId(), getQuestion()).get();
        assertEquals(Query1, question.getQuestion(), "Created Successfully");
    }

    @Test
    void testUpdate() {
        Question question = questionService.create(exam.getId(), getQuestion()).get();
        question.setQuestion("Updated Query");
        Integer newQuestionId = question.getId();
        question = questionService.update(newQuestionId, question).get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }

    @Test
    void testRead() {
        Question question = questionService.create(exam.getId(), getQuestion()).get();
        Integer newQuestionId = question.getId();
        assertNotNull(questionService.read(newQuestionId).get(), "Assert Created");
    }

    @Test
    void testDelete() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Question question = questionService.create(exam.getId(), getQuestion()).get();
            Integer newQuestionId = question.getId();
            questionService.delete(newQuestionId);
            questionService.read(newQuestionId).get();
        });
    }

    @Test
    void testList() {
        questionService.create(exam.getId(), getQuestion()).get();
        Question question2 = getQuestion();
        questionService.create(exam.getId(), question2);
        assertEquals(2, questionService.list(1, 2).size(), "Test Listing");
        assertEquals(1, questionService.list(1, 1).size(), "Test Listing with restricted page");
    }

    @Test
    void testListWithExamId() {
        Question question = questionService.create(exam.getId(), getQuestion()).get();
        Integer newExamId = question.getExamId();
        assertNotNull(questionService.list(newExamId), "Assert Created");
    }

    Question getQuestion() {
        Question question = new Question();
        question.setQuestion(Query1);
        question.setAnswer(Answer1);
        return question;
    }

    Exam getExam() {
        Exam exam = new Exam();
        exam.setName("Test Exam 1");
        return exam;
    }
}