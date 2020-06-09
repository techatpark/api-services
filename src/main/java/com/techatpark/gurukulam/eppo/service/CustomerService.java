package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Customer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
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
    public CustomerService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Customer table.
     * 
     * @param newCustomer
     * @return reads the input data
     */
    public Customer create(final Customer newCustomer) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("customers")
                .usingGeneratedKeyColumns("id").usingColumns("first_name", "middle_name", "last_name", "salutation",
                        "email_id", "primary_phone_number", "secondary_phone_number", "dob", "email_notif_preference",
                        "text_notif_preference", "created_by", "updated_by", "is_deleted", "status", "address_id",
                        "created_at", "updated_at");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("first_name", newCustomer.getFirstName());
        valuesMap.put("middle_name", newCustomer.getMiddleName());
        valuesMap.put("last_name", newCustomer.getLastName());
        valuesMap.put("salutation", newCustomer.getSalutation());
        valuesMap.put("email_id", newCustomer.getEmailId());
        valuesMap.put("primary_phone_number", newCustomer.getPrimaryPhoneNumber());
        valuesMap.put("secondary_phone_number", newCustomer.getSecondaryPhoneNumber());
        valuesMap.put("dob", newCustomer.getDob());
        valuesMap.put("email_notif_preference", newCustomer.getEmailNotifPreference());
        valuesMap.put("text_notif_preference", newCustomer.getTextNotifPreference());
        valuesMap.put("created_by", newCustomer.getCreatedBy());
        valuesMap.put("updated_by", newCustomer.getUpdatedBy());
        valuesMap.put("is_deleted", newCustomer.getIsDeleted());
        valuesMap.put("status", newCustomer.getStatus());
        valuesMap.put("address_id", newCustomer.getAddressId());

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
    public Optional<Customer> read(final Integer id) {
        final String query = "SELECT first_name, middle_name, last_name, salutation, email_id, primary_phone_number, secondary_phone_number, dob, email_notif_preference, text_notif_preference, created_by, updated_by, is_deleted, status, address_id,created_at,updated_at FROM customers WHERE id = ?";
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
    public Customer update(final Integer id, final Customer customerToBeUpdated) {
        final String query = "UPDATE customers SET first_name = ?, middle_name = ?, last_name = ?, salutation = ?,  email_id = ?, primary_phone_number = ?, secondary_phone_number = ?, dob = ?, email_notif_preference = ?, text_notif_preference = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, address_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
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
        final String query = "DELETE FROM customers";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Customer.
     *
     * @return Customer
     */
    public Integer delete() {
        final String query = "DELETE FROM Customer";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Customer.
     *
     * @return Customer
     */
    public List<Customer> list() {
        final String query = "SELECT id, first_name, middle_name, last_name, salutation, email_id, primary_phone_number, secondary_phone_number, dob, email_notif_preference, text_notif_preference, created_by, updated_by, is_deleted, status, address_id,created_at,updated_at FROM customers";
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
    private Customer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setMiddleName(rs.getString("middle_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setSalutation(rs.getString("salutation"));
        customer.setEmailId(rs.getString("email_id"));
        customer.setPrimaryPhoneNumber(rs.getString("primary_phone_number"));
        customer.setSecondaryPhoneNumber(rs.getString("secondary_phone_number"));
        customer.setDob(rs.getDate("dob"));
        customer.setEmailNotifPreference(rs.getInt("email_notif_preference"));
        customer.setTextNotifPreference(rs.getInt("text_notif_preference"));
        customer.setCreatedBy(rs.getInt("created_by"));
        customer.setUpdatedBy(rs.getInt("updated_by"));
        customer.setIsDeleted(rs.getInt("is_deleted"));
        customer.setStatus(rs.getInt("status"));
        customer.setAddressId(rs.getInt("address_id"));
        customer.setCreatedAt(rs.getDate("created_at"));
        customer.setUpdatedAt(rs.getDate("updated_at"));
        return customer;
    }
}
