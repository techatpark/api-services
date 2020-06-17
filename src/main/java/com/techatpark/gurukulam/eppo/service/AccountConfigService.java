package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountConfig;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountConfigService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AccountConfig related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountConfigService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into AccountConfig table.
     * 
     * @param newAccountConfig
     * @return reads the input data
     */
    public AccountConfig create(final AccountConfig newAccountConfig) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_configs")
                .usingGeneratedKeyColumns("id").usingColumns("configurability_name", "configurability_double_value",
                        "configurability_int_value", "configurability_string_value_1", "configurability_char_value_1",
                        "created_by", "updated_by", "is_deleted", "status", "account_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("configurability_name", newAccountConfig.getConfigurabilityName());
        valuesMap.put("configurability_double_value", newAccountConfig.getConfigurabilityDoubleValue());
        valuesMap.put("configurability_int_value", newAccountConfig.getConfigurabilityIntValue());
        valuesMap.put("configurability_string_value_1", newAccountConfig.getConfigurabilityStringValue1());
        valuesMap.put("configurability_char_value_1", newAccountConfig.getConfigurabilityCharValue1());
        valuesMap.put("created_by", newAccountConfig.getCreatedBy());
        valuesMap.put("updated_by", newAccountConfig.getUpdatedBy());
        valuesMap.put("is_deleted", newAccountConfig.getIsDeleted());
        valuesMap.put("status", newAccountConfig.getStatus());
        valuesMap.put("account_id", newAccountConfig.getAccountId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table AccountConfig.
     *
     * @param id
     * @return AccountConfig
     */
    public Optional<AccountConfig> read(final Integer id) {
        final String query = "SELECT configurability_name, configurability_double_value, configurability_int_value, configurability_string_value_1, configurability_char_value_1, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM account_configs WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table AccountConfig.
     *
     * @param id
     * @param newAccountConfig
     * @return AccountConfig
     */
    public AccountConfig update(final Integer id, final AccountConfig newAccountConfig) {
        final String query = "UPDATE account_configs SET configurability_name = ?, configurability_double_value = ?, configurability_int_value = ?, configurability_string_value_1 = ?, configurability_char_value_1 = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAccountConfig.getConfigurabilityName(),
                newAccountConfig.getConfigurabilityDoubleValue(), newAccountConfig.getConfigurabilityIntValue(),
                newAccountConfig.getConfigurabilityStringValue1(), newAccountConfig.getConfigurabilityCharValue1(),
                newAccountConfig.getCreatedBy(), newAccountConfig.getUpdatedBy(), newAccountConfig.getIsDeleted(),
                newAccountConfig.getStatus(), newAccountConfig.getAccountId(), id);
        return read(id).get();
    }

    /**
     * Delete all from AccountConfig.
     * 
     * @param id
     * @return AccountConfig
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM account_configs WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from AccountConfig.
     *
     * @return AccountConfig
     */
    public Integer delete() {
        final String query = "DELETE FROM account_configs";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in AccountConfig.
     *
     * @return AccountConfig
     */
    public List<AccountConfig> list() {
        final String query = "SELECT id, configurability_name, configurability_double_value, configurability_int_value, configurability_string_value_1, configurability_char_value_1, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM ccount_configs";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return AccountConfig
     * @throws SQLException
     */
    private AccountConfig mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AccountConfig accountConfig = new AccountConfig();
        accountConfig.setId(rs.getInt("id"));
        accountConfig.setId(rs.getInt("configurability_name"));
        accountConfig.setId(rs.getInt("configurability_double_value"));
        accountConfig.setId(rs.getInt("configurability_int_value"));
        accountConfig.setId(rs.getInt("configurability_string_value_1"));
        accountConfig.setId(rs.getInt("configurability_char_value_1"));
        accountConfig.setId(rs.getInt("created_by"));
        accountConfig.setId(rs.getInt("updated_by"));
        accountConfig.setId(rs.getInt("is_deleted"));
        accountConfig.setId(rs.getInt("status"));
        accountConfig.setId(rs.getInt("account_id"));
        accountConfig.setCreatedAt(rs.getDate("created_at"));
        accountConfig.setUpdatedAt(rs.getDate("updated_at"));
        return accountConfig;
    }
}
