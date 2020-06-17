package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Invoice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Invoice related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public InvoiceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Invoice table.
     * 
     * @param newInvoice
     * @return reads the input data
     */
    public Invoice create(final Invoice newInvoice) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("invoices")
                .usingGeneratedKeyColumns("id").usingColumns("invoice_number", "invoice_date", "invoice_to_party",
                        "invoice_to_party_id", "amount", "transaction_id", "created_by", "updated_by", "is_deleted",
                        "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("invoice_number", newInvoice.getInvoiceNumber());
        valuesMap.put("invoice_date", newInvoice.getInvoiceNumber());
        valuesMap.put("invoice_to_party", newInvoice.getInvoiceNumber());
        valuesMap.put("invoice_to_party_id", newInvoice.getInvoiceNumber());
        valuesMap.put("amount", newInvoice.getInvoiceNumber());
        valuesMap.put("transaction_id", newInvoice.getInvoiceNumber());
        valuesMap.put("created_by", newInvoice.getInvoiceNumber());
        valuesMap.put("updated_by", newInvoice.getInvoiceNumber());
        valuesMap.put("is_deleted", newInvoice.getInvoiceNumber());
        valuesMap.put("status", newInvoice.getInvoiceNumber());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Invoice.
     *
     * @param id
     * @return Invoice
     */
    public Optional<Invoice> read(final Integer id) {
        final String query = "SELECT invoice_number, invoice_date, invoice_to_party, invoice_to_party_id, amount, transaction_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM invoices WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Invoice.
     *
     * @param id
     * @param newInvoice
     * @return Invoice
     */
    public Invoice update(final Integer id, final Invoice newInvoice) {
        final String query = "UPDATE invoices SET invoice_number, invoice_date, invoice_to_party, invoice_to_party_id, amount, transaction_id, created_by, updated_by, is_deleted, status, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newInvoice.getInvoiceNumber(), newInvoice.getInvoiceDate(),
                newInvoice.getInvoiceToParty(), newInvoice.getInvoiceToPartyId(), newInvoice.getAmount(),
                newInvoice.getTransactionId(), newInvoice.getCreatedBy(), newInvoice.getUpdatedBy(),
                newInvoice.getIsDeleted(), newInvoice.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Invoice.
     * 
     * @param id
     * @return Invoice
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM invoices WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Invoice.
     *
     * @return Invoice
     */
    public Integer delete() {
        final String query = "DELETE FROM invoices";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Invoice.
     *
     * @return Invoice
     */
    public List<Invoice> list() {
        final String query = "SELECT id, invoice_number, invoice_date, invoice_to_party, invoice_to_party_id, amount, transaction_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM invoices";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Invoice
     * @throws SQLException
     */
    private Invoice mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("id"));
        invoice.setId(rs.getInt("invoice_number"));
        invoice.setId(rs.getInt("invoice_date"));
        invoice.setId(rs.getInt("invoice_to_party"));
        invoice.setId(rs.getInt("invoice_to_party_id"));
        invoice.setId(rs.getInt("amount"));
        invoice.setId(rs.getInt("transaction_id"));
        invoice.setId(rs.getInt("created_by"));
        invoice.setId(rs.getInt("updated_by"));
        invoice.setId(rs.getInt("is_deleted"));
        invoice.setId(rs.getInt("status"));
        invoice.setCreatedAt(rs.getDate("created_at"));
        invoice.setUpdatedAt(rs.getDate("updated_at"));
        return invoice;
    }
}
