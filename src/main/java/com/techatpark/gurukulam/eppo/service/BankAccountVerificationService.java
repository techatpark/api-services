package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.BankAccountVerification;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class BankAccountVerificationService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for BankAccountVerification related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public BankAccountVerificationService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into BankAccountVerification table.
     * 
     * @param newBankAccountVerification
     * @return reads the input data
     */
    public BankAccountVerification create(final BankAccountVerification newBankAccountVerification) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("bank_account_verifications")
                .usingGeneratedKeyColumns("id").usingColumns("subdollar_amount1", "subdollar_amount2", "created_by",
                        "updated_by", "is_deleted", "bank_account_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("subdollar_amount1", newBankAccountVerification.getSubdollarAmount1());
        valuesMap.put("subdollar_amount2", newBankAccountVerification.getSubdollarAmount2());
        valuesMap.put("created_by", newBankAccountVerification.getCreatedBy());
        valuesMap.put("updated_by", newBankAccountVerification.getUpdatedBy());
        valuesMap.put("is_deleted", newBankAccountVerification.getIsDeleted());
        valuesMap.put("bank_account_id", newBankAccountVerification.getBankAccountId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table BankAccountVerification.
     *
     * @param id
     * @return BankAccountVerification
     */
    public Optional<BankAccountVerification> read(final Integer id) {
        final String query = "SELECT subdollar_amount1, subdollar_amount2, created_by, updated_by, is_deleted, bank_account_id, created_at, updated_at FROM bank_account_verifications WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table BankAccountVerification.
     *
     * @param id
     * @param newBankAccountVerification
     * @return BankAccountVerification
     */
    public BankAccountVerification update(final Integer id, final BankAccountVerification newBankAccountVerification) {
        final String query = "UPDATE bank_account_verifications SET subdollar_amount1 = ?, subdollar_amount2 = ?, created_by = ?, updated_by = ?, is_deleted = ?, bank_account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newBankAccountVerification.getSubdollarAmount1(),
                newBankAccountVerification.getSubdollarAmount2(), newBankAccountVerification.getCreatedBy(),
                newBankAccountVerification.getUpdatedBy(), newBankAccountVerification.getIsDeleted(),
                newBankAccountVerification.getBankAccountId(), id);
        return read(id).get();
    }

    /**
     * Delete all from BankAccountVerification.
     * 
     * @param id
     * @return BankAccountVerification
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM bank_account_verifications WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from BankAccountVerification.
     *
     * @return BankAccountVerification
     */
    public Integer delete() {
        final String query = "DELETE FROM bank_account_verifications";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in BankAccountVerification.
     *
     * @return BankAccountVerification
     */
    public List<BankAccountVerification> list() {
        final String query = "SELECT id, subdollar_amount1, subdollar_amount2, created_by, updated_by, is_deleted, bank_account_id, created_at, updated_at FROM bank_account_verifications";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return BankAccountVerification
     * @throws SQLException
     */
    private BankAccountVerification mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final BankAccountVerification bankAccountVerification = new BankAccountVerification();
        bankAccountVerification.setId(rs.getInt("id"));
        bankAccountVerification.setSubdollarAmount1(rs.getInt("subdollar_amount1"));
        bankAccountVerification.setSubdollarAmount2(rs.getInt("subdollar_amount2"));
        bankAccountVerification.setCreatedBy(rs.getInt("created_by"));
        bankAccountVerification.setUpdatedBy(rs.getInt("updated_by"));
        bankAccountVerification.setIsDeleted(rs.getShort("is_deleted"));
        bankAccountVerification.setBankAccountId(rs.getInt("bank_account_id"));
        bankAccountVerification.setCreatedAt(rs.getDate("created_at"));
        bankAccountVerification.setUpdatedAt(rs.getDate("updated_at"));
        return bankAccountVerification;
    }
}
