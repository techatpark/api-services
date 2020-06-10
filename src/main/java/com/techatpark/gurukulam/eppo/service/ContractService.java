package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Contract;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Contract related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public ContractService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Contract table.
     * 
     * @param newContract
     * @return reads the input data
     */
    public Contract create(final Contract newContract) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("contracts")
                .usingGeneratedKeyColumns("id").usingColumns("rent_amount", "unique_account_number", "verification_pin",
                        "landlord_bank_account_id", "customer_primary_bank_account_id",
                        "customer_secondary_bank_account_id", "start_date", "end_date", "month_on_month",
                        "customer_preferred_payment_plan_dates_json", "pause_customer_date", "created_by", "updated_by",
                        "is_deleted", "status", "account_id", "customer_id", "unit_id",
                        "customer_preferred_payment_plan_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("rent_amount", newContract.getRentAmount());
        valuesMap.put("unique_account_number", newContract.getUniqueAccountNumber());
        valuesMap.put("verification_pin", newContract.getVerificationPin());
        valuesMap.put("landlord_bank_account_id", newContract.getLandlordBankAccountId());
        valuesMap.put("customer_primary_bank_account_id", newContract.getCustomerPrimaryBankAccountId());
        valuesMap.put("customer_secondary_bank_account_id", newContract.getCustomerSecondaryBankAccountId());
        valuesMap.put("start_date", newContract.getStartDate());
        valuesMap.put("end_date", newContract.getEndDate());
        valuesMap.put("month_on_month", newContract.getMonthOnMonth());
        valuesMap.put("customer_preferred_payment_plan_dates_json",
                newContract.getCustomerPreferredPaymentPlanDatesJson());
        valuesMap.put("pause_customer_date", newContract.getPauseCustomerDate());
        valuesMap.put("created_by", newContract.getCreatedBy());
        valuesMap.put("updated_by", newContract.getUpdatedBy());
        valuesMap.put("status", newContract.getStatus());
        valuesMap.put("account_id", newContract.getAccountId());
        valuesMap.put("customer_id", newContract.getCustomerId());
        valuesMap.put("unit_id", newContract.getUnitId());
        valuesMap.put("customer_preferred_payment_plan_id", newContract.getCustomerPreferredPaymentPlanId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Contract.
     *
     * @param id
     * @return Contract
     */
    public Optional<Contract> read(final Integer id) {
        final String query = "SELECT rent_amount, unique_account_number, verification_pin, landlord_bank_account_id, customer_primary_bank_account_id, customer_secondary_bank_account_id, start_date, end_date, month_on_month,customer_preferred_payment_plan_dates_json, pause_customer_date, created_by, updated_by, is_deleted, status, account_id, customer_id, unit_id, customer_preferred_payment_plan_id, created_at, updated_at FROM contracts WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Contract.
     *
     * @param id
     * @param newContract
     * @return Contract
     */
    public Contract update(final Integer id, final Contract newContract) {
        final String query = "UPDATE contracts SET rent_amount = ?, unique_account_number = ?, verification_pin = ?, landlord_bank_account_id = ?, customer_primary_bank_account_id = ?, customer_secondary_bank_account_id = ?, start_date = ?, end_date = ?, month_on_month = ?, customer_preferred_payment_plan_dates_json = ?, pause_customer_date = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, customer_id = ?, unit_id = ?, customer_preferred_payment_plan_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newContract.getRentAmount(), newContract.getUniqueAccountNumber(),
                newContract.getVerificationPin(), newContract.getLandlordBankAccountId(),
                newContract.getCustomerPrimaryBankAccountId(), newContract.getCustomerSecondaryBankAccountId(),
                newContract.getStartDate(), newContract.getEndDate(), newContract.getMonthOnMonth(),
                newContract.getCustomerPreferredPaymentPlanDatesJson(), newContract.getPauseCustomerDate(),
                newContract.getCreatedBy(), newContract.getUpdatedBy(), newContract.getIsDeleted(),
                newContract.getStatus(), newContract.getAccountId(), newContract.getCustomerId(),
                newContract.getUnitId(), newContract.getCustomerPreferredPaymentPlanId(), id);
        return read(id).get();
    }

    /**
     * Delete all from Contract.
     * 
     * @param id
     * @return Contract
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM contracts WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Contract.
     *
     * @return Contract
     */
    public Integer delete() {
        final String query = "DELETE FROM contracts";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Contract.
     *
     * @return Contract
     */
    public List<Contract> list() {
        final String query = "SELECT id, rent_amount, unique_account_number, verification_pin, landlord_bank_account_id, customer_primary_bank_account_id, customer_secondary_bank_account_id, start_date, end_date, month_on_month, customer_preferred_payment_plan_dates_json, pause_customer_date, created_by, updated_by, is_deleted, status, account_id, customer_id, unit_id, customer_preferred_payment_plan_id , created_at, updated_at FROM contracts";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Contract
     * @throws SQLException
     */
    private Contract mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Contract contract = new Contract();
        contract.setId(rs.getInt("id"));
        contract.setRentAmount(rs.getInt("rent_amount"));
        contract.setUniqueAccountNumber(rs.getString("unique_account_number"));
        contract.setVerificationPin(rs.getString("verification_pin"));
        contract.setLandlordBankAccountId(rs.getInt("landlord_bank_account_id"));
        contract.setCustomerPrimaryBankAccountId(rs.getInt("customer_primary_bank_account_id"));
        contract.setCustomerPrimaryBankAccountId(rs.getInt("customer_secondary_bank_account_id"));
        contract.setStartDate(rs.getDate("start_date"));
        contract.setEndDate(rs.getDate("end_date"));
        contract.setMonthOnMonth(rs.getBoolean("month_on_month"));
        contract.setCustomerPreferredPaymentPlanDatesJson(rs.getString("customer_preferred_payment_plan_dates_json"));
        contract.setPauseCustomerDate(rs.getDate("pause_customer_date"));
        contract.setCreatedBy(rs.getInt("created_by"));
        contract.setUpdatedBy(rs.getInt("updated_by"));
        contract.setIsDeleted(rs.getShort("is_deleted"));
        contract.setStatus(rs.getShort("status"));
        contract.setAccountId(rs.getInt("account_id"));
        contract.setCustomerId(rs.getInt("customer_id"));
        contract.setUnitId(rs.getInt("unit_id"));
        contract.setCustomerPreferredPaymentPlanId(rs.getInt("customer_preferred_payment_plan_id"));
        contract.setCreatedAt(rs.getDate("created_at"));
        contract.setUpdatedAt(rs.getDate("updated_at"));
        return contract;
    }
}
