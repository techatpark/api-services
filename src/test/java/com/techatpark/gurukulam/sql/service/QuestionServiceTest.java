package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private Practice exam;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private SQLPracticeService sqlExamService;

    @BeforeEach
    void before() throws IOException {
        cleanUp();
        exam = sqlExamService.create(getExam()).get();
    }

    private void cleanUp() {
        questionService.delete();
        sqlExamService.delete();
    }

    @AfterEach
    void after() {
        cleanUp();
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
        Assertions.assertNotNull(questionService.read(newQuestionId).get(), "Assert Created");
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

    Practice getExam() {
        final Practice exam = new Practice();
        exam.setName("Test Exam 1");
        exam.setDatabase(Database.POSTGRES);
        exam.setScript(TestUtil.getScript(exam));
        return exam;
    }
}
