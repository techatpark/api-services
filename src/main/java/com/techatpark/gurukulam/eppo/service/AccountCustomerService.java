package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;
import javax.validation.Valid;

import com.techatpark.gurukulam.eppo.model.AccountCustomer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AccountCustomerService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Customer related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountCustomerService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Customer table.
     * 
     * @param accountCustomer
     * @return reads the input data
     */
    public AccountCustomer create(final @Valid AccountCustomer accountCustomer) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_customers")
                .usingGeneratedKeyColumns("id").usingColumns("first_name", "middle_name", "last_name", "email_id",
                        "primary_phone_number", "secondary_phone_number", "dob", "account_id", "created_by",
                        "updated_by", "is_deleted", "status", "created_at", "updated_at");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("first_name", accountCustomer.getFirstName());
        valuesMap.put("middle_name", accountCustomer.getMiddleName());
        valuesMap.put("last_name", accountCustomer.getLastName());
        valuesMap.put("email_id", accountCustomer.getEmailId());
        valuesMap.put("primary_phone_number", accountCustomer.getPrimaryPhoneNumber());
        valuesMap.put("secondary_phone_number", accountCustomer.getSecondaryPhoneNumber());
        valuesMap.put("dob", accountCustomer.getDob());
        valuesMap.put("account_id", accountCustomer.getAccountId());
        valuesMap.put("created_by", accountCustomer.getCreatedBy());
        valuesMap.put("updated_by", accountCustomer.getUpdatedBy());
        valuesMap.put("is_deleted", accountCustomer.getIsDeleted());
        valuesMap.put("status", accountCustomer.getStatus());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Customer.
     *
     * @param id
     * @return Customer
     */
    public Optional<AccountCustomer> read(final Integer id) {
        final String query = "SELECT first_name, middle_name, last_name, email_id, primary_phone_number, secondary_phone_number, dob, created_by, updated_by, is_deleted, status, address_id,created_at,updated_at FROM account_customers WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Customer.
     *
     * @param id
     * @param accountCustomer
     * @return Customer
     */
    public AccountCustomer update(final Integer id, final @Valid AccountCustomer accountCustomer) {
        final String query = "UPDATE account_customers SET first_name = ?, middle_name = ?, last_name = ?, primary_phone_number = ?, secondary_phone_number = ?, email_id = ?, dob = ?,account_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, accountCustomer.getFirstName(), accountCustomer.getMiddleName(),
                accountCustomer.getLastName(), accountCustomer.getPrimaryPhoneNumber(),
                accountCustomer.getSecondaryPhoneNumber(), accountCustomer.getEmailId(), accountCustomer.getDob(),
                accountCustomer.getAccountId(), accountCustomer.getCreatedBy(), accountCustomer.getUpdatedBy(),
                accountCustomer.getIsDeleted(), accountCustomer.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Customer.
     * 
     * @param id
     * @return Customer
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM account_customers";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Customer.
     *
     * @return Customer
     */
    public Integer delete() {
        final String query = "DELETE FROM account_customers";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Customer.
     *
     * @return Customer
     */
    public List<AccountCustomer> list() {
        final String query = "SELECT id, first_name, middle_name, last_name, email_id, primary_phone_number, secondary_phone_number, dob, created_by, updated_by, is_deleted, status, address_id,created_at,updated_at FROM account_customers";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Customer
     * @throws SQLException
     */
    private AccountCustomer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AccountCustomer accountCustomer = new AccountCustomer();
        accountCustomer.setId(rs.getInt("id"));
        accountCustomer.setFirstName(rs.getString("first_name"));
        accountCustomer.setMiddleName(rs.getString("middle_name"));
        accountCustomer.setLastName(rs.getString("last_name"));
        accountCustomer.setEmailId(rs.getString("email_id"));
        accountCustomer.setPrimaryPhoneNumber(rs.getString("primary_phone_number"));
        accountCustomer.setSecondaryPhoneNumber(rs.getString("secondary_phone_number"));
        accountCustomer.setDob(rs.getDate("dob"));
        accountCustomer.setAccountId(rs.getInt("account_id"));
        accountCustomer.setCreatedBy(rs.getInt("created_by"));
        accountCustomer.setUpdatedBy(rs.getInt("updated_by"));
        accountCustomer.setIsDeleted(rs.getInt("is_deleted"));
        accountCustomer.setStatus(rs.getInt("status"));
        accountCustomer.setCreatedAt(rs.getDate("created_at"));
        accountCustomer.setUpdatedAt(rs.getDate("updated_at"));
        return accountCustomer;
    }
}
