package com.gurukulams.core.service;

import com.gurukulams.core.model.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GradeService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(GradeService.class);

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * this is the constructor.
     * @param ajdbcTemplate a jdbcTemplate
     * @param adataSource a dataSource
     */
    public GradeService(final JdbcTemplate ajdbcTemplate,
                        final DataSource adataSource) {
        this.jdbcTemplate = ajdbcTemplate;
        this.dataSource = adataSource;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private Grade rowMapper(final ResultSet rs,
                            final Integer rowNum)
            throws SQLException {
        Grade grade = new Grade((long)
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getString("created_by"),
                rs.getObject("modified_at", LocalDateTime.class),
                rs.getString("modified_by"));

        return grade;
    }

    /**
     * creates new grade.
     * @param userName the userName
     * @param grade the grade
     * @return grade optional
     */
    public Grade create(final String userName,
                        final Grade grade) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("grades").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();


        valueMap.put("title", grade.title());
        valueMap.put("description", grade.description());
        valueMap.put("created_by", userName);

        final Number gradeId = insert.executeAndReturnKey(valueMap);
        final Optional<Grade> createdGrade =
                read(userName, gradeId.longValue());

        logger.info("grade Created {}", gradeId);

        return createdGrade.get();
    }

    /**
     * reads from grade.
     * @param id the id
     * @param userName the userName
     * @return gread optional
     */
    public Optional<Grade> read(final String userName,
                                final Long id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM grades "
                + "WHERE id = ?";

        try {
            final Grade p = jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the grade.
     * @param id the id
     * @param userName the userName
     * @param grade the grade
     * @return grade optional
     */
    public Grade update(final Long id,
                        final String userName,
                        final Grade grade) {
        logger.debug("Entering update for Grade {}", id);
        final String query = "UPDATE grades SET title=?,"
                + "description=?,modified_by=? WHERE id=?";
        final Integer updatedRows =
                jdbcTemplate.update(query, grade.title(),
                        grade.description(), userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Grade not found");
        }
        return read(userName, id).get();
    }

    /**
     * delete the grade.
     * @param id the id
     * @param userName the userName
     * @return grade optional
     */
    public Boolean delete(final String userName,
                          final Long id) {
        final String query = "DELETE FROM grades WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * list the grade.
     * @param userName the userName
     * @return grade optional
     */
    public List<Grade> list(final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM grades";
        return jdbcTemplate.query(query, this::rowMapper);

    }

    /**
     * Cleaning up all boards.
     * @param boardId the board Id
     * @return no.of grade deleted
     */
    public Integer deleteAll(final Long boardId) {
        final String query = "DELETE FROM grades WHERE board_id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, boardId);
        return updatedRows;
    }

    /**
     * Cleaning up all boards.
     *
     * @return no.of grade deleted
     */
    public Integer deleteAllForTestCase() {
        final String query = "DELETE FROM grades";
        return jdbcTemplate.update(query);
    }
}
