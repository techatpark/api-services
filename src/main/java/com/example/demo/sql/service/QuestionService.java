package com.example.demo.sql.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.sql.model.Question;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    /**
     * * this is used to execute a connection with a database.
     * 
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     * 
     */
    private final DataSource dataSource;

    /**
     * * Creates a device service for device related operations. *
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public QuestionService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into table - question.
     * 
     * @param question
     * @return question
     */
    public Question create(final Question question) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("question")
                .usingGeneratedKeyColumns("id").usingColumns("description", "answer");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("description", question.getDescription());
        valuesMap.put("answer", question.getAnswer());
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table question.
     * 
     * @param id
     * @return namespace
     */
    public Optional<Question> read(final Integer id) {
        final String query = "SELECT id, description, answer FROM question WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table question.
     * 
     * @param id
     * @param question
     * @return question
     */
    public Question update(final Integer id, final Question question) {
        final String query = "UPDATE question SET description = ?, answer = ? WHERE id = ? ";
        Integer updatedRows = jdbcTemplate.update(query, question.getDescription(), question.getAnswer(), id);
        return updatedRows == 0 ? null : read(id).get();
    }

    /**
     * Soft Delete a row with given id.
     * 
     * @param b
     * @return question.
     */
    public Boolean delete(final boolean b) {
        final String query = "DELETE FROM question WHERE id = ?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { b });
        return !(updatedRows == 0);
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
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return question
     * @throws SQLException
     */
    private Question mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setDescription(rs.getString("description"));
        question.setAnswer(rs.getString("answer"));
        return question;
    }
}
