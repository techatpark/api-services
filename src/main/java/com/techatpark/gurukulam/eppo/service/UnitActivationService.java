
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.UnitActivation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UnitActivationService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for UnitActivation related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UnitActivationService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into UnitActivation table.
     * 
     * @param newUnitActivation
     * @return reads the input data
     */
    public UnitActivation create(final UnitActivation newUnitActivation) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("unit_activations")
                .usingGeneratedKeyColumns("id").usingColumns("start_date", "end_date", "active_inactive", "delete",
                        "created_by", "updated_by", "is_deleted", "status", "unit_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("start_date", newUnitActivation.getStartDate());
        valuesMap.put("end_date", newUnitActivation.getStartDate());
        valuesMap.put("active_inactive", newUnitActivation.getStartDate());
        valuesMap.put("delete", newUnitActivation.getStartDate());
        valuesMap.put("created_by", newUnitActivation.getStartDate());
        valuesMap.put("updated_by", newUnitActivation.getStartDate());
        valuesMap.put("is_deleted", newUnitActivation.getStartDate());
        valuesMap.put("status", newUnitActivation.getStartDate());
        valuesMap.put("unit_id", newUnitActivation.getStartDate());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table UnitActivation.
     *
     * @param id
     * @return UnitActivation
     */
    public Optional<UnitActivation> read(final Integer id) {
        final String query = "SELECT start_date, end_date, active_inactive, delete, created_by, updated_by, is_deleted, status, unit_id, created_at, updated_at FROM unit_activations WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table UnitActivation.
     *
     * @param id
     * @param newUnitActivation
     * @return UnitActivation
     */
    public UnitActivation update(final Integer id, final UnitActivation newUnitActivation) {
        final String query = "UPDATE unit_activations SET start_date = ?, end_date = ?, active_inactive = ?, delete = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, unit_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUnitActivation.getStartDate(), newUnitActivation.getEndDate(),
                newUnitActivation.getActiveInactive(), newUnitActivation.getDelete(), newUnitActivation.getCreatedBy(),
                newUnitActivation.getUpdatedBy(), newUnitActivation.getIsDeleted(), newUnitActivation.getStatus(),
                newUnitActivation.getUnitId(), id);
        return read(id).get();
    }

    /**
     * Delete all from UnitActivation.
     * 
     * @param id
     * @return UnitActivation
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM unit_activations WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from UnitActivation.
     *
     * @return UnitActivation
     */
    public Integer delete() {
        final String query = "DELETE FROM unit_activations";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in UnitActivation.
     *
     * @return UnitActivation
     */
    public List<UnitActivation> list() {
        final String query = "SELECT id, start_date, end_date, active_inactive, delete, created_by, updated_by, is_deleted, status, unit_id, created_at, updated_at FROM unit_activations";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return UnitActivation
     * @throws SQLException
     */
    private UnitActivation mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final UnitActivation unitActivation = new UnitActivation();
        unitActivation.setId(rs.getInt("id"));
        unitActivation.setStartDate(rs.getDate("start_date"));
        unitActivation.setEndDate(rs.getDate("end_date"));
        unitActivation.setActiveInactive(rs.getString("active_inactive"));
        unitActivation.setDelete(rs.getString("delete"));
        unitActivation.setCreatedBy(rs.getInt("created_by"));
        unitActivation.setUpdatedBy(rs.getInt("updated_by"));
        unitActivation.setIsDeleted(rs.getInt("is_deleted"));
        unitActivation.setStatus(rs.getInt("status"));
        unitActivation.setUnitId(rs.getInt("unit_id"));
        unitActivation.setCreatedAt(rs.getDate("created_at"));
        unitActivation.setUpdatedAt(rs.getDate("updated_at"));
        return unitActivation;
    }
}
