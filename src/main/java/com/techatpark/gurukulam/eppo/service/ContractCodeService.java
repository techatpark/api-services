
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.ContractCode;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ContractCodeService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for ContractCode de related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public ContractCodeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into ContractCodede table.
     * 
     * @param newContractCode
     * @return reads the input data
     */
    public ContractCode create(final ContractCode newContractCode) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("contract_codes")
                .usingGeneratedKeyColumns("id").usingColumns("contract_code");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("contract_code", newContractCode.getContractCode());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table ContractCode.
     *
     * @param id
     * @return ContractCode
     */
    public Optional<ContractCode> read(final Integer id) {
        final String query = "SELECT contract_code, created_at, updated_at FROM  WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table ContractCode.
     *
     * @param id
     * @param newContractCode de
     * @return ContractCodde
     */
    public ContractCode update(final Integer id, final ContractCode newContractCode) {
        final String query = "UPDATE  SET contract_code = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newContractCode.getContractCode(), id);
        return read(id).get();
    }

    /**
     * Delete all from ContractCodde.
     * 
     * @param id
     * @return ContractCodde
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM  WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from ContractCodde.
     *
     * @return ContractCodde
     */
    public Integer delete() {
        final String query = "DELETE FROM ";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in ContractCode de.
     *
     * @return ContractCodde
     */
    public List<ContractCode> list() {
        final String query = "SELECT id, contract_code, created_at, updated_at FROM ";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return ContractCode
     * @throws SQLException
     */
    private ContractCode mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final ContractCode contractCode = new ContractCode();
        contractCode.setId(rs.getInt("id"));
        contractCode.setContractCode(rs.getString("contract_code"));
        contractCode.setCreatedAt(rs.getDate("created_at"));
        contractCode.setUpdatedAt(rs.getDate("updated_at"));
        return contractCode;
    }
}
