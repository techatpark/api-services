package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.GlobalPayment;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class GlobalPaymentService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for GlobalPayment related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public GlobalPaymentService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into GlobalPayment table.
     * 
     * @param newGlobalPayment
     * @return reads the input data
     */
    public GlobalPayment create(final GlobalPayment newGlobalPayment) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("global_payments")
                .usingGeneratedKeyColumns("id").usingColumns("payment_name", "description", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("payment_name", newGlobalPayment.getPaymentName());
        valuesMap.put("description", newGlobalPayment.getDescription());
        valuesMap.put("status", newGlobalPayment.getStatus());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table GlobalPayment.
     *
     * @param id
     * @return GlobalPayment
     */
    public Optional<GlobalPayment> read(final Integer id) {
        final String query = "SELECT payment_name, description, status, created_at, updated_at FROM global_payments WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table GlobalPayment.
     *
     * @param id
     * @param newGlobalPayment
     * @return GlobalPayment
     */
    public GlobalPayment update(final Integer id, final GlobalPayment newGlobalPayment) {
        final String query = "UPDATE global_payments SET payment_name = ?, description = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newGlobalPayment.getPaymentName(), newGlobalPayment.getDescription(),
                newGlobalPayment.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from GlobalPayment.
     * 
     * @param id
     * @return GlobalPayment
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM global_payments WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from GlobalPayment.
     *
     * @return GlobalPayment
     */
    public Integer delete() {
        final String query = "DELETE FROM global_payments";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in GlobalPayment.
     *
     * @return GlobalPayment
     */
    public List<GlobalPayment> list() {
        final String query = "SELECT id, payment_name, description, status, created_at, updated_at FROM global_payments";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return GlobalPayment
     * @throws SQLException
     */
    private GlobalPayment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final GlobalPayment globalPayment = new GlobalPayment();
        globalPayment.setId(rs.getInt("id"));
        globalPayment.setPaymentName(rs.getString("payment_name"));
        globalPayment.setDescription(rs.getString("description"));
        globalPayment.setStatus(rs.getShort("status"));
        globalPayment.setCreatedAt(rs.getDate("created_at"));
        globalPayment.setUpdatedAt(rs.getDate("updated_at"));
        return globalPayment;
    }
}
