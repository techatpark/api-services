package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.Database;
import com.techatpark.gurukulam.model.Practice;
import com.techatpark.gurukulam.model.Question;
import com.techatpark.gurukulam.model.sql.SqlPractice;
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

/**
 * The type Question service test.
 */
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
     * variable to be used for testing.
     */
    private static final String TYPE = "Multiline";

    /**
     * Instance of Exam is used for testing Question.
     */
    private Practice practice;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Connection created with Question Service.
     */
    @Autowired
    private PracticeService sqlPracticeService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
        practice =
                sqlPracticeService.create("sql", "user", getPractice()).get();
    }

    private void cleanUp() {
        questionService.delete();
        sqlPracticeService.delete("sql");
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    /**
     * Test create.
     */
    @Test
    void testCreate() {
        final Question question = questionService.create(practice.getId(),
                TYPE, getQuestion()).get();
        assertEquals(QUERY1, question.getQuestion(), "Created Successfully");
    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        Question question = questionService.create(practice.getId(), TYPE,
                getQuestion()).get();
        question.setQuestion("Updated Query");
        final Integer newQuestionId = question.getId();
        question = questionService
                .update(practice.getId(), newQuestionId, question).get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }

    /**
     * Test read.
     */
    @Test
    void testRead() {
        final Question question = questionService.create(practice.getId(),
                TYPE, getQuestion()).get();
        final Integer newQuestionId = question.getId();
        Assertions.assertNotNull(questionService.read(newQuestionId).get(),
                "Assert Created");
    }

    /**
     * Test delete.
     */
    @Test
    void testDelete() {

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            final Question question =
                    questionService.create(practice.getId(), TYPE,
                            getQuestion()).get();
            final Integer newQuestionId = question.getId();
            questionService.delete(newQuestionId);
            questionService.read(newQuestionId).get();
        });
    }

    /**
     * Test list.
     */
    @Test
    void testList() {
        questionService.create(practice.getId(), TYPE, getQuestion()).get();
        final Question question2 = getQuestion();
        questionService.create(practice.getId(), TYPE, question2);
        assertEquals(2, questionService.list(1, 2).size(), "Test Listing");
        assertEquals(1, questionService.list(1, 1).size(),
                "Test Listing with restricted page");
    }

    /**
     * Test list with exam id.
     */
    @Test
    void testListWithExamId() {
        final Question question = questionService.create(practice.getId(),
                TYPE, getQuestion()).get();
        final Integer newExamId = question.getExamId();
        assertNotNull(questionService.list("user", newExamId),
                "Assert Created");
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    Question getQuestion() {
        final Question question = new Question();
        question.setQuestion(QUERY1);
        question.setAnswer(ANSWER1);
        question.setType("Multiline");
        return question;
    }

    /**
     * Gets practice.
     *
     * @return the practice
     */
    SqlPractice getPractice() {
        final SqlPractice exam = new SqlPractice();
        exam.setName("Test Exam 1");
        exam.setDatabase(Database.POSTGRES);
        exam.setScript(TestUtil.getScript(exam));
        exam.setDescription("description");
        return exam;
    }
}
