package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.UnitType;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UnitTypeService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for UnitType related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UnitTypeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into UnitType table.
     * 
     * @param newUnitType
     * @return reads the input data
     */
    public UnitType create(final UnitType newUnitType) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("unit_types")
                .usingGeneratedKeyColumns("id")
                .usingColumns("unit_type_name", "created_by", "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("unit_type_name", newUnitType.getUnitTypeName());
        valuesMap.put("created_by", newUnitType.getUnitTypeName());
        valuesMap.put("updated_by", newUnitType.getUnitTypeName());
        valuesMap.put("is_deleted", newUnitType.getUnitTypeName());
        valuesMap.put("status", newUnitType.getUnitTypeName());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table UnitType.
     *
     * @param id
     * @return UnitType
     */
    public Optional<UnitType> read(final Integer id) {
        final String query = "SELECT unit_type_name, created_by, updated_by, is_deleted, status, created_at, updated_at FROM unit_types WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table UnitType.
     *
     * @param id
     * @param newUnitType
     * @return UnitType
     */
    public UnitType update(final Integer id, final UnitType newUnitType) {
        final String query = "UPDATE unit_types SET unit_type_name = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUnitType.getUnitTypeName(), newUnitType.getCreatedBy(),
                newUnitType.getUpdatedBy(), newUnitType.getIsDeleted(), newUnitType.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from UnitType.
     * 
     * @param id
     * @return UnitType
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM unit_types WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from UnitType.
     *
     * @return UnitType
     */
    public Integer delete() {
        final String query = "DELETE FROM unit_types";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in UnitType.
     *
     * @return UnitType
     */
    public List<UnitType> list() {
        final String query = "SELECT id, unit_type_name, created_by, updated_by, is_deleted, status, created_at, updated_at FROM unit_types";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return UnitType
     * @throws SQLException
     */
    private UnitType mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final UnitType unitType = new UnitType();
        unitType.setId(rs.getInt("id"));
        unitType.setUnitTypeName(rs.getString("unit_type_name"));
        unitType.setCreatedBy(rs.getInt("created_by"));
        unitType.setUpdatedBy(rs.getInt("updated_by"));
        unitType.setIsDeleted(rs.getInt("is_deleted"));
        unitType.setStatus(rs.getInt("status"));
        unitType.setCreatedAt(rs.getDate("created_at"));
        unitType.setUpdatedAt(rs.getDate("updated_at"));
        return unitType;
    }
}
