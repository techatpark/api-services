
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.CustomerPayment;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class CustomerPaymentService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for CustomerPayment related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public CustomerPaymentService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into CustomerPayment table.
     * 
     * @param newCustomerPayment
     * @return reads the input data
     */
    public CustomerPayment create(final CustomerPayment newCustomerPayment) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("customer_payments")
                .usingGeneratedKeyColumns("id").usingColumns("payment_date", "amount", "smoothing_fee",
                        "eppopay_commission", "net_amount", "contract_payment_id", "created_by", "updated_by",
                        "is_deleted", "status", "active_inactive");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("payment_date", newCustomerPayment.getPaymentDate());
        valuesMap.put("amount", newCustomerPayment.getPaymentDate());
        valuesMap.put("smoothing_fee", newCustomerPayment.getPaymentDate());
        valuesMap.put("eppopay_commission", newCustomerPayment.getPaymentDate());
        valuesMap.put("net_amount", newCustomerPayment.getPaymentDate());
        valuesMap.put("contract_payment_id", newCustomerPayment.getPaymentDate());
        valuesMap.put("created_by", newCustomerPayment.getPaymentDate());
        valuesMap.put("updated_by", newCustomerPayment.getPaymentDate());
        valuesMap.put("is_deleted", newCustomerPayment.getPaymentDate());
        valuesMap.put("status", newCustomerPayment.getPaymentDate());
        valuesMap.put("active_inactive", newCustomerPayment.getPaymentDate());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table CustomerPayment.
     *
     * @param id
     * @return CustomerPayment
     */
    public Optional<CustomerPayment> read(final Integer id) {
        final String query = "SELECT payment_date, amount, smoothing_fee, eppopay_commission, net_amount, contract_payment_id, created_by, updated_by, is_deleted, status, active_inactive, created_at, updated_at FROM customer_payments WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table CustomerPayment.
     *
     * @param id
     * @param newCustomerPayment
     * @return CustomerPayment
     */
    public CustomerPayment update(final Integer id, final CustomerPayment newCustomerPayment) {
        final String query = "UPDATE customer_payments SET payment_date = ?, amount = ?, smoothing_fee = ?, eppopay_commission = ?, net_amount = ?, contract_payment_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, active_inactive = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newCustomerPayment.getPaymentDate(), newCustomerPayment.getAmount(),
                newCustomerPayment.getSmoothingFee(), newCustomerPayment.getEppopayCommission(),
                newCustomerPayment.getNetAmount(), newCustomerPayment.getContractPaymentId(),
                newCustomerPayment.getCreatedBy(), newCustomerPayment.getUpdatedBy(), newCustomerPayment.getIsDeleted(),
                newCustomerPayment.getStatus(), newCustomerPayment.getActiveInactive(), id);
        return read(id).get();
    }

    /**
     * Delete all from CustomerPayment.
     * 
     * @param id
     * @return CustomerPayment
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM customer_payments WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from CustomerPayment.
     *
     * @return CustomerPayment
     */
    public Integer delete() {
        final String query = "DELETE FROM customer_payments";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in CustomerPayment.
     *
     * @return CustomerPayment
     */
    public List<CustomerPayment> list() {
        final String query = "SELECT id, payment_date, amount, smoothing_fee, eppopay_commission, net_amount, contract_payment_id, created_by, updated_by, is_deleted, status, active_inactive, created_at, updated_at FROM customer_payments";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return CustomerPayment
     * @throws SQLException
     */
    private CustomerPayment mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setId(rs.getInt("id"));
        customerPayment.setPaymentDate(rs.getDate("payment_date"));
        customerPayment.setAmount(rs.getInt("amount"));
        customerPayment.setSmoothingFee(rs.getInt("smoothing_fee"));
        customerPayment.setEppopayCommission(rs.getInt("eppopay_commission"));
        customerPayment.setNetAmount(rs.getInt("net_amount"));
        customerPayment.setContractPaymentId(rs.getInt("contract_payment_id"));
        customerPayment.setCreatedBy(rs.getInt("created_by"));
        customerPayment.setUpdatedBy(rs.getInt("updated_by"));
        customerPayment.setIsDeleted(rs.getShort("is_deleted"));
        customerPayment.setStatus(rs.getShort("status"));
        customerPayment.setActiveInactive(rs.getString("active_inactive"));
        customerPayment.setCreatedAt(rs.getDate("created_at"));
        customerPayment.setUpdatedAt(rs.getDate("updated_at"));
        return customerPayment;
    }
}
