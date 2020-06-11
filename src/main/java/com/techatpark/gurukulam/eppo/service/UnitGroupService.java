package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.UnitGroup;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UnitGroupService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for UnitGroup related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UnitGroupService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into UnitGroup table.
     * 
     * @param newUnitGroup
     * @return reads the input data
     */
    public UnitGroup create(final UnitGroup newUnitGroup) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("unit_groups")
                .usingGeneratedKeyColumns("id").usingColumns("unit_group_name", "created_by", "updated_by",
                        "is_deleted", "status", "account_id", "address_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("unit_group_name", newUnitGroup.getUnitGroupName());
        valuesMap.put("created_by", newUnitGroup.getCreatedBy());
        valuesMap.put("updated_by", newUnitGroup.getUpdatedBy());
        valuesMap.put("is_deleted", newUnitGroup.getIsDeleted());
        valuesMap.put("status", newUnitGroup.getStatus());
        valuesMap.put("account_id", newUnitGroup.getAccountId());
        valuesMap.put("address_id", newUnitGroup.getAddressId());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table UnitGroup.
     *
     * @param id
     * @return UnitGroup
     */
    public Optional<UnitGroup> read(final Integer id) {
        final String query = "SELECT unit_group_name, created_by, updated_by, is_deleted, status, account_id, address_id, created_at, updated_at FROM unit_groups WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table UnitGroup.
     *
     * @param id
     * @param newUnitGroup
     * @return UnitGroup
     */
    public UnitGroup update(final Integer id, final UnitGroup newUnitGroup) {
        final String query = "UPDATE unit_groups SET unit_group_name = ?, created_by = ?, updated_by = ?, is_deleted = ?, status =?, account_id = ?, address_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUnitGroup.getUnitGroupName(), newUnitGroup.getCreatedBy(),
                newUnitGroup.getUpdatedBy(), newUnitGroup.getIsDeleted(), newUnitGroup.getStatus(),
                newUnitGroup.getAccountId(), newUnitGroup.getAddressId(), id);
        return read(id).get();
    }

    /**
     * Delete all from UnitGroup.
     * 
     * @param id
     * @return UnitGroup
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM unit_groups WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from UnitGroup.
     *
     * @return UnitGroup
     */
    public Integer delete() {
        final String query = "DELETE FROM unit_groups";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in UnitGroup.
     *
     * @return UnitGroup
     */
    public List<UnitGroup> list() {
        final String query = "SELECT id, unit_group_name, created_by, updated_by, is_deleted, status, account_id, address_id, created_at, updated_at FROM unit_groups";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return UnitGroup
     * @throws SQLException
     */
    private UnitGroup mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final UnitGroup unitGroup = new UnitGroup();
        unitGroup.setId(rs.getInt("id"));
        unitGroup.setUnitGroupName(rs.getString("unit_group_name"));
        unitGroup.setCreatedBy(rs.getInt("created_by"));
        unitGroup.setUpdatedBy(rs.getInt("updated_by"));
        unitGroup.setIsDeleted(rs.getInt("is_deleted"));
        unitGroup.setStatus(rs.getInt("status"));
        unitGroup.setAccountId(rs.getInt("account_id"));
        unitGroup.setAddressId(rs.getInt("address_id"));
        unitGroup.setCreatedAt(rs.getDate("created_at"));
        unitGroup.setUpdatedAt(rs.getDate("updated_at"));
        return unitGroup;
    }
}
