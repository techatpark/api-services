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
import java.util.Locale;
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
     * @param locale the locale
     * @return syllabus optional
     */
    public Syllabus create(final String userName,
                                     final Locale locale,
                                     final Syllabus syllabus) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("syllabus").usingGeneratedKeyColumns("id")
                .usingColumns("title", "description", "created_by");

        final Map<String, Object> valueMap = new HashMap<>();

        valueMap.put("title", syllabus.title());
        valueMap.put("description", syllabus.description());
        valueMap.put("created_by", userName);

        final Number syllabusId = insert.executeAndReturnKey(valueMap);

        if (locale != null) {
            valueMap.put("syllabus_id", syllabusId);
            valueMap.put("locale", locale.getLanguage());
            createLocalizedSyllabus(valueMap);
        }
        final Optional<Syllabus> createdSyllabus =
                read(userName, null, syllabusId.longValue());

        logger.info("Syllabus Created {}", syllabusId);

        return createdSyllabus.get();
    }

    /**
     * Create Localized syllabus.
     * @param valueMap
     * @return noOfSyllabus
     */
    private int createLocalizedSyllabus(final Map<String, Object> valueMap) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("syllabus_localized")
                .usingColumns("syllabus_id", "locale", "title", "description")
                .execute(valueMap);
    }

    /**
     * reads from syllabus.
     * @param id the id
     * @param userName the userName
     * @param locale the locale
     * @return question optional
     */
    public Optional<Syllabus> read(final String userName,
                                   final Locale locale,
                                   final Long id) {


        final String query = locale == null
                ? "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM syllabus "
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
                + "FROM SYLLABUS b "
                + "LEFT JOIN SYLLABUS_LOCALIZED bl "
                + "ON b.ID = bl.SYLLABUS_ID "
                + "WHERE b.ID = ? "
                + "AND (bl.LOCALE IS NULL "
                + "OR bl.LOCALE = ? OR "
                + "b.ID NOT IN "
                + "(SELECT SYLLABUS_ID FROM SYLLABUS_LOCALIZED "
                + "WHERE SYLLABUS_ID=b.ID AND LOCALE = ?))";

        try {
            final Syllabus p = locale == null ? jdbcTemplate
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
     * update the syllabus.
     * @param id the id
     * @param userName the userName
     * @param syllabus the syllabus
     * @param locale the locale
     * @return question optional
     */
    public Syllabus update(final Long id,
                                     final String userName,
                                      final Locale locale,
                                      final Syllabus syllabus) {
        logger.debug("Entering update for Syllabus {}", id);
        final String query = locale == null
                ? "UPDATE syllabus SET title=?,"
                + "description=?,modified_by=? WHERE id=?"
                : "UPDATE syllabus SET modified_by=? WHERE id=?";
        Integer updatedRows = locale == null
                ? jdbcTemplate.update(query, syllabus.title(),
                syllabus.description(), userName, id)
                : jdbcTemplate.update(query, userName, id);
        if (updatedRows == 0) {
            logger.error("Update not found", id);
            throw new IllegalArgumentException("Syllabus not found");
        } else if (locale != null) {
            updatedRows = jdbcTemplate.update(
                    "UPDATE syllabus_localized SET title=?,locale=?,"
                            + "description=? WHERE syllabus_id=? AND locale=?",
                    syllabus.title(), locale.getLanguage(),
                    syllabus.description(), id, locale.getLanguage());
            if (updatedRows == 0) {
                final Map<String, Object> valueMap = new HashMap<>(4);
                valueMap.put("syllabus_id", id);
                valueMap.put("locale", locale.getLanguage());
                valueMap.put("title", syllabus.title());
                valueMap.put("description", syllabus.description());
                createLocalizedSyllabus(valueMap);
            }
        }
        return read(userName, locale, id).get();
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
     * @param locale the locale
     * @return question optional
     */
    public List<Syllabus> list(final String userName,
                               final Locale locale) {
        final String query = locale == null
                ? "SELECT id,title,description,created_by,"
                + "created_at, modified_at, modified_by FROM syllabus"
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
                + "FROM syllabus b "
                + "LEFT JOIN SYLLABUS_LOCALIZED bl "
                + "ON b.ID = bl.SYLLABUS_ID "
                + "WHERE bl.LOCALE IS NULL "
                + "OR bl.LOCALE = ? OR "
                + "b.ID NOT IN "
                + "(SELECT SYLLABUS_ID FROM SYLLABUS_LOCALIZED "
                + "WHERE SYLLABUS_ID=b.ID AND LOCALE = ?)";
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
     * Cleaning up all institutes.
     *
     */
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM syllabus_localized");
        jdbcTemplate.update("DELETE FROM syllabus");
    }
}
