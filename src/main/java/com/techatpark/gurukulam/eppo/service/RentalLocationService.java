package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.RentalLocation;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class RentalLocationService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for RentalLocation related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public RentalLocationService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into RentalLocation table.
     * 
     * @param newRentalLocation
     * @return reads the input data
     */
    public RentalLocation create(final RentalLocation newRentalLocation) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("rental_locations")
                .usingGeneratedKeyColumns("id")
                .usingColumns("rental_location_name", "created_by", "updated_by", "is_deleted", "status", "account_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("rental_location_name", newRentalLocation.getRentalLocationName());
        valuesMap.put("created_by", newRentalLocation.getCreatedBy());
        valuesMap.put("updated_by", newRentalLocation.getUpdatedBy());
        valuesMap.put("is_deleted", newRentalLocation.getIsDeleted());
        valuesMap.put("status", newRentalLocation.getStatus());
        valuesMap.put("account_id", newRentalLocation.getAccountId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table RentalLocation.
     *
     * @param id
     * @return RentalLocation
     */
    public Optional<RentalLocation> read(final Integer id) {
        final String query = "SELECT rental_location_name, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM rental_locations WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table RentalLocation.
     *
     * @param id
     * @param newRentalLocation
     * @return RentalLocation
     */
    public RentalLocation update(final Integer id, final RentalLocation newRentalLocation) {
        final String query = "UPDATE rental_locations SET rental_location_name = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newRentalLocation.getRentalLocationName(), newRentalLocation.getCreatedBy(),
                newRentalLocation.getUpdatedBy(), newRentalLocation.getAccountId(), newRentalLocation.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from RentalLocation.
     * 
     * @param id
     * @return RentalLocation
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM rental_locations WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from RentalLocation.
     *
     * @return RentalLocation
     */
    public Integer delete() {
        final String query = "DELETE FROM rental_locations";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in RentalLocation.
     *
     * @return RentalLocation
     */
    public List<RentalLocation> list() {
        final String query = "SELECT id, rental_location_name, created_by, updated_by, is_deleted, status, account_id, created_at, updated_at FROM rental_locations";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return RentalLocation
     * @throws SQLException
     */
    private RentalLocation mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RentalLocation rentalLocation = new RentalLocation();
        rentalLocation.setId(rs.getInt("id"));
        rentalLocation.setRentalLocationName(rs.getString("rental_location_name"));
        rentalLocation.setCreatedBy(rs.getInt("created_by"));
        rentalLocation.setUpdatedBy(rs.getInt("updated_by"));
        rentalLocation.setIsDeleted(rs.getInt("is_deleted"));
        rentalLocation.setStatus(rs.getInt("status"));
        rentalLocation.setAccountId(rs.getInt("account_id"));
        rentalLocation.setCreatedAt(rs.getDate("created_at"));
        rentalLocation.setUpdatedAt(rs.getDate("updated_at"));
        return rentalLocation;
    }
}
