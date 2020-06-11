package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.StatusDetail;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class StatusDetailService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for StatusDetail related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public StatusDetailService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into StatusDetail table.
     * 
     * @param newStatusDetail
     * @return reads the input data
     */
    public StatusDetail create(final StatusDetail newStatusDetail) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("status_details")
                .usingGeneratedKeyColumns("id").usingColumns("status_value", "module_name");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("status_value", newStatusDetail.getStatusValue());
        valuesMap.put("module_name", newStatusDetail.getModuleName());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table StatusDetail.
     *
     * @param id
     * @return StatusDetail
     */
    public Optional<StatusDetail> read(final Integer id) {
        final String query = "SELECT status_value, module_name, created_at, updated_at FROM status_details WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table StatusDetail.
     *
     * @param id
     * @param newStatusDetail
     * @return StatusDetail
     */
    public StatusDetail update(final Integer id, final StatusDetail newStatusDetail) {
        final String query = "UPDATE status_details SET status_value = ?, module_name = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newStatusDetail.getStatusValue(), newStatusDetail.getModuleName(), id);
        return read(id).get();
    }

    /**
     * Delete all from StatusDetail.
     * 
     * @param id
     * @return StatusDetail
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM status_details WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from StatusDetail.
     *
     * @return StatusDetail
     */
    public Integer delete() {
        final String query = "DELETE FROM status_details";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in StatusDetail.
     *
     * @return StatusDetail
     */
    public List<StatusDetail> list() {
        final String query = "SELECT id, status_value, module_name, created_at, updated_at FROM status_details";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return StatusDetail
     * @throws SQLException
     */
    private StatusDetail mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final StatusDetail statusDetail = new StatusDetail();
        statusDetail.setId(rs.getInt("id"));
        statusDetail.setStatusValue(rs.getString("status_value"));
        statusDetail.setModuleName(rs.getString("module_name"));
        statusDetail.setCreatedAt(rs.getDate("created_at"));
        statusDetail.setUpdatedAt(rs.getDate("updated_at"));
        return statusDetail;
    }
}
