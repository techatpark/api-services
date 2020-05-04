package com.example.demo.sql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.sql.model.Exam;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public ExamService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    /**
     * Maps the data from and to the database. return namespace
     */
    private final RowMapper<Exam> rowMapper = (rs, rowNum) -> {
        final Exam exam = new Exam();
        exam.setId(rs.getInt("id"));
        exam.setName(rs.getString("name"));
        return exam;
    };

    /**
     * inserts data to database.
     * 
     * @param exam
     * @return exam
     */
    public Optional<Exam> create(final Exam exam) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("exams")
                .usingGeneratedKeyColumns("id").usingColumns("name");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("name", exam.getName());
        final Number id = insert.executeAndReturnKey(valueMap);
        return read(id.intValue());
    }

    /**
     * reads from the database with given id.
     * 
     * @param id
     * @return exam
     */
    public Optional<Exam> read(final Integer id) {
        final String query = "SELECT id,name FROM exams WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update database.
     * 
     * @TODO Soft Delete
     * @param exam
     * @param id
     * @return exam
     */
    public Optional<Exam> update(final Integer id, final Exam exam) {
        final String query = "UPDATE exams SET name = ? WHERE id = ?";
        Integer updatedRows = jdbcTemplate.update(query, exam.getName(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes from database.
     * 
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        String query = "DELETE FROM EXAMS WHERE ID=?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        return true;
    }

    /**
     * lists all from table.
     * 
     * @param pageNumber
     * @param pageSize
     * @return list
     */
    public List<Exam> list(final Integer pageNumber, final Integer pageSize) {
        String query = "SELECT id,name FROM exams";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }

}
