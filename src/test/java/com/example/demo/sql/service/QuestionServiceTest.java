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
    /**
     * variable to be used for testing.
     */
    private static final String QUERY1 = "Query for all tables.";

    /**
     * variable to be used for testing.
     */
    private static final String ANSWER1 = "SELECT * FROM exams;";

    /**
     * Instance of Exam is used for testing Question.
     */
    private Exam exam;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private ExamService examService;

    @BeforeEach
    void before() {

        questionService.delete();
        examService.delete();
        exam = examService.create(getExam(), null).get();
    }

    @AfterEach
    void after() {
        questionService.delete();
        examService.delete();
    }

    @Test
    void testCreate() {
        final Question question = questionService.create(exam.getId(), getQuestion()).get();
        assertEquals(QUERY1, question.getQuestion(), "Created Successfully");
    }

    @Test
    void testUpdate() {
        Question question = questionService.create(exam.getId(), getQuestion()).get();
        question.setQuestion("Updated Query");
        final Integer newQuestionId = question.getId();
        question = questionService.update(newQuestionId, question).get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }

    @Test
    void testRead() {
        final Question question = questionService.create(exam.getId(), getQuestion()).get();
        final Integer newQuestionId = question.getId();
        assertNotNull(questionService.read(newQuestionId).get(), "Assert Created");
    }

    @Test
    void testDelete() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            final Question question = questionService.create(exam.getId(), getQuestion()).get();
            final Integer newQuestionId = question.getId();
            questionService.delete(newQuestionId);
            questionService.read(newQuestionId).get();
        });
    }

    @Test
    void testList() {
        questionService.create(exam.getId(), getQuestion()).get();
        final Question question2 = getQuestion();
        questionService.create(exam.getId(), question2);
        assertEquals(2, questionService.list(1, 2).size(), "Test Listing");
        assertEquals(1, questionService.list(1, 1).size(), "Test Listing with restricted page");
    }

    @Test
    void testListWithExamId() {
        final Question question = questionService.create(exam.getId(), getQuestion()).get();
        final Integer newExamId = question.getExamId();
        assertNotNull(questionService.list(newExamId), "Assert Created");
    }

    Question getQuestion() {
        final Question question = new Question();
        question.setQuestion(QUERY1);
        question.setAnswer(ANSWER1);
        return question;
    }

    Exam getExam() {
        final Exam exam = new Exam();
        exam.setName("Test Exam 1");
        return exam;
    }
}
