package com.gurukulams.service;

import com.gurukulams.core.model.Practice;
import com.gurukulams.core.service.PracticeService;
import com.gurukulams.core.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    private void cleanUp() {
        questionService.delete();
        practiceService.delete();
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    @Test
    void create() throws IOException {
        final Practice practice=practiceService.create("Basic", "Mani", null,anPractice()).get();
        Assertions.assertEquals("Java Practice", practice.getTitle(), "Created Successfully");
    }

    @Test
    void read() throws IOException{
        final Practice practice=practiceService.create("Basic", "Mani", null,anPractice()).get();
    }

    void update() throws IOException{

    }

    void delete() throws IOException{
    }

    /**
     * Gets practice.
     *
     * @return the practice
     */
    Practice anPractice() {
        final Practice practice = new Practice();
        practice.setId(null);
        practice.setTitle("Java Practice");
        practice.setDescription("Test Practice description");
        return practice;
    }
}
