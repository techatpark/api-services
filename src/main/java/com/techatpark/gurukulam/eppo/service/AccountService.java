package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Account;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Account related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Account table.
     * 
     * @param newAccount
     * @return reads the input data
     */
    public Account create(final Account newAccount) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("accounts")
                .usingGeneratedKeyColumns("id").usingColumns("account_code", "account_name", "phone_no", "email_id",
                        "company_name", "tax_id", "created_by", "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("account_code", newAccount.getAccountCode());
        valuesMap.put("account_name", newAccount.getAccountName());
        valuesMap.put("phone_no", newAccount.getPhoneNo());
        valuesMap.put("email_id", newAccount.getEmailId());
        valuesMap.put("company_name", newAccount.getCompanyName());
        valuesMap.put("tax_id", newAccount.getTaxId());
        valuesMap.put("created_by", newAccount.getCreatedBy());
        valuesMap.put("updated_by", newAccount.getUpdatedBy());
        valuesMap.put("is_deleted", newAccount.getIsDeleted());
        valuesMap.put("status", newAccount.getStatus());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Account.
     *
     * @param id
     * @return Account
     */
    public Optional<Account> read(final Integer id) {
        final String query = "SELECT account_code, account_name, phone_no, email_id, company_name, tax_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM accounts WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Account.
     *
     * @param id
     * @param newAccount
     * @return Account
     */
    public Account update(final Integer id, final Account newAccount) {
        final String query = "UPDATE accounts SET account_code = ?, account_name = ?, phone_no = ?, email_id = ?, company_name = ?, tax_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAccount.getAccountCode(), newAccount.getAccountName(), newAccount.getPhoneNo(),
                newAccount.getEmailId(), newAccount.getCompanyName(), newAccount.getTaxId(), newAccount.getCreatedAt(),
                newAccount.getUpdatedBy(), newAccount.getIsDeleted(), newAccount.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Account.
     * 
     * @param id
     * @return Account
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM accounts WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Account.
     *
     * @return Account
     */
    public Integer delete() {
        final String query = "DELETE FROM accounts";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Account.
     *
     * @return Account
     */
    public List<Account> list() {
        final String query = "SELECT id, account_code, account_name, phone_no, email_id, company_name, tax_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM accounts";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Account
     * @throws SQLException
     */
    private Account mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setAccountCode(rs.getString("account_code"));
        account.setAccountName(rs.getString("account_name"));
        account.setPhoneNo(rs.getString("phone_no"));
        account.setEmailId(rs.getString("email_id"));
        account.setCompanyName(rs.getString("company_name"));
        account.setTaxId(rs.getString("tax_id"));
        account.setCreatedBy(rs.getInt("created_by"));
        account.setUpdatedBy(rs.getInt("updated_by"));
        account.setIsDeleted(rs.getInt("is_deleted"));
        account.setStatus(rs.getInt("status"));
        account.setCreatedAt(rs.getDate("created_at"));
        account.setUpdatedAt(rs.getDate("updated_at"));
        return account;
    }
}
