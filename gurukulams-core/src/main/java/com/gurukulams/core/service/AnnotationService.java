package com.gurukulams.core.service;

import com.gurukulams.core.model.Annotation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * The type User note service.
 */
@Service
public class AnnotationService {
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this creates connection functionalities.
     */
    private final DataSource dataSource;
    /**
     * Maps the data from and to the database. return question.
     */
    private final RowMapper<Annotation> rowMapper = (rs, rowNum) -> {
        final Annotation annotation = new Annotation();
        annotation.setId((UUID) rs.getObject("id"));
        annotation.setOnType(rs.getString("on_type"));
        annotation.setOnInstance(rs.getString("on_instance"));
        annotation.setOnSection(rs.getString("on_section"));
        annotation.setText(rs.getString("text"));
        annotation.setNote(rs.getString("note"));

        return annotation;
    };

    /**
     * initializes.
     *
     * @param aJdbcTemplate the a jdbc template
     * @param aDataSource   the a data source
     */
    public AnnotationService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDataSource) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDataSource;
    }

    /**
     * Create optional.
     *
     * @param userName user name
     * @param annotation the user note
     * @param locale tha language
     * @return the optional
     */
    public Optional<Annotation> create(final Annotation annotation,
                                     final Locale locale,
                                     final String userName) {
        final SimpleJdbcInsert insert =
                new SimpleJdbcInsert(dataSource).withTableName("annotations")

                        .usingColumns("id", "created_by",
                                "on_type", "on_instance",
                                "on_section",
                                "text", "note");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("created_by", userName);
        valueMap.put("on_type", annotation.getOnType());
        valueMap.put("on_instance", annotation.getOnInstance());
        valueMap.put("on_section", annotation.getOnSection());
        valueMap.put("text", annotation.getText());
        valueMap.put("note", annotation.getNote());
        final UUID id = UUID.randomUUID();
        valueMap.put("id", id);
        insert.execute(valueMap);
        return read(id, locale);
    }

    /**
     * Read optional.
     *
     * @param id the id
     * @param locale tha language
     * @return the optional
     */
    public Optional<Annotation> read(final UUID id,
                                   final Locale locale) {
        final String query =
                "SELECT id,on_type,on_instance,on_section,text,"
                        + "note FROM "
                        + "annotations WHERE"
                        + " id = ?";
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * List list.
     *
     * @param userName   user name
     * @param onInstance the on instance
     * @param onSection  the on section
     * @param locale tha language
     * @return the list
     */
    public List<Annotation> searchAnnotations(final String userName,
                                      final Locale locale,
                                      final String onInstance,
                                      final String onSection) {
        final String query = "SELECT id,on_type,on_instance,on_section,"
                + "text,note FROM "
                + "annotations WHERE"
                + " on_instance = ? and on_section = ? and created_by = ?";
        return jdbcTemplate.query(query, rowMapper, onInstance,
                onSection, userName);
    }

    /**
     * Update note optional.
     *
     * @param id       the id
     * @param annotation the user note
     * @param locale tha language
     * @return the optional
     */
    public Optional<Annotation> updateNote(final UUID id,
                                         final Locale locale,
                                         final Annotation annotation) {
        final String query =
                "UPDATE annotations SET "
                        + "text = ?, note = ? WHERE id = ?";
        final Integer updatedRows =
                jdbcTemplate.update(query,
                        annotation.getText(), annotation.getNote(), id);
        return updatedRows == 0 ? null : read(id, locale);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @param locale tha language
     */
    public Boolean delete(final UUID id,
                          final Locale locale) {
        final String query = "DELETE FROM annotations WHERE ID=?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }
}
