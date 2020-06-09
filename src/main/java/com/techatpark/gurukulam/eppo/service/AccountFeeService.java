package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountFee;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountFeeService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AccountFee related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountFeeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into AccountFee table.
     * 
     * @param newAccountFee
     * @return reads the input data
     */
    public AccountFee create(final AccountFee newAccountFee) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_fees")
                .usingGeneratedKeyColumns("id").usingColumns("account_id", "ach_return_fee");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("account_id", newAccountFee.getAccountId());
        valuesMap.put("ach_return_fee", newAccountFee.getAchReturnFee());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table AccountFee.
     *
     * @param id
     * @return AccountFee
     */
    public Optional<AccountFee> read(final Integer id) {
        final String query = "SELECT account_id, ach_return_fee, created_at, updated_at FROM account_fees WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table AccountFee.
     *
     * @param id
     * @param newAccountFee
     * @return AccountFee
     */
    public AccountFee update(final Integer id, final AccountFee newAccountFee) {
        final String query = "UPDATE account_fees SET account_id = ?, ach_return_fee = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAccountFee.getAccountId(), newAccountFee.getAchReturnFee(), id);
        return read(id).get();
    }

    /**
     * Delete all from AccountFee.
     * 
     * @param id
     * @return AccountFee
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM account_fees";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from AccountFee.
     *
     * @return AccountFee
     */
    public Integer delete() {
        final String query = "DELETE FROM account_fees";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in AccountFee.
     *
     * @return AccountFee
     */
    public List<AccountFee> list() {
        final String query = "SELECT id, account_id, ach_return_fee, created_at, updated_at FROM account_fees";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return AccountFee
     * @throws SQLException
     */
    private AccountFee mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AccountFee accountFee = new AccountFee();
        accountFee.setId(rs.getInt("id"));
        accountFee.setAccountId(rs.getInt("account_id"));
        accountFee.setAchReturnFee(rs.getLong("ach_return_fee"));
        accountFee.setCreatedAt(rs.getDate("created_at"));
        accountFee.setUpdatedAt(rs.getDate("updated_at"));
        return accountFee;
    }
}
