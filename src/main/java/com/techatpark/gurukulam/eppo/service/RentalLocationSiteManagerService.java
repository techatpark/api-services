
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.RentalLocationSiteManager;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class RentalLocationSiteManagerService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for RentalLocationSiteManager related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public RentalLocationSiteManagerService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into RentalLocationSiteManager table.
     * 
     * @param newRentalLocationSiteManager
     * @return reads the input data
     */
    public RentalLocationSiteManager create(final RentalLocationSiteManager newRentalLocationSiteManager) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("rental_location_site_managers")
                .usingGeneratedKeyColumns("id").usingColumns("rental_location_id", "merchant_site_manager_id",
                        "created_by", "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("rental_location_id", newRentalLocationSiteManager.getRentalLocationId());
        valuesMap.put("merchant_site_manager_id", newRentalLocationSiteManager.getMerchantSiteManagerId());
        valuesMap.put("created_by", newRentalLocationSiteManager.getCreatedBy());
        valuesMap.put("updated_by", newRentalLocationSiteManager.getUpdatedBy());
        valuesMap.put("is_deleted", newRentalLocationSiteManager.getIsDeleted());
        valuesMap.put("status", newRentalLocationSiteManager.getStatus());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table RentalLocationSiteManager.
     *
     * @param id
     * @return RentalLocationSiteManager
     */
    public Optional<RentalLocationSiteManager> read(final Integer id) {
        final String query = "SELECT rental_location_id, merchant_site_manager_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM rental_location_site_managers WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table RentalLocationSiteManager.
     *
     * @param id
     * @param newRentalLocationSiteManager
     * @return RentalLocationSiteManager
     */
    public RentalLocationSiteManager update(final Integer id,
            final RentalLocationSiteManager newRentalLocationSiteManager) {
        final String query = "UPDATE rental_location_site_managers SET rental_location_id = ?, merchant_site_manager_id = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newRentalLocationSiteManager.getRentalLocationId(),
                newRentalLocationSiteManager.getMerchantSiteManagerId(), newRentalLocationSiteManager.getCreatedBy(),
                newRentalLocationSiteManager.getUpdatedBy(), newRentalLocationSiteManager.getIsDeleted(),
                newRentalLocationSiteManager.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from RentalLocationSiteManager.
     * 
     * @param id
     * @return RentalLocationSiteManager
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM rental_location_site_managers WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from RentalLocationSiteManager.
     *
     * @return RentalLocationSiteManager
     */
    public Integer delete() {
        final String query = "DELETE FROM rental_location_site_managers";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in RentalLocationSiteManager.
     *
     * @return RentalLocationSiteManager
     */
    public List<RentalLocationSiteManager> list() {
        final String query = "SELECT id, rental_location_id, merchant_site_manager_id, created_by, updated_by, is_deleted, status, created_at, updated_at FROM rental_location_site_managers";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return RentalLocationSiteManager
     * @throws SQLException
     */
    private RentalLocationSiteManager mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RentalLocationSiteManager rentalLocationSiteManager = new RentalLocationSiteManager();
        rentalLocationSiteManager.setId(rs.getInt("id"));
        rentalLocationSiteManager.setId(rs.getInt("rental_location_id"));
        rentalLocationSiteManager.setId(rs.getInt("merchant_site_manager_id"));
        rentalLocationSiteManager.setId(rs.getInt("created_by"));
        rentalLocationSiteManager.setId(rs.getInt("updated_by"));
        rentalLocationSiteManager.setId(rs.getInt("is_deleted"));
        rentalLocationSiteManager.setId(rs.getInt("status"));
        rentalLocationSiteManager.setCreatedAt(rs.getDate("created_at"));
        rentalLocationSiteManager.setUpdatedAt(rs.getDate("updated_at"));
        return rentalLocationSiteManager;
    }
}
