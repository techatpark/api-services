package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountCustomer;
import com.techatpark.gurukulam.eppo.model.Customer;

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
     * @param newAccountCustomer
     * @return reads the input data
     */
    public AccountCustomer create(final Customer newAccountCustomer) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_customers")
                .usingGeneratedKeyColumns("id").usingColumns("first_name", "middle_name", "last_name", "email_id",
                        "primary_phone_number", "secondary_phone_number", "dob", "created_by", "updated_by",
                        "is_deleted", "status", "address_id", "created_at", "updated_at");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("first_name", newAccountCustomer.getFirstName());
        valuesMap.put("middle_name", newAccountCustomer.getMiddleName());
        valuesMap.put("last_name", newAccountCustomer.getLastName());

        valuesMap.put("email_id", newAccountCustomer.getEmailId());
        valuesMap.put("primary_phone_number", newAccountCustomer.getPrimaryPhoneNumber());
        valuesMap.put("secondary_phone_number", newAccountCustomer.getSecondaryPhoneNumber());
        valuesMap.put("dob", newAccountCustomer.getDob());

        valuesMap.put("created_by", newAccountCustomer.getCreatedBy());
        valuesMap.put("updated_by", newAccountCustomer.getUpdatedBy());
        valuesMap.put("is_deleted", newAccountCustomer.getIsDeleted());
        valuesMap.put("status", newAccountCustomer.getStatus());
        valuesMap.put("address_id", newAccountCustomer.getAddressId());

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
     * @param customerToBeUpdated
     * @return Customer
     */
    public AccountCustomer update(final Integer id, final Customer customerToBeUpdated) {
        final String query = "UPDATE account_customers SET first_name = ?, middle_name = ?, last_name = ?, email_id = ?, primary_phone_number = ?, secondary_phone_number = ?, dob = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, address_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, customerToBeUpdated.getFirstName(), customerToBeUpdated.getMiddleName(),
                customerToBeUpdated.getLastName(), customerToBeUpdated.getSalutation(),
                customerToBeUpdated.getEmailId(), customerToBeUpdated.getPrimaryPhoneNumber(),
                customerToBeUpdated.getSecondaryPhoneNumber(), customerToBeUpdated.getDob(),
                customerToBeUpdated.getEmailNotifPreference(), customerToBeUpdated.getTextNotifPreference(),
                customerToBeUpdated.getCreatedBy(), customerToBeUpdated.getUpdatedBy(),
                customerToBeUpdated.getIsDeleted(), customerToBeUpdated.getStatus(), customerToBeUpdated.getAddressId(),
                id);
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

        accountCustomer.setCreatedBy(rs.getInt("created_by"));
        accountCustomer.setUpdatedBy(rs.getInt("updated_by"));
        accountCustomer.setIsDeleted(rs.getInt("is_deleted"));
        accountCustomer.setStatus(rs.getInt("status"));
        accountCustomer.setAddressId(rs.getInt("address_id"));
        accountCustomer.setCreatedAt(rs.getDate("created_at"));
        accountCustomer.setUpdatedAt(rs.getDate("updated_at"));
        return accountCustomer;
    }
}
