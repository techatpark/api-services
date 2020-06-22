package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.SubEntityPlan;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class SubEntityPlanService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for SubEntityPlan related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public SubEntityPlanService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into SubEntityPlan table.
     * 
     * @param newSubEntityPlan
     * @return reads the input data
     */
    public SubEntityPlan create(final SubEntityPlan newSubEntityPlan) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("sub_entity_plans")
                .usingGeneratedKeyColumns("id")
                .usingColumns("created_by", "updated_by", "is_deleted", "status", "sub_entity_id", "eppopay_plan_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("created_by", newSubEntityPlan.getCreatedBy());
        valuesMap.put("updated_by", newSubEntityPlan.getUpdatedBy());
        valuesMap.put("is_deleted", newSubEntityPlan.getIsDeleted());
        valuesMap.put("status", newSubEntityPlan.getStatus());
        valuesMap.put("sub_entity_id", newSubEntityPlan.getSubEntityId());
        valuesMap.put("eppopay_plan_id", newSubEntityPlan.getEppopayPlanId());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table SubEntityPlan.
     *
     * @param id
     * @return SubEntityPlan
     */
    public Optional<SubEntityPlan> read(final Integer id) {
        final String query = "SELECT created_by, updated_by, is_deleted, status, sub_entity_id, eppopay_plan_id, created_at, updated_at FROM sub_entity_plans WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table SubEntityPlan.
     *
     * @param id
     * @param newSubEntityPlan
     * @return SubEntityPlan
     */
    public SubEntityPlan update(final Integer id, final SubEntityPlan newSubEntityPlan) {
        final String query = "UPDATE sub_entity_plans SET created_by = ?, updated_by = ?, is_deleted = ?, status = ?, sub_entity_id = ?, eppopay_plan_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newSubEntityPlan.getCreatedBy(), newSubEntityPlan.getUpdatedBy(),
                newSubEntityPlan.getIsDeleted(), newSubEntityPlan.getStatus(), newSubEntityPlan.getSubEntityId(),
                newSubEntityPlan.getEppopayPlanId(), id);
        return read(id).get();
    }

    /**
     * Delete all from SubEntityPlan.
     * 
     * @param id
     * @return SubEntityPlan
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM sub_entity_plans WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from SubEntityPlan.
     *
     * @return SubEntityPlan
     */
    public Integer delete() {
        final String query = "DELETE FROM sub_entity_plans";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in SubEntityPlan.
     *
     * @return SubEntityPlan
     */
    public List<SubEntityPlan> list() {
        final String query = "SELECT id, created_by, updated_by, is_deleted, status, sub_entity_id, eppopay_plan_id, created_by, updated_by, created_at, updated_at FROM sub_entity_plans";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return SubEntityPlan
     * @throws SQLException
     */
    private SubEntityPlan mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final SubEntityPlan subEntityPlan = new SubEntityPlan();
        subEntityPlan.setId(rs.getInt("id"));
        subEntityPlan.setCreatedBy(rs.getInt("created_by"));
        subEntityPlan.setUpdatedBy(rs.getInt("updated_by"));
        subEntityPlan.setIsDeleted(rs.getShort("is_deleted"));
        subEntityPlan.setStatus(rs.getShort("status"));
        subEntityPlan.setSubEntityId(rs.getInt("sub_entity_id"));
        subEntityPlan.setEppopayPlanId(rs.getInt("eppopay_plan_id"));
        subEntityPlan.setCreatedAt(rs.getDate("created_at"));
        subEntityPlan.setUpdatedAt(rs.getDate("updated_at"));
        return subEntityPlan;
    }
}
