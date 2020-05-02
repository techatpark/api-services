package com.example.demo.sql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.sql.model.Question;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    /**
     * * this is used to execute a connection with a database.
     * 
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * list of questions.
     */
    private final List<Question> questions;

    /**
     * @param jdbcTemplate initiate list.
     */
    QuestionService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.questions = new ArrayList<>();
    }

    /**
     * inserting into table - question.
     * 
     * @param question
     * @return question
     */
    public Question create(final Question question) {
        question.setId(this.questions.size() + 1);
        this.questions.add(question);
        // try {
        //     jdbcTemplate.queryForObject(question.getAnswer(), Integer.class);
            

        // } catch (Exception e) {
        //     throw new NotValidAnswerException("your answer is not valid quey", e);
        // }
        return question;
    }

    /**
     * reads from table question.
     * 
     * @param id
     * @return namespace
     */
    public Optional<Question> read(final Integer id) {
        Optional<Question> question = this.questions.stream().filter(que -> id == que.getId()).findFirst();
        return question;
    }

    /**
     * update table question.
     * 
     * @param id
     * @param question
     * @return question
     */
    public Question update(final Integer id, final Question question) {
        this.questions.set(id - 1, question);
        return question;
    }

    /**
     * Soft Delete a row with given id.
     * 
     * @param b
     * @return question.
     */
    public Boolean delete(final boolean b) {
        return null;
    }

    /**
     * soft delete all from namespace.
     * 
     * @return question
     */
    public Boolean delete() {
        return delete(false);
    }

    /**
     * delete question.
     * 
     * @param id
     * @return question
     */
    public boolean delete(final Integer id) {
        return this.questions.remove(id - 1) != null;
    }

    /**
     * delete question.
     * 
     * @param pageNumber
     * @param pageSize
     * @return question
     */
    public List<Question> lists(final Integer pageNumber, final Integer pageSize) {
        return this.questions;
    }

}
