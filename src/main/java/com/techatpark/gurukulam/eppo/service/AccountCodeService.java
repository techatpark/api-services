package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountCode;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountCodeService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AccountCode related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountCodeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into AccountCode table.
     * 
     * @param newAccountCode
     * @return reads the input data
     */
    public AccountCode create(final AccountCode newAccountCode) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_codes")
                .usingGeneratedKeyColumns("id").usingColumns("account_code", "code_used");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("account_code", newAccountCode.getAccountCode());
        valuesMap.put("code_used", newAccountCode.getCodeUsed());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table AccountCode.
     *
     * @param id
     * @return AccountCode
     */
    public Optional<AccountCode> read(final Integer id) {
        final String query = "SELECT account_code, code_used, created_at, updated_at FROM account_codes WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table AccountCode.
     *
     * @param id
     * @param newAccountCode
     * @return AccountCode
     */
    public AccountCode update(final Integer id, final AccountCode newAccountCode) {
        final String query = "UPDATE account_codes SET account_code = ?, code_used = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAccountCode.getAccountCode(), newAccountCode.getCodeUsed(), id);
        return read(id).get();
    }

    /**
     * Delete all from AccountCode.
     * 
     * @param id
     * @return AccountCode
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM account_codes";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from AccountCode.
     *
     * @return AccountCode
     */
    public Integer delete() {
        final String query = "DELETE FROM account_codes";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in AccountCode.
     *
     * @return AccountCode
     */
    public List<AccountCode> list() {
        final String query = "SELECT id, account_code, code_used, created_at, updated_at FROM account_codes";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return AccountCode
     * @throws SQLException
     */
    private AccountCode mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AccountCode accountCode = new AccountCode();
        accountCode.setId(rs.getInt("id"));
        accountCode.setAccountCode(rs.getString("account_code"));
        accountCode.setCodeUsed(rs.getString("code_used"));
        accountCode.setCreatedAt(rs.getDate("created_at"));
        accountCode.setUpdatedAt(rs.getDate("updated_at"));
        return accountCode;
    }
}
