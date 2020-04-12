package com.example.demo.sql.service;

import java.util.Optional;

import com.example.demo.sql.model.Question;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    /**
     * * this is used to execute a connection with a database.
     * 
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * question Service.
     */
    private final QuestionService questionService;

    /**
     * Constructs Answer Service.
     * 
     * @param jdbcTemplate
     */
    AnswerService(final JdbcTemplate jdbcTemplate, final QuestionService questionService) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionService = questionService;
    }

    /**
     * checks whether the given answer is correct.returns true if correct.
     * 
     * @param questionId
     * @param answer
     * @return true
     */
    public final Boolean answer(final Integer questionId, final String answer) {
        Boolean isRigntAnswer = false;
        Optional<Question> question = questionService.read(questionId);
        if (question.isPresent()) {
            String verificationSQL = "select * from tableA minus select * from tableB";
        }
        return isRigntAnswer;
    }

}
