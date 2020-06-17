
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.ContractPayment;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ContractPaymentService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for ContractPayment related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public ContractPaymentService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into ContractPayment table.
     * 
     * @param newContractPayment
     * @return reads the input data
     */
    public ContractPayment create(final ContractPayment newContractPayment) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("contract_payments")
                .usingGeneratedKeyColumns("id").usingColumns("due_number", "rent_amount", "carried_over_rent_amount",
                        "carried_over_rent_fee", "rent_relief_amount", "adjustment_amount", "merchant_due_date",
                        "net_amount", "eppopay_plan_dates_json", "adjustment_comment", "contract_id", "eppopay_plan_id",
                        "created_by", "updated_by", "status", "is_deleted");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("due_number", newContractPayment.getDueNumber());
        valuesMap.put("rent_amount", newContractPayment.getRentAmount());
        valuesMap.put("carried_over_rent_amount", newContractPayment.getCarriedOverRentAmount());
        valuesMap.put("carried_over_rent_fee", newContractPayment.getCarriedOverRentFee());
        valuesMap.put("rent_relief_amount", newContractPayment.getRentReliefAmount());
        valuesMap.put("adjustment_amount", newContractPayment.getAdjustmentAmount());
        valuesMap.put("merchant_due_date", newContractPayment.getMerchantDueDate());
        valuesMap.put("net_amount", newContractPayment.getNetAmount());
        valuesMap.put("eppopay_plan_dates_json", newContractPayment.getEppopayPlanDatesJson());
        valuesMap.put("adjustment_comment", newContractPayment.getAdjustmentComment());
        valuesMap.put("contract_id", newContractPayment.getContractId());
        valuesMap.put("eppopay_plan_id", newContractPayment.getEppopayPlanId());
        valuesMap.put("created_by", newContractPayment.getCreatedBy());
        valuesMap.put("updated_by", newContractPayment.getUpdatedBy());
        valuesMap.put("status", newContractPayment.getStatus());
        valuesMap.put("is_deleted", newContractPayment.getIsDeleted());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table ContractPayment.
     *
     * @param id
     * @return ContractPayment
     */
    public Optional<ContractPayment> read(final Integer id) {
        final String query = "SELECT due_number, rent_amount, carried_over_rent_amount, carried_over_rent_fee, rent_relief_amount, adjustment_amount, merchant_due_date, net_amount, eppopay_plan_dates_json, adjustment_comment, contract_id, eppopay_plan_id, created_by, updated_by, status, is_deleted, created_at, updated_at FROM contract_payments WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table ContractPayment.
     *
     * @param id
     * @param newContractPayment
     * @return ContractPayment
     */
    public ContractPayment update(final Integer id, final ContractPayment newContractPayment) {
        final String query = "UPDATE contract_payments SET due_number = ?, rent_amount = ?, carried_over_rent_amount = ?, carried_over_rent_fee = ?, rent_relief_amount = ?, adjustment_amount = ?, merchant_due_date = ?, net_amount = ?, eppopay_plan_dates_json = ?, adjustment_comment = ?, contract_id = ?, eppopay_plan_id = ?, created_by = ?, updated_by = ?, status = ?, is_deleted = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newContractPayment.getDueNumber(), newContractPayment.getRentAmount(),
                newContractPayment.getCarriedOverRentAmount(), newContractPayment.getCarriedOverRentFee(),
                newContractPayment.getRentReliefAmount(), newContractPayment.getAdjustmentAmount(),
                newContractPayment.getMerchantDueDate(), newContractPayment.getNetAmount(),
                newContractPayment.getEppopayPlanDatesJson(), newContractPayment.getAdjustmentComment(),
                newContractPayment.getContractId(), newContractPayment.getEppopayPlanId(),
                newContractPayment.getCreatedBy(), newContractPayment.getUpdatedBy(), newContractPayment.getStatus(),
                newContractPayment.getIsDeleted(), id);
        return read(id).get();
    }

    /**
     * Delete all from ContractPayment.
     * 
     * @param id
     * @return ContractPayment
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM contract_payments WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from ContractPayment.
     *
     * @return ContractPayment
     */
    public Integer delete() {
        final String query = "DELETE FROM contract_payments";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in ContractPayment.
     *
     * @return ContractPayment
     */
    public List<ContractPayment> list() {
        final String query = "SELECT id, due_number, rent_amount, carried_over_rent_amount, carried_over_rent_fee, rent_relief_amount, adjustment_amount, merchant_due_date, net_amount, eppopay_plan_dates_json, adjustment_comment, contract_id, eppopay_plan_id, created_by, updated_by, status, is_deleted , created_at, updated_at FROM contract_payments";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return ContractPayment
     * @throws SQLException
     */
    private ContractPayment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ContractPayment contractPayment = new ContractPayment();
        contractPayment.setId(rs.getInt("id"));
        contractPayment.setId(rs.getInt("due_number"));
        contractPayment.setId(rs.getInt("rent_amount"));
        contractPayment.setId(rs.getInt("carried_over_rent_amount"));
        contractPayment.setId(rs.getInt("carried_over_rent_fee"));
        contractPayment.setId(rs.getInt("rent_relief_amount"));
        contractPayment.setId(rs.getInt("adjustment_amount"));
        contractPayment.setId(rs.getInt("merchant_due_date"));
        contractPayment.setId(rs.getInt("inet_amountd"));
        contractPayment.setId(rs.getInt("eppopay_plan_dates_json"));
        contractPayment.setId(rs.getInt("eppopay_plan_dates_json"));
        contractPayment.setId(rs.getInt("contract_id"));
        contractPayment.setId(rs.getInt("eppopay_plan_id"));
        contractPayment.setId(rs.getInt("created_by"));
        contractPayment.setId(rs.getInt("updated_by"));
        contractPayment.setId(rs.getInt("status"));
        contractPayment.setId(rs.getInt("is_deleted"));
        contractPayment.setCreatedAt(rs.getDate("created_at"));
        contractPayment.setUpdatedAt(rs.getDate("updated_at"));
        return contractPayment;
    }
}
