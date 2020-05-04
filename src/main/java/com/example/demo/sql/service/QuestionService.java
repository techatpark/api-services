package com.example.demo.sql.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.sql.model.Question;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this creates connection functionalities.
     */
    private final DataSource dataSource;

    /**
     * initializes.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public QuestionService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    /**
     * inserts data.
     * 
     * @param question
     * @return question
     */
    public Optional<Question> create(final Question question) {
        return null;
    }

    /**
     * reads from question with given id.
     * 
     * @param id
     * @return question
     */
    public Optional<Question> read(final Integer id) {
        return null;
    }

    /**
     * updates question with id.
     * 
     * @param id
     * @param question
     * @return question
     */
    public Optional<Question> update(final Integer id, final Question question) {
        return null;
    }


    /**
     * delete.
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        return false;
    }

/**
 * list of question.
 * @param pageNumber
 * @param pageSize
 * @return question
 */
    public List<Question> list(final Integer pageNumber, final Integer pageSize) {
        return null;
    }
}
