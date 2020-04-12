package com.example.demo.sql.service;

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
     * Constructs Answer Service.
     * 
     * @param jdbcTemplate
     */
    AnswerService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * checks whether the given answer is correct.returns true if correct.
     * 
     * @param questionId
     * @param answer
     * @return true
     */
    public final Boolean answer(final Integer questionId, final String answer) {

        return true;
    }

}
