package com.example.demo.sql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.sql.model.Question;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    /**
     * list of questions.
     */
    private final List<Question> questions;

    /**
     * initiate list.
     */
    QuestionService() {
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
