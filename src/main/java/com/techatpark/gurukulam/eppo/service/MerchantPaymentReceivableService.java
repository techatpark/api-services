
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.MerchantPaymentReceivable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class MerchantPaymentReceivableService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for MerchantPaymentReceivable related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public MerchantPaymentReceivableService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into MerchantPaymentReceivable table.
     * 
     * @param newMerchantPaymentReceivable
     * @return reads the input data
     */
    public MerchantPaymentReceivable create(final MerchantPaymentReceivable newMerchantPaymentReceivable) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("merchant_payment_receivables")
                .usingGeneratedKeyColumns("id").usingColumns("day_of_month", "percent_receivable", "created_by",
                        "updated_by", "is_deleted", "status", "account_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("day_of_month", newMerchantPaymentReceivable.getDayOfMonth());
        valuesMap.put("percent_receivable", newMerchantPaymentReceivable.getDayOfMonth());
        valuesMap.put("created_by", newMerchantPaymentReceivable.getCreatedBy());
        valuesMap.put("updated_by", newMerchantPaymentReceivable.getUpdatedBy());
        valuesMap.put("is_deleted", newMerchantPaymentReceivable.getIsDeleted());
        valuesMap.put("status", newMerchantPaymentReceivable.getStatus());
        valuesMap.put("account_id", newMerchantPaymentReceivable.getAccountId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table MerchantPaymentReceivable.
     *
     * @param id
     * @return MerchantPaymentReceivable
     */
    public Optional<MerchantPaymentReceivable> read(final Integer id) {
        final String query = "SELECT day_of_month, percent_receivable, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM merchant_payment_receivables WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table MerchantPaymentReceivable.
     *
     * @param id
     * @param newMerchantPaymentReceivable
     * @return MerchantPaymentReceivable
     */
    public MerchantPaymentReceivable update(final Integer id,
            final MerchantPaymentReceivable newMerchantPaymentReceivable) {
        final String query = "UPDATE merchant_payment_receivables SET day_of_month = ?, percent_receivable = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newMerchantPaymentReceivable.getDayOfMonth(),
                newMerchantPaymentReceivable.getPercentReceivable(), newMerchantPaymentReceivable.getCreatedBy(),
                newMerchantPaymentReceivable.getUpdatedBy(), newMerchantPaymentReceivable.getIsDeleted(),
                newMerchantPaymentReceivable.getStatus(), newMerchantPaymentReceivable.getAccountId(), id);
        return read(id).get();
    }

    /**
     * Delete all from MerchantPaymentReceivable.
     * 
     * @param id
     * @return MerchantPaymentReceivable
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM merchant_payment_receivables WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from MerchantPaymentReceivable.
     *
     * @return MerchantPaymentReceivable
     */
    public Integer delete() {
        final String query = "DELETE FROM merchant_payment_receivables";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in MerchantPaymentReceivable.
     *
     * @return MerchantPaymentReceivable
     */
    public List<MerchantPaymentReceivable> list() {
        final String query = "SELECT id, day_of_month, percent_receivable, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM merchant_payment_receivables";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return MerchantPaymentReceivable
     * @throws SQLException
     */
    private MerchantPaymentReceivable mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final MerchantPaymentReceivable merchantPaymentReceivable = new MerchantPaymentReceivable();
        merchantPaymentReceivable.setId(rs.getInt("id"));
        merchantPaymentReceivable.setDayOfMonth(rs.getDate("day_of_month"));
        merchantPaymentReceivable.setPercentReceivable(rs.getInt("percent_receivable"));
        merchantPaymentReceivable.setCreatedBy(rs.getInt("created_by"));
        merchantPaymentReceivable.setUpdatedBy(rs.getInt("updated_by"));
        merchantPaymentReceivable.setIsDeleted(rs.getShort("is_deleted"));
        merchantPaymentReceivable.setStatus(rs.getShort("status"));
        merchantPaymentReceivable.setAccountId(rs.getInt("account_id"));
        merchantPaymentReceivable.setCreatedAt(rs.getDate("created_at"));
        merchantPaymentReceivable.setUpdatedAt(rs.getDate("updated_at"));
        return merchantPaymentReceivable;
    }
}
