package com.example.demo.sql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.sql.model.Question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionServiceTest {


    private static final String Query1 = "Query for all tables.";
    private static final String Answer1 = "SELECT * FROM exams;";

    @Autowired
    QuestionService questionService;

    @BeforeEach
    void before() {
        questionService.delete();
    }

    @Test
    void testCreate() {
        Question question = questionService.create(getQuestion()).get();
        assertEquals(Query1, question.getQuestion(), "Created Successfully");
    }

    
    @Test
    void testUpdate() {
        Question question = questionService.create(getQuestion()).get();
        question.setQuestion("Updated Query");
        Integer newQuestionId = question.getId();
        question = questionService.update(newQuestionId, question).get();
        assertEquals("Updated Query", question.getQuestion(), "Updated");
    }

    @Test
    void testRead() {
        Question question = questionService.create(getQuestion()).get();
        Integer newQuestionId = question.getId();
        assertNotNull(questionService.read(newQuestionId).get(), "Assert Created");
    }

    @Test
    void testList() {
        questionService.create(getQuestion()).get();
        Question question2 = getQuestion();
        questionService.create(question2);
        assertEquals(2, questionService.list(1, 2).size(), "Test Listing");
        assertEquals(1, questionService.list(1, 1).size(), "Test Listing with restricted page");
    }

    Question getQuestion() {
        Question question = new Question();
        question.setExamId(33);
        question.setQuestion(Query1);
        question.setAnswer(Answer1);
        return question;
    }
}