package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Log;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Log related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public LogService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Log table.
     * 
     * @param newLog
     * @return reads the input data
     */
    public Log create(final Log newLog) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("logs")
                .usingGeneratedKeyColumns("id")
                .usingColumns("log_event", "reference_id", "user_type", "user_id", "log_message", "log_data_json");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("log_event", newLog.getLogEvent());
        valuesMap.put("reference_id", newLog.getReferenceId());
        valuesMap.put("user_type", newLog.getUserType());
        valuesMap.put("user_id", newLog.getUserId());
        valuesMap.put("log_message", newLog.getLogMessage());
        valuesMap.put("log_data_json", newLog.getLogDataJson());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Log.
     *
     * @param id
     * @return Log
     */
    public Optional<Log> read(final Integer id) {
        final String query = "SELECT log_event, reference_id, user_type, user_id, log_message, log_data_json, created_at, updated_at FROM logs WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Log.
     *
     * @param id
     * @param newLog
     * @return Log
     */
    public Log update(final Integer id, final Log newLog) {
        final String query = "UPDATE logs SET log_event = ?, reference_id = ?, user_type = ?, user_id = ?, log_message = ?, log_data_json = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newLog.getLogEvent(), newLog.getReferenceId(), newLog.getUserType(),
                newLog.getUserId(), newLog.getLogMessage(), newLog.getLogDataJson(), id);
        return read(id).get();
    }

    /**
     * Delete all from Log.
     * 
     * @param id
     * @return Log
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM logs WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Log.
     *
     * @return Log
     */
    public Integer delete() {
        final String query = "DELETE FROM logs";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Log.
     *
     * @return Log
     */
    public List<Log> list() {
        final String query = "SELECT id, log_event, reference_id, user_type, user_id, log_message, log_data_json, created_at, updated_at FROM logs";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Log
     * @throws SQLException
     */
    private Log mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Log log = new Log();
        log.setId(rs.getInt("id"));
        log.setLogEvent(rs.getString("log_event"));
        log.setReferenceId(rs.getInt("reference_id"));
        log.setUserType(rs.getString("user_type"));
        log.setUserId(rs.getInt("user_id"));
        log.setLogMessage(rs.getString("log_message"));
        log.setLogDataJson(rs.getString("log_data_json"));
        log.setCreatedAt(rs.getDate("created_at"));
        log.setUpdatedAt(rs.getDate("updated_at"));
        return log;
    }
}
