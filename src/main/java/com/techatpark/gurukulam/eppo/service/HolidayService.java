package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Holiday;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Holiday related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public HolidayService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Holiday table.
     * 
     * @param newHoliday
     * @return reads the input data
     */
    public Holiday create(final Holiday newHoliday) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("holidays")
                .usingGeneratedKeyColumns("id")
                .usingColumns("holiday_date", "holiday", "is_deleted", "created_by", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("holiday_date", newHoliday.getHolidayDate());
        valuesMap.put("holiday", newHoliday.getHoliday());
        valuesMap.put("is_deleted", newHoliday.getIsDeleted());
        valuesMap.put("created_by", newHoliday.getCreatedBy());
        valuesMap.put("updated_by", newHoliday.getUpdatedBy());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Holiday.
     *
     * @param id
     * @return Holiday
     */
    public Optional<Holiday> read(final Integer id) {
        final String query = "SELECT holiday_date, holiday, is_deleted, created_by, updated_by, created_at, updated_at FROM holidays WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Holiday.
     *
     * @param id
     * @param newHoliday
     * @return Holiday
     */
    public Holiday update(final Integer id, final Holiday newHoliday) {
        final String query = "UPDATE holidays SET holiday_date = ?, holiday = ?, is_deleted = ?, created_by = ?, updated_by = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newHoliday.getHolidayDate(), newHoliday.getHoliday(), newHoliday.getIsDeleted(),
                newHoliday.getCreatedBy(), newHoliday.getUpdatedBy(), id);
        return read(id).get();
    }

    /**
     * Delete all from Holiday.
     * 
     * @param id
     * @return Holiday
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM holidays WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Holiday.
     *
     * @return Holiday
     */
    public Integer delete() {
        final String query = "DELETE FROM holidays";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Holiday.
     *
     * @return Holiday
     */
    public List<Holiday> list() {
        final String query = "SELECT id, holiday_date, holiday, is_deleted, created_by, updated_by, created_at, updated_at FROM holidays";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Holiday
     * @throws SQLException
     */
    private Holiday mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Holiday holiday = new Holiday();
        holiday.setId(rs.getInt("id"));
        holiday.setHolidayDate(rs.getDate("holiday_date"));
        holiday.setHoliday(rs.getString("holiday"));
        holiday.setIsDeleted(rs.getShort("is_deleted"));
        holiday.setCreatedBy(rs.getInt("created_by"));
        holiday.setUpdatedBy(rs.getInt("updated_by"));
        holiday.setCreatedAt(rs.getDate("created_at"));
        holiday.setUpdatedAt(rs.getDate("updated_at"));
        return holiday;
    }
}
