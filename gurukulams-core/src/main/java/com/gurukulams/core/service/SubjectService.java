package com.gurukulams.core.service;

import com.gurukulams.core.model.Subject;
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
 * The type Subjects service.
 */
@Service
public class SubjectService {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(SubjectService.class);

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
    public SubjectService(final JdbcTemplate ajdbcTemplate,
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
    private Subject rowMapper(final ResultSet rs,
                               final Integer rowNum)
        throws SQLException {
        Subject subject = new Subject((long)
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getObject("created_at", LocalDateTime.class),
        rs.getString("created_by"),
        rs.getObject("modified_at", LocalDateTime.class),
        rs.getString("modified_by"));

        return subject;
    }

    /**
     * creates new subject.
     * @param userName the userName
     * @param subject the syllabus
     * @return syllabus optional
     */
    public Subject create(final String userName,
                                     final Subject subject) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("subjects").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("title", subject.title());
        valueMap.put("description", subject.description());
        valueMap.put("created_by", userName);

        final Number subjectId = insert.executeAndReturnKey(valueMap);
        final Optional<Subject> createdSubjects =
                read(userName, subjectId.longValue());

        logger.info("Subject Created {}", subjectId);

        return createdSubjects.get();
    }
    /**
     * reads from syllabus.
     * @param id the id
     * @param userName the userName
     * @return question optional
     */
    public Optional<Subject> read(final String userName, final Long id) {
        final String query = "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM subjects "
                + "WHERE id = ?";



        try {
            final Subject p = jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the subjects.
     * @param id the id
     * @param userName the userName
     * @param subject the subjects
     * @return question optional
     */
    public Subject update(final Long id,
                                     final String userName,
                                      final Subject subject) {
        logger.debug("Entering update for Subject {}", id);
        final String query = "UPDATE subjects SET title=?,"
                + "description=?,modified_by=? WHERE id=?";
        final Integer updatedRows =
                jdbcTemplate.update(query, subject.title(),
                        subject.description(), userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Syllabus not found");
        }
        return read(userName, id).get();
    }
    /**
     * delete the subject.
     * @param id the id
     * @param userName the userName
     * @return question optional
     */
    public Boolean delete(final String userName, final Long id) {
        final String query = "DELETE FROM subjects WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }
    /**
     * list the subject.
     * @param userName the userName
     * @return question optional
     */
    public List<Subject> list(final String userName) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM subjects";
        return jdbcTemplate.query(query, this::rowMapper);

    }

    /**
     * Adds subject to grade and board.
     * @param userName the userName
     * @param boardId the gradeId
     * @param gradeId the gradeId
     * @param subjectId the syllabusId
     * @return grade optional
     */
    public boolean addToBoardsGrades(final String userName, final Long boardId,
                                     final Long gradeId,
                                     final Long subjectId) {
        // Insert to boards_grades
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("boards_grades_subjects")
                .usingColumns("board_id", "grade_id", "subject_id");

        // Fill the values
        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("board_id", boardId);
        valueMap.put("grade_id", gradeId);
        valueMap.put("subject_id", subjectId);

        int noOfRowsInserted = insert.execute(valueMap);

        return noOfRowsInserted == 1;
    }

    /**
     * list the subject by grade and board.
     * @param userName the userName
     * @param boardId the grade
     * @param gradeId the grade
     * @return syllabus optional
     */
    public List<Subject> list(final String userName, final Long boardId,
                               final Long gradeId) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM subjects "
                + "JOIN boards_grades_subjects ON subjects.id "
                + "= boards_grades_subjects.subject_id "
                + " where boards_grades_subjects.grade_id = ? "
                + "AND "
                + " boards_grades_subjects.board_id = ? ";
        return jdbcTemplate.query(query, this::rowMapper, gradeId, boardId);
    }

    /**
     * Cleaning up all subjects.
     *
     */
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM boards_grades_subjects");
        jdbcTemplate.update("DELETE FROM subjects");

    }
}
