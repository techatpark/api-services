package com.gurukulams.gurukulam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Syllabus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type Syllabus service.
 */
@Service
public class SyllabusService {

    /**
     * Validator.
     */
    private final Validator validator;
    /**
     * this creates connection functionalities.
     */
    private final DataSource dataSource;
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * initializes.
     * @param adataSource the data source
     * @param avalidator the validator
     * @param ajdbcTemplate the jdbc template.
     */
    public SyllabusService(final Validator avalidator,
                           final DataSource adataSource,
                           final JdbcTemplate ajdbcTemplate) {

        this.validator = avalidator;
        this.dataSource = adataSource;
        this.jdbcTemplate = ajdbcTemplate;
    }

    /**
     * Maps the data from and to the database. return syllabus.
     */
    private final RowMapper<Syllabus> rowMapper = (rs, rowNum) -> {
        final Syllabus syllabus = new Syllabus();
        syllabus.setId(rs.getLong("id"));
        syllabus.setTitle(rs.getString("title"));
        syllabus.setDescription(rs.getString("description"));

        return syllabus;
    };

    /**
     * Create optional.
     * @param id id
     * @param title the title
     * @param description the description
     * @param <T> the t
     * @return the optional
     */
    public <T extends Syllabus> Optional<Syllabus> create(final long id,
                                                     final String title,
                                                     final String description)
        throws JsonProcessingException {
        final SimpleJdbcInsert insert =
                new SimpleJdbcInsert(dataSource).withTableName("syllabus")
                        .usingGeneratedKeyColumns("id")
                        .usingColumns("syllabusId", "title", "description");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("syllabusId", id);
        valueMap.put("title", Syllabus.getTitle());
        valueMap.put("description", Syllabus.getDescription());
        final Number syllabusId = insert.executeAndReturnKey(valueMap);
        return read(syllabusId.intValue());
    }

    /**
     * Read optional.
     * @param <T> the t
     * @param id the id
     * @return the optional
     */
    public <T extends Syllabus> Optional<T> read(final long id) {
        final String query = "SELECT id, title, description"
                + "FROM syllabus WHERE id = ?";


        try {
            return (Optional<T>) Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * delete all records from syllabus with the title.
     *
     * @param id
     * @return successflag boolean
     */
    public Boolean deleteById(final long id) {
        final String query =
                "DELETE FROM syllabus WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * Update note optional.
     *
     * @param id       the id
     * @param title the title
     * @param description the description
     * @param <T> the t
     * @return the optional
     */
    public  <T extends Syllabus> Optional<T> update(final long id,
                                                    final String title,
                                                    final String description)
            throws JsonProcessingException {

        final String query =
                "UPDATE syllabus SET title = ?,"
                        + "description = ? WHERE id = ?";
        final Integer updatedRows =
                jdbcTemplate.update(query, Syllabus.getTitle(),
                        Syllabus.getDescription(), id);
        return updatedRows == 0 ? null : read(id);

    }
}
