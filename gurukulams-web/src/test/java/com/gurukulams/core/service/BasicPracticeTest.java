package com.gurukulams.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.QuestionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Question service test.
 */
@SpringBootTest
class BasicPracticeTest {

    /**
     * This is a question.
     */
    private static final String QUESTION = "This is a question";

    /**
     * This is a answer.
     */
    private static final String ANSWER = "This is an answer";

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
    private PracticeService practiceService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
        practice =
                practiceService.create("basic", "user", getPractice()).get();
    }

    private void cleanUp() {
        questionService.delete();
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
    void testCreateAQuestion() throws JsonProcessingException {
        final Question question = questionService.create("maths",
                QuestionType.MULTI_LINE, getQuestion(), "tom", "/chap1").get();
        assertEquals(QUESTION, question.getQuestion(), "Created Successfully");
    }

    @Test
    void testCreate() {
        final Question question = questionService.create(practice.getId(),
                QuestionType.MULTI_LINE, getQuestion(),"tom").get();
        assertEquals(QUESTION, question.getQuestion(), "Created Successfully");

    }

    /**
     * Test update.
     */
    @Test
    void testUpdate() {
        Question question = questionService
                .create(practice.getId(), QuestionType.MULTI_LINE,
                        getQuestion(),"tom").get();
        question.setQuestion("Updated Query");
        final Integer newQuestionId = question.getId();
        question = questionService
                .update(practice.getId(), QuestionType.MULTI_LINE,
                        newQuestionId, question).get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }


    @Test
    void testUpdateAQuestion() throws JsonProcessingException {

        Question question = questionService.create("maths",
                QuestionType.MULTI_LINE, getQuestion(), "tom","chap1").get();
        question.setQuestion("Updated Query");
        final Integer newQuestionId = question.getId();
        question = questionService
                .update("maths", QuestionType.MULTI_LINE,
                        newQuestionId, question, "chap1").get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }

    /**
     * Test read.
     */
    @Test
    void testRead() {
        final Question question = questionService.create(practice.getId(),
                QuestionType.MULTI_LINE, getQuestion(), "tom").get();
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
                    questionService
                            .create(practice.getId(), QuestionType.MULTI_LINE,
                                    getQuestion(),"tom").get();
            final Integer newQuestionId = question.getId();
            questionService.delete(newQuestionId);
            questionService.read(newQuestionId).get();
        });
    }


    /**
     * Test delete.
     */
    @Test
    void testDeleteWithQType() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            final Question question =
                    questionService
                            .create(practice.getId(), QuestionType.MULTI_LINE,
                                    getQuestion(),"tom").get();
            final Integer newQuestionId = question.getId();
            questionService.deleteAQuestion(newQuestionId, QuestionType.MULTI_LINE);
            questionService.read(newQuestionId).get();
        });
    }

    /**
     * Test list.
     */
    @Test
    void testList() {
        questionService.create(practice.getId(), QuestionType.MULTI_LINE,
                getQuestion(),"tom").get();
        final Question question2 = getQuestion();
        questionService
                .create(practice.getId(), QuestionType.MULTI_LINE, question2,"tom");
        assertEquals(2, questionService.list(1, 2).size(), "Test Listing");
        assertEquals(1, questionService.list(1, 1).size(),
                "Test Listing with restricted page");
    }


    @Test
    void testListQuestionBank() throws JsonProcessingException {

        Optional<Question> question =
                questionService.create("maths",
                        QuestionType.MULTI_LINE, getQuestion(), "tom", "chap1");

        assertEquals(1, questionService.list( "user","maths","chap1").size(), "Test " +
                "Listing");

    }

    /**
     * Test list with exam id.
     */
    @Test
    void testListWithExamId() {
        final Question question = questionService.create(practice.getId(),
                QuestionType.MULTI_LINE, getQuestion(), "tom").get();
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
        question.setQuestion(QUESTION);
        question.setAnswer(ANSWER);
        question.setType(QuestionType.MULTI_LINE);
        return question;
    }

    /**
     * Gets practice.
     *
     * @return the practice
     */
    Practice getPractice() {
        final Practice exam = new Practice();
        exam.setName("Test Practice");
        exam.setDescription("Test Practice description");
        return exam;
    }


}
