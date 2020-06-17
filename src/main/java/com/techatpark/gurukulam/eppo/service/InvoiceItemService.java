
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.InvoiceItem;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class InvoiceItemService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for InvoiceItem related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public InvoiceItemService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into InvoiceItem table.
     * 
     * @param newInvoiceItem
     * @return reads the input data
     */
    public InvoiceItem create(final InvoiceItem newInvoiceItem) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("invoice_items")
                .usingGeneratedKeyColumns("id").usingColumns("amount", "created_by", "customer_payment_id",
                        "invoice_id", "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("amount", newInvoiceItem.getAmount());
        valuesMap.put("created_by", newInvoiceItem.getCreatedBy());
        valuesMap.put("customer_payment_id", newInvoiceItem.getCustomerPaymentId());
        valuesMap.put("invoice_id", newInvoiceItem.getInvoiceId());
        valuesMap.put("updated_by", newInvoiceItem.getUpdatedBy());
        valuesMap.put("is_deleted", newInvoiceItem.getIsDeleted());
        valuesMap.put("status", newInvoiceItem.getStatus());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table InvoiceItem.
     *
     * @param id
     * @return InvoiceItem
     */
    public Optional<InvoiceItem> read(final Integer id) {
        final String query = "SELECT amount, created_by, customer_payment_id, invoice_id, updated_by, is_deleted, status, created_at, updated_at FROM invoice_items WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table InvoiceItem.
     *
     * @param id
     * @param newInvoiceItem
     * @return InvoiceItem
     */
    public InvoiceItem update(final Integer id, final InvoiceItem newInvoiceItem) {
        final String query = "UPDATE invoice_items SET amount = ?, created_by = ?, customer_payment_id = ?, invoice_id = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newInvoiceItem.getAmount(), newInvoiceItem.getCreatedBy(),
                newInvoiceItem.getCustomerPaymentId(), newInvoiceItem.getInvoiceId(), newInvoiceItem.getUpdatedBy(),
                newInvoiceItem.getIsDeleted(), newInvoiceItem.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from InvoiceItem.
     * 
     * @param id
     * @return InvoiceItem
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM invoice_items WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from InvoiceItem.
     *
     * @return InvoiceItem
     */
    public Integer delete() {
        final String query = "DELETE FROM invoice_items";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in InvoiceItem.
     *
     * @return InvoiceItem
     */
    public List<InvoiceItem> list() {
        final String query = "SELECT id, amount, created_by, customer_payment_id, invoice_id, updated_by, is_deleted, status, created_at, updated_at FROM invoice_items";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return InvoiceItem
     * @throws SQLException
     */
    private InvoiceItem mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(rs.getInt("id"));
        invoiceItem.setId(rs.getInt("amount"));
        invoiceItem.setId(rs.getInt("created_by"));
        invoiceItem.setId(rs.getInt("customer_payment_id"));
        invoiceItem.setId(rs.getInt("invoice_id"));
        invoiceItem.setId(rs.getInt("updated_by"));
        invoiceItem.setId(rs.getInt("is_deleted"));
        invoiceItem.setId(rs.getInt("status"));
        invoiceItem.setCreatedAt(rs.getDate("created_at"));
        invoiceItem.setUpdatedAt(rs.getDate("updated_at"));
        return invoiceItem;
    }
}
