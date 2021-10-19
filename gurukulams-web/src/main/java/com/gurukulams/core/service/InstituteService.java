package com.gurukulams.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurukulams.core.model.Institute;
import com.gurukulams.core.model.Practice;
import com.gurukulams.core.model.sql.SqlPractice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Institute service.
 */
@Service
public final class InstituteService {

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    public InstituteService(
            JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private Institute rowMapper(final ResultSet rs,
                                             final Integer rowNum)
            throws SQLException {

        LocalDate calendarDate = rs.getDate("created_at")
                .toLocalDate();

        Institute institute = new Institute((long) rs.getInt("id"), rs.getString(
                "title"), rs.getString("description"),
                calendarDate, rs.getString("created_by"));

        return institute;
    }
    /**
     * inserts data.
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public Institute create(final String userName,
                                      final Institute institute) {

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("institutes")
                .usingGeneratedKeyColumns("id")
                .usingColumns("title",
                        "description",
                        "created_by");

        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("title",
                institute.title());
        valueMap.put("description", institute.description());
        valueMap.put("created_by", userName);

        final Number instituteId = insert.executeAndReturnKey(valueMap);
        final Optional<Institute> createdInstitute=
                read(instituteId.longValue(),
                userName);

        return createdInstitute.get();
    }


    /**
     * reads from institute.
     * @param id the id
     * @param userName the userName
     * @return question optional
     */
    public Optional<Institute> read(final Long id,
                                   final String userName) {
        final String query = "SELECT id,title,description,created_by," +
                "created_at"
                + "FROM institutes WHERE id = ?";


        try {
            final Institute p = (Institute) jdbcTemplate
                    .queryForObject(query, new Object[]{id},
                            this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the institute.
     * @param id the id
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public Optional<Institute> update(final Long id,
                                     final String userName,
                                     final Institute institute) {
        return null;
    }

    /**
     * delete the institute.
     * @param id the id
     * @return false
     */
    public Boolean delete(final Long id) {
        return false;
    }

    /**
     * list the institute.
     * @param userName the userName
     * @return question optional
     */
    public List<Institute> list(final String userName) {
        return null;
    }
}
