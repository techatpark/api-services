package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountContractOffset;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountContractOffsetService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AccountContractOffset related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountContractOffsetService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into AccountContractOffset table.
     * 
     * @param newAccountContractOffset
     * @return reads the input data
     */
    public AccountContractOffset create(final AccountContractOffset newAccountContractOffset) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_contract_offset")
                .usingGeneratedKeyColumns("id").usingColumns("account_code", "contract_offset");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("account_code", newAccountContractOffset.getAccountCode());
        valuesMap.put("contract_offset", newAccountContractOffset.getContractOffset());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table AccountContractOffset.
     *
     * @param id
     * @return AccountContractOffset
     */
    public Optional<AccountContractOffset> read(final Integer id) {
        final String query = "SELECT account_code, contract_offset, created_at, updated_at FROM account_contract_offset WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table AccountContractOffset.
     *
     * @param id
     * @param newAccountContractOffset
     * @return AccountContractOffset
     */
    public AccountContractOffset update(final Integer id, final AccountContractOffset newAccountContractOffset) {
        final String query = "UPDATE account_contract_offset SET account_code = ?, contract_offset = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAccountContractOffset.getAccountCode(),
                newAccountContractOffset.getContractOffset(), id);
        return read(id).get();
    }

    /**
     * Delete all from AccountContractOffset.
     * 
     * @param id
     * @return AccountContractOffset
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM account_contract_offset";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from AccountContractOffset.
     *
     * @return AccountContractOffset
     */
    public Integer delete() {
        final String query = "DELETE FROM account_contract_offset";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in AccountContractOffset.
     *
     * @return AccountContractOffset
     */
    public List<AccountContractOffset> list() {
        final String query = "SELECT id, account_code, contract_offset, created_at, updated_at FROM account_contract_offset";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return AccountContractOffset
     * @throws SQLException
     */
    private AccountContractOffset mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AccountContractOffset accountContractOffset = new AccountContractOffset();
        accountContractOffset.setId(rs.getInt("id"));
        accountContractOffset.setAccountCode(rs.getString("account_code"));
        accountContractOffset.setContractOffset(rs.getInt("contract_offset"));
        accountContractOffset.setCreatedAt(rs.getDate("created_at"));
        accountContractOffset.setUpdatedAt(rs.getDate("updated_at"));
        return accountContractOffset;
    }
}
