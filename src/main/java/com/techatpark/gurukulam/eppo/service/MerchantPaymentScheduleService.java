package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.MerchantPaymentSchedule;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class MerchantPaymentScheduleService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for MerchantPaymentSchedule related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public MerchantPaymentScheduleService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into MerchantPaymentSchedule table.
     * 
     * @param newMerchantPaymentSchedule
     * @return reads the input data
     */
    public MerchantPaymentSchedule create(final MerchantPaymentSchedule newMerchantPaymentSchedule) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("merchant_payment_schedules")
                .usingGeneratedKeyColumns("id").usingColumns("payment_date", "amount", "created_by", "updated_by",
                        "is_deleted", "status", "active_inactive", "account_id", "contract_payment_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("payment_date", newMerchantPaymentSchedule.getPaymentDate());
        valuesMap.put("amount", newMerchantPaymentSchedule.getAmount());
        valuesMap.put("created_by", newMerchantPaymentSchedule.getCreatedBy());
        valuesMap.put("updated_by", newMerchantPaymentSchedule.getUpdatedBy());
        valuesMap.put("is_deleted", newMerchantPaymentSchedule.getIsDeleted());
        valuesMap.put("status", newMerchantPaymentSchedule.getStatus());
        valuesMap.put("active_inactive", newMerchantPaymentSchedule.getActiveInactive());
        valuesMap.put("account_id", newMerchantPaymentSchedule.getAccountId());
        valuesMap.put("contract_payment_id", newMerchantPaymentSchedule.getContractPaymentId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table MerchantPaymentSchedule.
     *
     * @param id
     * @return MerchantPaymentSchedule
     */
    public Optional<MerchantPaymentSchedule> read(final Integer id) {
        final String query = "SELECT payment_date, amount, created_by, updated_by, is_deleted, status, active_inactive, account_id, contract_payment_id, created_at, updated_at FROM merchant_payment_schedules WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table MerchantPaymentSchedule.
     *
     * @param id
     * @param newMerchantPaymentSchedule
     * @return MerchantPaymentSchedule
     */
    public MerchantPaymentSchedule update(final Integer id, final MerchantPaymentSchedule newMerchantPaymentSchedule) {
        final String query = "UPDATE merchant_payment_schedules SET payment_date = ?, amount = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, active_inactive = ?, account_id = ?, contract_payment_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newMerchantPaymentSchedule.getPaymentDate(), newMerchantPaymentSchedule.getAmount(),
                newMerchantPaymentSchedule.getCreatedBy(), newMerchantPaymentSchedule.getUpdatedBy(),
                newMerchantPaymentSchedule.getIsDeleted(), newMerchantPaymentSchedule.getStatus(),
                newMerchantPaymentSchedule.getActiveInactive(), newMerchantPaymentSchedule.getAccountId(),
                newMerchantPaymentSchedule.getContractPaymentId(), id);
        return read(id).get();
    }

    /**
     * Delete all from MerchantPaymentSchedule.
     * 
     * @param id
     * @return MerchantPaymentSchedule
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM merchant_payment_schedules WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from MerchantPaymentSchedule.
     *
     * @return MerchantPaymentSchedule
     */
    public Integer delete() {
        final String query = "DELETE FROM merchant_payment_schedules";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in MerchantPaymentSchedule.
     *
     * @return MerchantPaymentSchedule
     */
    public List<MerchantPaymentSchedule> list() {
        final String query = "SELECT id, payment_date, amount, created_by, updated_by, is_deleted, status, active_inactive, account_id, contract_payment_id, created_at, updated_at FROM merchant_payment_schedules";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return MerchantPaymentSchedule
     * @throws SQLException
     */
    private MerchantPaymentSchedule mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final MerchantPaymentSchedule merchantPaymentSchedule = new MerchantPaymentSchedule();
        merchantPaymentSchedule.setId(rs.getInt("id"));
        merchantPaymentSchedule.setPaymentDate(rs.getDate("payment_date"));
        merchantPaymentSchedule.setAmount(rs.getInt("amount"));
        merchantPaymentSchedule.setCreatedBy(rs.getInt("created_by"));
        merchantPaymentSchedule.setUpdatedBy(rs.getInt("updated_by"));
        merchantPaymentSchedule.setIsDeleted(rs.getShort("is_deleted"));
        merchantPaymentSchedule.setStatus(rs.getShort("status"));
        merchantPaymentSchedule.setActiveInactive(rs.getString("active_inactive"));
        merchantPaymentSchedule.setAccountId(rs.getInt("account_id"));
        merchantPaymentSchedule.setContractPaymentId(rs.getInt("contract_payment_id"));
        merchantPaymentSchedule.setCreatedAt(rs.getDate("created_at"));
        merchantPaymentSchedule.setUpdatedAt(rs.getDate("updated_at"));
        return merchantPaymentSchedule;
    }
}
