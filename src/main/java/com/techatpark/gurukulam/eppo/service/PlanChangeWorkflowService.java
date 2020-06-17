package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.PlanChangeWorkflow;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class PlanChangeWorkflowService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for PlanChangeWorkflow related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public PlanChangeWorkflowService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into PlanChangeWorkflow table.
     * 
     * @param newPlanChangeWorkflow
     * @return reads the input data
     */
    public PlanChangeWorkflow create(final PlanChangeWorkflow newPlanChangeWorkflow) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("plan_change_workflows")
                .usingGeneratedKeyColumns("id").usingColumns("contract_payment_id", "eppopay_plan_id", "created_by",
                        "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("contract_payment_id", newPlanChangeWorkflow.getContractPaymentId());
        valuesMap.put("eppopay_plan_id", newPlanChangeWorkflow.getEppopayPlanId());
        valuesMap.put("created_by", newPlanChangeWorkflow.getCreatedBy());
        valuesMap.put("updated_by", newPlanChangeWorkflow.getUpdatedBy());
        valuesMap.put("is_deleted", newPlanChangeWorkflow.getIsDeleted());
        valuesMap.put("status", newPlanChangeWorkflow.getStatus());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table PlanChangeWorkflow.
     *
     * @param id
     * @return PlanChangeWorkflow
     */
    public Optional<PlanChangeWorkflow> read(final Integer id) {
        final String query = "SELECT contract_payment_id, eppopay_plan_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM  WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table PlanChangeWorkflow.
     *
     * @param id
     * @param newPlanChangeWorkflow
     * @return PlanChangeWorkflow
     */
    public PlanChangeWorkflow update(final Integer id, final PlanChangeWorkflow newPlanChangeWorkflow) {
        final String query = "UPDATE  SET contract_payment_id = ?, eppopay_plan_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newPlanChangeWorkflow.getContractPaymentId(),
                newPlanChangeWorkflow.getEppopayPlanId(), newPlanChangeWorkflow.getCreatedBy(),
                newPlanChangeWorkflow.getUpdatedBy(), newPlanChangeWorkflow.getIsDeleted(),
                newPlanChangeWorkflow.getStatus(), id);

        return read(id).get();
    }

    /**
     * Delete all from PlanChangeWorkflow.
     * 
     * @param id
     * @return PlanChangeWorkflow
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM  WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from PlanChangeWorkflow.
     *
     * @return PlanChangeWorkflow
     */
    public Integer delete() {
        final String query = "DELETE FROM ";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in PlanChangeWorkflow.
     *
     * @return PlanChangeWorkflow
     */
    public List<PlanChangeWorkflow> list() {
        final String query = "SELECT id, contract_payment_id, eppopay_plan_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM ";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return PlanChangeWorkflow
     * @throws SQLException
     */
    private PlanChangeWorkflow mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final PlanChangeWorkflow planChangeWorkflow = new PlanChangeWorkflow();
        planChangeWorkflow.setId(rs.getInt("id"));
        planChangeWorkflow.setId(rs.getInt("contract_payment_id"));
        planChangeWorkflow.setId(rs.getInt("eppopay_plan_id"));
        planChangeWorkflow.setId(rs.getInt("created_by"));
        planChangeWorkflow.setId(rs.getInt("updated_by"));
        planChangeWorkflow.setId(rs.getInt("is_deleted"));
        planChangeWorkflow.setId(rs.getInt("status"));
        planChangeWorkflow.setCreatedAt(rs.getDate("created_at"));
        planChangeWorkflow.setUpdatedAt(rs.getDate("updated_at"));
        return planChangeWorkflow;
    }
}
