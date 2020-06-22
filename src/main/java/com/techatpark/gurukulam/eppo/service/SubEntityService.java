package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.SubEntity;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class SubEntityService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for SubEntity related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public SubEntityService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into SubEntity table.
     * 
     * @param newSubEntity
     * @return reads the input data
     */
    public SubEntity create(final SubEntity newSubEntity) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("sub_entities")
                .usingGeneratedKeyColumns("id").usingColumns("sub_entity_name", "created_by", "updated_by",
                        "is_deleted", "status", "legal_state_code", "is_default", "account_id", "bank_account_id",
                        "created_by", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();

        valuesMap.put("sub_entity_name", newSubEntity.getSubEntityName());
        valuesMap.put("created_by", newSubEntity.getCreatedBy());
        valuesMap.put("updated_by", newSubEntity.getUpdatedBy());
        valuesMap.put("is_deleted", newSubEntity.getIsDeleted());
        valuesMap.put("status", newSubEntity.getStatus());
        valuesMap.put("legal_state_code", newSubEntity.getLegalStateCode());
        valuesMap.put("is_default", newSubEntity.getIsDefault());
        valuesMap.put("account_id", newSubEntity.getAccountId());
        valuesMap.put("bank_account_id", newSubEntity.getBankAccountId());
        valuesMap.put("created_by", newSubEntity.getCreatedBy());
        valuesMap.put("updated_by", newSubEntity.getUpdatedBy());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table SubEntity.
     *
     * @param id
     * @return SubEntity
     */
    public Optional<SubEntity> read(final Integer id) {
        final String query = "SELECT sub_entity_name, created_by, updated_by, is_deleted, status, legal_state_code, is_default, account_id, bank_account_id, created_at, updated_at FROM sub_entities WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table SubEntity.
     *
     * @param id
     * @param newSubEntity
     * @return SubEntity
     */
    public SubEntity update(final Integer id, final SubEntity newSubEntity) {
        final String query = "UPDATE sub_entities SET sub_entity_name = ?, created_by = ?, updated_by = ?, is_deleted = ?, status =?,legal_state_code = ?, is_default = ?, account_id = ?, bank_account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newSubEntity.getSubEntityName(), newSubEntity.getCreatedBy(),
                newSubEntity.getUpdatedBy(), newSubEntity.getIsDeleted(), newSubEntity.getStatus(),
                newSubEntity.getLegalStateCode(), newSubEntity.getIsDefault(), newSubEntity.getAccountId(),
                newSubEntity.getBankAccountId(), newSubEntity.getCreatedAt(), newSubEntity.getUpdatedAt(), id);
        return read(id).get();
    }

    /**
     * Delete all from SubEntity.
     * 
     * @param id
     * @return SubEntity
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM sub_entities WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from SubEntity.
     *
     * @return SubEntity
     */
    public Integer delete() {
        final String query = "DELETE FROM sub_entities";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in SubEntity.
     *
     * @return SubEntity
     */
    public List<SubEntity> list() {
        final String query = "SELECT id, sub_entity_name, created_by, updated_by, is_deleted, status, legal_state_code, is_default, account_id, bank_account_id, created_at, updated_at FROM sub_entities";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return SubEntity
     * @throws SQLException
     */
    private SubEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final SubEntity subEntity = new SubEntity();
        subEntity.setId(rs.getInt("id"));
        subEntity.setSubEntityName(rs.getString("sub_entity_name"));
        subEntity.setCreatedBy(rs.getInt("created_by"));
        subEntity.setUpdatedBy(rs.getInt("updated_by"));
        subEntity.setIsDeleted(rs.getShort("is_deleted"));
        subEntity.setStatus(rs.getShort("status"));
        subEntity.setLegalStateCode(rs.getString("legal_state_code"));
        subEntity.setIsDefault(rs.getString("is_default"));
        subEntity.setAccountId(rs.getInt("account_id"));
        subEntity.setBankAccountId(rs.getInt("bank_account_id"));
        subEntity.setCreatedAt(rs.getDate("created_at"));
        subEntity.setUpdatedAt(rs.getDate("updated_at"));
        return subEntity;
    }
}
