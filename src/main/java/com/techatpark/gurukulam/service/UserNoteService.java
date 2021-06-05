package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.UserNote;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type User note service.
 */
@Service
public class UserNoteService {
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
     * @param aJdbcTemplate the a jdbc template
     * @param aDataSource   the a data source
     */
    public UserNoteService(final JdbcTemplate aJdbcTemplate,
                           final DataSource aDataSource) {
        this.jdbcTemplate = aJdbcTemplate;
        this.dataSource = aDataSource;
    }

    /**
     * Create optional.
     *
     * @param userNote the user note
     * @return the optional
     */
    public Optional<UserNote> create(final UserNote userNote) {
        final SimpleJdbcInsert insert =
                new SimpleJdbcInsert(dataSource).withTableName("user_notes")
                        .usingGeneratedKeyColumns("id")
                        .usingColumns("on_type", "on_instance", "on_section",
                                "text", "notes");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("on_type", userNote.getOnType());
        valueMap.put("on_instance", userNote.getOnInstance());
        valueMap.put("on_section", userNote.getOnSection());
        valueMap.put("text", userNote.getText());
        valueMap.put("notes", userNote.getNotes());
        final Number id = insert.executeAndReturnKey(valueMap);
        return read(id.intValue());
    }

    /**
     * Read optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<UserNote> read(final Integer id) {
        final String query =
                "SELECT id,on_type,on_instance,on_section,text,notes FROM "
                        + "user_notes WHERE"
                        + " id = ?";
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(query, new Object[]{id}, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * Maps the data from and to the database. return question.
     */
    private final RowMapper<UserNote> rowMapper = (rs, rowNum) -> {
        final UserNote userNote = new UserNote();
        userNote.setId(rs.getInt("id"));
        userNote.setOnType(rs.getString("on_type"));
        userNote.setOnInstance(rs.getString("on_instance"));
        userNote.setOnSection(rs.getString("on_section"));
        userNote.setText(rs.getString("text"));
        userNote.setNotes(rs.getString("notes"));

        return userNote;
    };
}