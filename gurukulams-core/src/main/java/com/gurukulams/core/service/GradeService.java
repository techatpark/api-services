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
import java.util.Locale;
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
     *
     * @param ajdbcTemplate a jdbcTemplate
     * @param adataSource   a dataSource
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
     *
     * @param userName the userName
     * @param grade    the grade
     * @param locale the locale
     * @return grade optional
     */
    public Grade create(final String userName,
                        final Locale locale,
                        final Grade grade) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("grades").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();


        valueMap.put("title", grade.title());
        valueMap.put("description", grade.description());
        valueMap.put("created_by", userName);

        final Number gradeId = insert.executeAndReturnKey(valueMap);

        if (locale != null) {
            valueMap.put("grade_id", gradeId);
            valueMap.put("locale", locale.getLanguage());
            createLocalizedGrade(valueMap);
        }

        final Optional<Grade> createdGrade =
                read(userName, null, gradeId.longValue());

        logger.info("grade Created {}", gradeId);

        return createdGrade.get();
    }


    /**
     * Create Localized Grade.
     * @param valueMap
     * @return noOfGrades
     */
    private int createLocalizedGrade(final Map<String, Object> valueMap) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("grades_localized")
                .usingColumns("grade_id", "locale", "title", "description")
                .execute(valueMap);
    }


    /**
     * reads from grade.
     *
     * @param id       the id
     * @param userName the userName
     * @param locale
     * @return gread optional
     */
    public Optional<Grade> read(final String userName,
                                final Locale locale,
                                final Long id) {
        final String query = locale == null
                ? "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM grades "
                + "WHERE id = ?"
                : "SELECT DISTINCT b.ID, "
                + "CASE WHEN bl.LOCALE = ? "
                + "THEN bl.TITLE "
                + "ELSE b.TITLE "
                + "END AS TITLE, "
                + "CASE WHEN bl.LOCALE = ? "
                + "THEN bl.DESCRIPTION "
                + "ELSE b.DESCRIPTION "
                + "END AS DESCRIPTION,"
                + "created_by,created_at, modified_at, modified_by "
                + "FROM GRADES b "
                + "LEFT JOIN GRADES_LOCALIZED bl "
                + "ON b.ID = bl.GRADE_ID "
                + "WHERE b.ID = ? "
                + "AND (bl.LOCALE IS NULL "
                + "OR bl.LOCALE = ? OR "
                + "b.ID NOT IN "
                + "(SELECT GRADE_ID FROM GRADES_LOCALIZED "
                + "WHERE GRADE_ID=b.ID AND LOCALE = ?))";


        try {
            final Grade p = locale == null ? jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper)
                    : jdbcTemplate
                    .queryForObject(query, new Object[]{
                                    locale.getLanguage(),
                                    locale.getLanguage(),
                                    id,
                                    locale.getLanguage(),
                                    locale.getLanguage()},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    /**
     * update the grade.
     *
     * @param id       the id
     * @param userName the userName
     * @param grade    the grade
     * @param locale
     * @return grade optional
     */
    public Grade update(final Long id,
                        final String userName,
                        final Locale locale,
                        final Grade grade) {
        logger.debug("Entering update for Grade {}", id);
        final String query = locale == null
                ? "UPDATE grades SET title=?,"
                + "description=?,modified_by=? WHERE id=?"
                : "UPDATE grades SET modified_by=? WHERE id=?";
        Integer updatedRows =  locale == null
                ? jdbcTemplate.update(query, grade.title(),
                grade.description(), userName, id)
                : jdbcTemplate.update(query, userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Grade not found");
        } else if (locale != null) {
            updatedRows = jdbcTemplate.update(
                    "UPDATE grades_localized SET title=?,locale=?,"
                            + "description=? WHERE grade_id=? AND locale=?",
                    grade.title(), locale.getLanguage(),
                    grade.description(), id, locale.getLanguage());
            if (updatedRows == 0) {
                final Map<String, Object> valueMap = new HashMap<>(4);
                valueMap.put("grade_id", id);
                valueMap.put("locale", locale.getLanguage());
                valueMap.put("title", grade.title());
                valueMap.put("description", grade.description());
                createLocalizedGrade(valueMap);
            }
        }
        return read(userName, locale, id).get();
    }


    /**
     * delete the grade.
     *
     * @param id       the id
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
     *
     * @param userName the userName
     * @param locale
     * @return grade optional
     */
    public List<Grade> list(final String userName,
                            final Locale locale) {
        final String query = locale == null
                ? "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM grades"
                : "SELECT DISTINCT b.ID, "
                + "CASE WHEN bl.LOCALE = ? "
                + "THEN bl.TITLE "
                + "ELSE b.TITLE "
                + "END AS TITLE, "
                + "CASE WHEN bl.LOCALE = ? "
                + "THEN bl.DESCRIPTION "
                + "ELSE b.DESCRIPTION "
                + "END AS DESCRIPTION,"
                + "created_by,created_at, modified_at, modified_by "
                + "FROM grades b "
                + "LEFT JOIN GRADES_LOCALIZED bl "
                + "ON b.ID = bl.GRADE_ID "
                + "WHERE bl.LOCALE IS NULL "
                + "OR bl.LOCALE = ? OR "
                + "b.ID NOT IN "
                + "(SELECT GRADE_ID FROM GRADES_LOCALIZED "
                + "WHERE GRADE_ID=b.ID AND LOCALE = ?)";
        return locale == null
                ? jdbcTemplate.query(query, this::rowMapper)
                : jdbcTemplate
                .query(query, new Object[]{
                                locale.getLanguage(),
                                locale.getLanguage(),
                                locale.getLanguage(),
                                locale.getLanguage()},
                        this::rowMapper);

    }


    /**
     * Adds grade to board.
     *
     * @param userName the userName
     * @param gradeId  the gradeId
     * @param boardId  the boardId
     * @return grade optional
     */
    public boolean addToBoard(final String userName, final Long gradeId,
                              final Long boardId) {
        // Insert to boards_grades
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("boards_grades")
                .usingColumns("board_id", "grade_id");

        // Fill the values
        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("board_id", boardId);
        valueMap.put("grade_id", gradeId);

        int noOfRowsInserted = insert.execute(valueMap);

        return noOfRowsInserted == 1;
    }

    /**
     * list the grade by board.
     *
     * @param userName the userName
     * @param boardId  the boardId
     * @param locale
     * @return grade optional
     */
    public List<Grade> list(final String userName,
                            final Locale locale,
                            final Long boardId) {
        String query = "SELECT id,title,description,created_by,"
                + "created_at,modified_at,modified_by FROM grades "
                + "JOIN boards_grades ON grades.id=boards_grades.grade_id"
                + " where boards_grades.board_id = ?";
        return jdbcTemplate.query(query, this::rowMapper, boardId);
    }

    /**
     * Cleaning up all grades.
     */
    public void deleteAllForTestCase() {
        jdbcTemplate.update("DELETE FROM boards_grades");
        jdbcTemplate.update("DELETE FROM boards_grades_subjects");
        jdbcTemplate.update("DELETE FROM grades_localized");
        jdbcTemplate.update("DELETE FROM grades");
    }
}
