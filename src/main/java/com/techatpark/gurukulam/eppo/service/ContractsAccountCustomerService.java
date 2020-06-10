
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.ContractsAccountCustomer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ContractsAccountCustomerService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for ContractsAccountCustomer related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public ContractsAccountCustomerService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into ContractsAccountCustomer table.
     * 
     * @param newContractsAccountCustomer
     * @return reads the input data
     */
    public ContractsAccountCustomer create(final ContractsAccountCustomer newContractsAccountCustomer) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("contracts_account_customers")
                .usingGeneratedKeyColumns("id").usingColumns("account_customer_id", "contract_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("account_customer_id", newContractsAccountCustomer.getAccountCustomerId());
        valuesMap.put("contract_id", newContractsAccountCustomer.getContractId());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table ContractsAccountCustomer.
     *
     * @param id
     * @return ContractsAccountCustomer
     */
    public Optional<ContractsAccountCustomer> read(final Integer id) {
        final String query = "SELECT account_customer_id, contract_id, created_at, updated_at FROM contracts_account_customers WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table ContractsAccountCustomer.
     *
     * @param id
     * @param newContractsAccountCustomer
     * @return ContractsAccountCustomer
     */
    public ContractsAccountCustomer update(final Integer id,
            final ContractsAccountCustomer newContractsAccountCustomer) {
        final String query = "UPDATE contracts_account_customers SET account_customer_id = ?, contract_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newContractsAccountCustomer.getAccountCustomerId(),
                newContractsAccountCustomer.getContractId(), id);
        return read(id).get();
    }

    /**
     * Delete all from ContractsAccountCustomer.
     * 
     * @param id
     * @return ContractsAccountCustomer
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM contracts_account_customers WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from ContractsAccountCustomer.
     *
     * @return ContractsAccountCustomer
     */
    public Integer delete() {
        final String query = "DELETE FROM contracts_account_customers";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in ContractsAccountCustomer.
     *
     * @return ContractsAccountCustomer
     */
    public List<ContractsAccountCustomer> list() {
        final String query = "SELECT id, account_customer_id, contract_id, created_at, updated_at FROM contracts_account_customers";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return ContractsAccountCustomer
     * @throws SQLException
     */
    private ContractsAccountCustomer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ContractsAccountCustomer contractsAccountCustomer = new ContractsAccountCustomer();
        contractsAccountCustomer.setId(rs.getInt("id"));
        contractsAccountCustomer.setAccountCustomerId(rs.getInt("account_customer_id"));
        contractsAccountCustomer.setContractId(rs.getInt("contract_id"));
        contractsAccountCustomer.setCreatedAt(rs.getDate("created_at"));
        contractsAccountCustomer.setUpdatedAt(rs.getDate("updated_at"));
        return contractsAccountCustomer;
    }
}
