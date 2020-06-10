package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.EppopayPlanType;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class EppopayPlanTypeService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for EppopayPlanType related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public EppopayPlanTypeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into EppopayPlanType table.
     * 
     * @param newEppopayPlanType
     * @return reads the input data
     */
    public EppopayPlanType create(final EppopayPlanType newEppopayPlanType) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("eppopay_plan_types")
                .usingGeneratedKeyColumns("id").usingColumns("plan_type_name", "plan_type_description");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("plan_type_name", newEppopayPlanType.getPlanTypeName());
        valuesMap.put("plan_type_description", newEppopayPlanType.getPlanTypeDescription());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table EppopayPlanType.
     *
     * @param id
     * @return EppopayPlanType
     */
    public Optional<EppopayPlanType> read(final Integer id) {
        final String query = "SELECT plan_type_name, plan_type_description, created_at, updated_at FROM eppopay_plan_types WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table EppopayPlanType.
     *
     * @param id
     * @param newEppopayPlanType
     * @return EppopayPlanType
     */
    public EppopayPlanType update(final Integer id, final EppopayPlanType newEppopayPlanType) {
        final String query = "UPDATE eppopay_plan_types SET plan_type_name = ?, plan_type_description = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newEppopayPlanType.getPlanTypeName(), newEppopayPlanType.getPlanTypeDescription(),
                id);
        return read(id).get();
    }

    /**
     * Delete all from EppopayPlanType.
     * 
     * @param id
     * @return EppopayPlanType
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM eppopay_plan_types WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from EppopayPlanType.
     *
     * @return EppopayPlanType
     */
    public Integer delete() {
        final String query = "DELETE FROM eppopay_plan_types";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in EppopayPlanType.
     *
     * @return EppopayPlanType
     */
    public List<EppopayPlanType> list() {
        final String query = "SELECT id, plan_type_name, plan_type_description , created_at, updated_at FROM eppopay_plan_types";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return EppopayPlanType
     * @throws SQLException
     */
    private EppopayPlanType mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final EppopayPlanType eppopayPlanType = new EppopayPlanType();
        eppopayPlanType.setId(rs.getInt("id"));
        eppopayPlanType.setPlanTypeName(rs.getString("plan_type_name"));
        eppopayPlanType.setPlanTypeDescription(rs.getString("plan_type_description"));
        eppopayPlanType.setCreatedAt(rs.getDate("created_at"));
        eppopayPlanType.setUpdatedAt(rs.getDate("updated_at"));
        return eppopayPlanType;
    }
}
