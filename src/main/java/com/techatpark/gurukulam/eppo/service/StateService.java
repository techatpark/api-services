
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.State;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for State related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public StateService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into State table.
     * 
     * @param newState
     * @return reads the input data
     */
    public State create(final State newState) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("states")
                .usingGeneratedKeyColumns("id").usingColumns("state_name", "state_code", "status", "country_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("state_name", newState.getClass());
        valuesMap.put("state_code", newState.getStateCode());
        valuesMap.put("status", newState.getStatus());
        valuesMap.put("country_id", newState.getCountryId());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table State.
     *
     * @param id
     * @return State
     */
    public Optional<State> read(final Integer id) {
        final String query = "SELECT state_name, state_code, status, country_id, created_at, updated_at FROM states WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table State.
     *
     * @param id
     * @param newState
     * @return State
     */
    public State update(final Integer id, final State newState) {
        final String query = "UPDATE states SET state_name = ?, state_code = ?, status = ?, country_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newState.getStateName(), newState.getStateCode(), newState.getStatus(),
                newState.getCountryId(), id);
        return read(id).get();
    }

    /**
     * Delete all from State.
     * 
     * @param id
     * @return State
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM states WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from State.
     *
     * @return State
     */
    public Integer delete() {
        final String query = "DELETE FROM states";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in State.
     *
     * @return State
     */
    public List<State> list() {
        final String query = "SELECT id, state_name, state_code, status, country_id, created_at, updated_at FROM states";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return State
     * @throws SQLException
     */
    private State mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final State state = new State();
        state.setId(rs.getInt("id"));
        state.setStateName(rs.getString("state_name"));
        state.setStateCode(rs.getString("state_code"));
        state.setStatus(rs.getInt("status"));
        state.setCountryId(rs.getInt("country_id"));
        state.setCreatedAt(rs.getDate("created_at"));
        state.setUpdatedAt(rs.getDate("updated_at"));
        return state;
    }
}
