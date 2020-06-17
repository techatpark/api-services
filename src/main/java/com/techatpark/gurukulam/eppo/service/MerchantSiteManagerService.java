
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.MerchantSiteManager;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class MerchantSiteManagerService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for MerchantSiteManager related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public MerchantSiteManagerService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into MerchantSiteManager table.
     * 
     * @param newMerchantSiteManager
     * @return reads the input data
     */
    public MerchantSiteManager create(final MerchantSiteManager newMerchantSiteManager) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("merchant_site_managers")
                .usingGeneratedKeyColumns("id").usingColumns("first_name", "middle_name", "last_name", "phone_no",
                        "email_id", "created_by", "updated_by", "is_deleted", "status", "account_id", "role_id",
                        "address_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("first_name", newMerchantSiteManager.getFirstName());
        valuesMap.put("middle_name", newMerchantSiteManager.getMiddleName());
        valuesMap.put("last_name", newMerchantSiteManager.getLastName());
        valuesMap.put("phone_no", newMerchantSiteManager.getPhoneNo());
        valuesMap.put("email_id", newMerchantSiteManager.getEmailId());
        valuesMap.put("created_by", newMerchantSiteManager.getCreatedBy());
        valuesMap.put("updated_by", newMerchantSiteManager.getUpdatedBy());
        valuesMap.put("is_deleted", newMerchantSiteManager.getIsDeleted());
        valuesMap.put("status", newMerchantSiteManager.getStatus());
        valuesMap.put("account_id", newMerchantSiteManager.getAccountId());
        valuesMap.put("role_id", newMerchantSiteManager.getRoleId());
        valuesMap.put("address_id", newMerchantSiteManager.getAddressId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table MerchantSiteManager.
     *
     * @param id
     * @return MerchantSiteManager
     */
    public Optional<MerchantSiteManager> read(final Integer id) {
        final String query = "SELECT first_name, middle_name, last_name, phone_no, email_id, created_by, updated_by, is_deleted, status, account_id, role_id, address_id, created_at, updated_at FROM merchant_site_managers WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table MerchantSiteManager.
     *
     * @param id
     * @param newMerchantSiteManager
     * @return MerchantSiteManager
     */
    public MerchantSiteManager update(final Integer id, final MerchantSiteManager newMerchantSiteManager) {
        final String query = "UPDATE merchant_site_managers SET first_name = ?, middle_name = ?, last_name = ?, phone_no = ?, email_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, role_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newMerchantSiteManager.getFirstName(), newMerchantSiteManager.getMiddleName(),
                newMerchantSiteManager.getLastName(), newMerchantSiteManager.getPhoneNo(),
                newMerchantSiteManager.getEmailId(), newMerchantSiteManager.getCreatedBy(),
                newMerchantSiteManager.getUpdatedBy(), newMerchantSiteManager.getIsDeleted(),
                newMerchantSiteManager.getStatus(), newMerchantSiteManager.getAccountId(),
                newMerchantSiteManager.getRoleId(), newMerchantSiteManager.getAddressId(), id);
        return read(id).get();
    }

    /**
     * Delete all from MerchantSiteManager.
     * 
     * @param id
     * @return MerchantSiteManager
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM merchant_site_managers WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from MerchantSiteManager.
     *
     * @return MerchantSiteManager
     */
    public Integer delete() {
        final String query = "DELETE FROM merchant_site_managers";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in MerchantSiteManager.
     *
     * @return MerchantSiteManager
     */
    public List<MerchantSiteManager> list() {
        final String query = "SELECT id, first_name, middle_name, last_name, phone_no, email_id, created_by, updated_by, is_deleted, status, account_id, role_id, created_at, updated_at FROM merchant_site_managers";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return MerchantSiteManager
     * @throws SQLException
     */
    private MerchantSiteManager mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final MerchantSiteManager merchantSiteManager = new MerchantSiteManager();
        merchantSiteManager.setId(rs.getInt("id"));
        merchantSiteManager.setId(rs.getInt("first_name"));
        merchantSiteManager.setId(rs.getInt("middle_name"));
        merchantSiteManager.setId(rs.getInt("last_name"));
        merchantSiteManager.setId(rs.getInt("phone_no"));
        merchantSiteManager.setId(rs.getInt("email_id"));
        merchantSiteManager.setId(rs.getInt("created_by"));
        merchantSiteManager.setId(rs.getInt("updated_by"));
        merchantSiteManager.setId(rs.getInt("is_deleted"));
        merchantSiteManager.setId(rs.getInt("status"));
        merchantSiteManager.setId(rs.getInt("account_id"));
        merchantSiteManager.setId(rs.getInt("role_id"));
        merchantSiteManager.setId(rs.getInt("address_id"));
        merchantSiteManager.setCreatedAt(rs.getDate("created_at"));
        merchantSiteManager.setUpdatedAt(rs.getDate("updated_at"));
        return merchantSiteManager;
    }
}
