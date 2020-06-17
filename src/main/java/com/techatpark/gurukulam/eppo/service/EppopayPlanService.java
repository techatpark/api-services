package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.EppopayPlan;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class EppopayPlanService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for EppopayPlan related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public EppopayPlanService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into EppopayPlan table.
     * 
     * @param newEppopayPlan
     * @return reads the input data
     */
    public EppopayPlan create(final EppopayPlan newEppopayPlan) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("eppopay_plans")
                .usingGeneratedKeyColumns("id").usingColumns("plan_name", "frequency", "rule_json", "fees_json",
                        "plan_type", "eppopay_fee_json", "approval_required", "created_by", "updated_by", "is_deleted",
                        "eppopay_plan_type_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("plan_name", newEppopayPlan.getPlanName());
        valuesMap.put("frequency", newEppopayPlan.getPlanName());
        valuesMap.put("rule_json", newEppopayPlan.getPlanName());
        valuesMap.put("fees_json", newEppopayPlan.getPlanName());
        valuesMap.put("plan_type", newEppopayPlan.getPlanName());
        valuesMap.put("eppopay_fee_json", newEppopayPlan.getPlanName());
        valuesMap.put("approval_required", newEppopayPlan.getPlanName());
        valuesMap.put("created_by", newEppopayPlan.getPlanName());
        valuesMap.put("updated_by", newEppopayPlan.getPlanName());
        valuesMap.put("is_deleted", newEppopayPlan.getPlanName());
        valuesMap.put("eppopay_plan_type_id", newEppopayPlan.getPlanName());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table EppopayPlan.
     *
     * @param id
     * @return EppopayPlan
     */
    public Optional<EppopayPlan> read(final Integer id) {
        final String query = "SELECT plan_name, frequency, rule_json, fees_json, plan_type, eppopay_fee_json, approval_required, created_by, updated_by, is_deleted, eppopay_plan_type_id, created_at, updated_at FROM eppopay_plans WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table EppopayPlan.
     *
     * @param id
     * @param newEppopayPlan
     * @return EppopayPlan
     */
    public EppopayPlan update(final Integer id, final EppopayPlan newEppopayPlan) {
        final String query = "UPDATE eppopay_plans SET plan_name = ?, frequency = ?, rule_json = ?, fees_json = ?, plan_type = ?, eppopay_fee_json = ?, approval_required = ?, created_by = ?, updated_by = ?, is_deleted = ?, eppopay_plan_type_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newEppopayPlan.getPlanName(), newEppopayPlan.getFrequency(),
                newEppopayPlan.getRuleJson(), newEppopayPlan.getFeesJson(), newEppopayPlan.getPlanType(),
                newEppopayPlan.getEppopayFeeJson(), newEppopayPlan.getApprovalRequired(), newEppopayPlan.getCreatedBy(),
                newEppopayPlan.getUpdatedBy(), newEppopayPlan.getIsDeleted(), newEppopayPlan.getEppopayPlanTypeId(),
                id);
        return read(id).get();
    }

    /**
     * Delete all from EppopayPlan.
     * 
     * @param id
     * @return EppopayPlan
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM eppopay_plans WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from EppopayPlan.
     *
     * @return EppopayPlan
     */
    public Integer delete() {
        final String query = "DELETE FROM eppopay_plans";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in EppopayPlan.
     *
     * @return EppopayPlan
     */
    public List<EppopayPlan> list() {
        final String query = "SELECT id, plan_name, frequency, rule_json, fees_json, plan_type, eppopay_fee_json, approval_required, created_by, updated_by, is_deleted, eppopay_plan_type_id, created_at, updated_at FROM eppopay_plans";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return EppopayPlan
     * @throws SQLException
     */
    private EppopayPlan mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final EppopayPlan eppopayPlan = new EppopayPlan();
        eppopayPlan.setId(rs.getInt("id"));
        eppopayPlan.setPlanName(rs.getString("plan_name"));
        eppopayPlan.setFrequency(rs.getString("frequency"));
        eppopayPlan.setRuleJson(rs.getString("rule_json"));
        eppopayPlan.setFeesJson(rs.getString("fees_json"));
        eppopayPlan.setPlanType(rs.getString("plan_type"));
        eppopayPlan.setFeesJson(rs.getString("eppopay_fee_json"));
        eppopayPlan.setApprovalRequired(rs.getShort("approval_required"));
        eppopayPlan.setCreatedBy(rs.getInt("created_by"));
        eppopayPlan.setUpdatedBy(rs.getInt("updated_by"));
        eppopayPlan.setIsDeleted(rs.getShort("is_deleted"));
        eppopayPlan.setEppopayPlanTypeId(rs.getInt("eppopay_plan_type_id"));
        eppopayPlan.setCreatedAt(rs.getDate("created_at"));
        eppopayPlan.setUpdatedAt(rs.getDate("updated_at"));
        return eppopayPlan;
    }
}
