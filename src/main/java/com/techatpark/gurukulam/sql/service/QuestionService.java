package com.techatpark.gurukulam.sql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.sql.model.Question;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
     * Maps the data from and to the database. return question.
     */
    private RowMapper<Question> rowMapper = (rs, rowNum) -> {
        final Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setExamId(rs.getInt("exam_id"));
        question.setQuestion(rs.getString("question"));
        question.setAnswer(rs.getString("answer"));
        return question;
    };

    /**
     * inserts data.
     * 
     * @param question
     * @param examId
     * @return question
     */
    public Optional<Question> create(final Integer examId, final Question question) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("questions")
                .usingGeneratedKeyColumns("id").usingColumns("exam_id", "question", "answer");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("exam_id", examId);
        valueMap.put("question", question.getQuestion());
        valueMap.put("answer", question.getAnswer());
        final Number id = insert.executeAndReturnKey(valueMap);
        return read(id.intValue());
    }

    /**
     * reads from question with given id.
     * 
     * @param id
     * @return question
     */
    public Optional<Question> read(final Integer id) {
        final String query = "SELECT id,exam_id,question,answer FROM questions WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * updates question with id.
     * 
     * @param id
     * @param question
     * @return question
     */
    public Optional<Question> update(final Integer id, final Question question) {
        final String query = "UPDATE questions SET exam_id = ?, question = ?, answer = ? WHERE id = ?";
        Integer updatedRows = jdbcTemplate.update(query, question.getExamId(), question.getQuestion(),
                question.getAnswer(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     * 
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        String query = "DELETE FROM questions WHERE ID=?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        return !(updatedRows == 0);
    }

    /**
     * Cleaning up all exams.
     * 
     * @return no.of exams deleted
     */
    public Integer delete() {
        final String query = "DELETE FROM questions";
        return jdbcTemplate.update(query);
    }

    /**
     * List questions of exam.
     * 
     * @param examId
     * @return quetions in given exam
     */
    public List<Question> list(final Integer examId) {
        String query = "SELECT id,exam_id,question,answer FROM questions WHERE exam_id = ?";
        return List.of(jdbcTemplate.queryForObject(query, new Object[] { examId }, rowMapper));
    }

    /**
     * list of question.
     * 
     * @param pageNumber
     * @param pageSize
     * @return question
     */
    public List<Question> list(final Integer pageNumber, final Integer pageSize) {
        String query = "SELECT id,exam_id,question,answer FROM questions";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }
}
