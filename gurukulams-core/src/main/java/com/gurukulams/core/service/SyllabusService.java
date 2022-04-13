package com.gurukulams.core.service;

import com.gurukulams.core.model.Syllabus;
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

/**
 * The type Syllabus service.
 */
@Service
public class SyllabusService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(SyllabusService.class);

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
    public SyllabusService(final JdbcTemplate ajdbcTemplate,
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
    private Syllabus rowMapper(final ResultSet rs,
                               final Integer rowNum)
        throws SQLException {
        Syllabus syllabus = new Syllabus((long)
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getObject("created_at", LocalDateTime.class),
        rs.getString("created_by"),
        rs.getObject("modified_at", LocalDateTime.class),
        rs.getString("modified_by"));

        return syllabus;
    }

    /**
     * creates new syllabus.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return syllabus optional
     */
    public Syllabus create(final String userName,
                                     final Syllabus syllabus) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("syllabus").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("title", syllabus.title());
        valueMap.put("description", syllabus.description());
        valueMap.put("created_by", userName);

        final Number syllabusId = insert.executeAndReturnKey(valueMap);
        final Optional<Syllabus> createdSyllabus =
                read(userName, syllabusId.longValue());

        logger.info("Syllabus Created {}", syllabusId);

        return createdSyllabus.get();
    }
    /**
     * reads from syllabus.
     * @param id the id
     * @param userName the userName
     * @return question optional
     */
    public Optional<Syllabus> read(final String userName, final Long id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM syllabus "
                + "WHERE id = ?";


        try {
            final Syllabus p = jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the syllabus.
     * @param id the id
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Syllabus update(final Long id,
                                     final String userName,
                                      final Syllabus syllabus) {
        logger.debug("Entering update for Syllabus {}", id);
        final String query = "UPDATE syllabus SET title=?,"
                + "description=?,modified_by=? WHERE id=?";
        final Integer updatedRows =
                jdbcTemplate.update(query, syllabus.title(),
                        syllabus.description(), userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Syllabus not found");
        }
        return read(userName, id).get();
    }
    /**
     * delete the syllabus.
     * @param id the id
     * @param userName the userName
     * @return question optional
     */
    public Boolean delete(final String userName, final Long id) {
        final String query = "DELETE FROM syllabus WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }
    /**
     * list the syllabus.
     * @param userName the userName
     * @return question optional
     */
    public List<Syllabus> list(final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM syllabus";
        return jdbcTemplate.query(query, this::rowMapper);

    }

    /**
     * Adds grade to board.
     * @param userName the userName
     * @param gradeId the gradeId
     * @param syllabusId the syllabusId
     * @return grade optional
     */
    public boolean addToGrades(final String userName, final Long gradeId,
                              final Long syllabusId) {
        // Insert to boards_grades
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("grades_syllabus")
                .usingColumns("grade_id", "syllabus_id");

        // Fill the values
        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("grade_id", gradeId);
        valueMap.put("syllabus_id", syllabusId);

        int noOfRowsInserted = insert.execute(valueMap);

        return noOfRowsInserted == 1;
    }

    /**
     * list the grade by board.
     * @param userName the userName
     * @param gradeId the grade
     * @return syllabus optional
     */
    public List<Syllabus> list(final String userName, final Long gradeId) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM syllabus "
                + "JOIN grades_syllabus ON syllabus.id "
                + "= grades_syllabus.syllabus_id "
                + " where grades_syllabus.grade_id = ?";
        return jdbcTemplate.query(query, this::rowMapper, gradeId);
    }

    /**
     * Cleaning up all institutes.
     *
     */
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM grades_syllabus");
        jdbcTemplate.update("DELETE FROM syllabus");

    }
}