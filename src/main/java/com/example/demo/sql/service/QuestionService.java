package com.example.demo.sql.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.sql.model.Question;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    /**
     * inserting into table - question.
     * 
     * @param question
     * @return question
     */
    public Question create(final Question question) {
        return null;
    }

    /**
     * reads from table question.
     * 
     * @param id
     * @return namespace
     */
    public Optional<Question> read(final Integer id) {
        return null;
    }

    /**
     * update table question.
     * 
     * @param id
     * @param question
     * @return question
     */
    public Question update(final Integer id, final Question question) {
        return null;
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
        return false;
    }

    /**
     * delete question.
     * 
     * @param pageNumber
     * @param pageSize
     * @return question
     */
    public List<Question> lists(final Integer pageNumber, final Integer pageSize) {
        return null;
    }

}
