
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Transaction;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Transaction related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public TransactionService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Transaction table.
     * 
     * @param newTransaction
     * @return reads the input data
     */
    public Transaction create(final Transaction newTransaction) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("transactions")
                .usingGeneratedKeyColumns("id").usingColumns("transaction_date", "payment_type", "bank_account_id",
                        "amount", "payment_provider", "provider_transaction_id", "provider_transaction_status",
                        "parent_transaction_id", "customer_payment_id", "created_by", "updated_by", "is_deleted",
                        "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("transaction_date", newTransaction.getTransactionDate());
        valuesMap.put("payment_type", newTransaction.getTransactionDate());
        valuesMap.put("bank_account_id", newTransaction.getTransactionDate());
        valuesMap.put("amount", newTransaction.getTransactionDate());
        valuesMap.put("payment_provider", newTransaction.getTransactionDate());
        valuesMap.put("provider_transaction_id", newTransaction.getTransactionDate());
        valuesMap.put("provider_transaction_status", newTransaction.getTransactionDate());
        valuesMap.put("parent_transaction_id", newTransaction.getTransactionDate());
        valuesMap.put("customer_payment_id", newTransaction.getTransactionDate());
        valuesMap.put("created_by", newTransaction.getTransactionDate());
        valuesMap.put("updated_by", newTransaction.getTransactionDate());
        valuesMap.put("is_deleted", newTransaction.getTransactionDate());
        valuesMap.put("status", newTransaction.getTransactionDate());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Transaction.
     *
     * @param id
     * @return Transaction
     */
    public Optional<Transaction> read(final Integer id) {
        final String query = "SELECT transaction_date, payment_type, bank_account_id, amount, payment_provider, provider_transaction_id, provider_transaction_status, parent_transaction_id, customer_payment_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM transactions WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Transaction.
     *
     * @param id
     * @param newTransaction
     * @return Transaction
     */
    public Transaction update(final Integer id, final Transaction newTransaction) {
        final String query = "UPDATE transactions SET transaction_date = ?, payment_type = ?, bank_account_id = ?, amount = ?, payment_provider = ?, provider_transaction_id = ?, provider_transaction_status = ?, parent_transaction_id = ?, customer_payment_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newTransaction.getTransactionDate(), newTransaction.getPaymentType(),
                newTransaction.getBankAccountId(), newTransaction.getAmount(), newTransaction.getPaymentProvider(),
                newTransaction.getProviderTransactionId(), newTransaction.getProviderTransactionStatus(),
                newTransaction.getParentTransactionId(), newTransaction.getCustomerPaymentId(),
                newTransaction.getCreatedBy(), newTransaction.getUpdatedBy(), newTransaction.getIsDeleted(),
                newTransaction.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Transaction.
     * 
     * @param id
     * @return Transaction
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM transactions WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Transaction.
     *
     * @return Transaction
     */
    public Integer delete() {
        final String query = "DELETE FROM transactions";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Transaction.
     *
     * @return Transaction
     */
    public List<Transaction> list() {
        final String query = "SELECT id, transaction_date, payment_type, bank_account_id, amount, payment_provider, provider_transaction_id, provider_transaction_status, parent_transaction_id, customer_payment_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM transactions";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Transaction
     * @throws SQLException
     */
    private Transaction mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setTransactionDate(rs.getDate("transaction_date"));
        transaction.setPaymentType(rs.getString("payment_type"));
        transaction.setBankAccountId(rs.getInt("bank_account_id"));
        transaction.setAmount(rs.getLong("amount"));
        transaction.setPaymentProvider(rs.getString("payment_provider"));
        transaction.setProviderTransactionId(rs.getInt("provider_transaction_id"));
        transaction.setProviderTransactionStatus(rs.getString("provider_transaction_status"));
        transaction.setParentTransactionId(rs.getInt("parent_transaction_id"));
        transaction.setCustomerPaymentId(rs.getInt("customer_payment_id"));
        transaction.setCreatedBy(rs.getInt("created_by"));
        transaction.setUpdatedBy(rs.getInt("updated_by"));
        transaction.setIsDeleted(rs.getInt("is_deleted"));
        transaction.setStatus(rs.getInt("status"));
        transaction.setCreatedAt(rs.getDate("created_at"));
        transaction.setUpdatedAt(rs.getDate("updated_at"));
        return transaction;
    }
}
