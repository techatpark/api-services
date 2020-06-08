package com.techatpark.gurukulam.eppo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountCodes;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountCodesService {

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountCodesService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

     /**
     * Maps the data from and to the database. 
     * @return accountCodes
     */
    private final RowMapper<AccountCodes> rowMapper = (rs, rowNum) -> {
        final AccountCodes accountCodes = new AccountCodes();
        accountCodes.setId(rs.getInt("id"));
        accountCodes.setAccountCode(rs.getString("account_code"));
        accountCodes.setCodeUsed(rs.getString("code_used"));
        accountCodes.setCreatedAt(rs.getDate("created_at"));
        accountCodes.setUpdatedAt(rs.getDate("updated_at"));
        return accountCodes;
    };

    /**
     * Inserting into accountcodes table.
     * 
     * @param accountCodes
     * @return accountcodes
     */
    public AccountCodes create(final AccountCodes accountCodes) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_codes")
                .usingGeneratedKeyColumns("id").usingColumns("account_code", "code_used");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("account_code", accountCodes.getAccountCode());
        valueMap.put("code_used", accountCodes.getCodeUsed());
        final Number id = insert.executeAndReturnKey(valueMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from the accountcodes table.
     * 
     * @param id
     * @return accountcodes
     */
    public Optional<AccountCodes> read(final Integer id) {
        final String query = "SELECT id, account_code, code_used, created_at, updated_at FROM account_codes WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * updates table accountcodes using a id.
     * 
     * @param id
     * @param accountCodes
     * @return accountcodes
     */
    public Optional<AccountCodes> update(final Integer id, final AccountCodes accountCodes) {
        final String query = "UPDATE account_codes SET account_code = ?, code_used = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, accountCodes.getAccountCode(),
                accountCodes.getCodeUsed(), id);
        return updatedRows == 0 ? null : read(id);
    }

    /**
     * deletes a row from the accountcode table with given id.
     * 
     * @param id
     * @return successflag
     */
    public Boolean delete(final Integer id) {
        Boolean success = false;
        String query = "DELETE FROM EXAMS WHERE ID=?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        success = !(updatedRows == 0);
        return success;
    }

}
