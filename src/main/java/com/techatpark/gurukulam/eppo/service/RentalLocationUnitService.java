
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.RentalLocationUnit;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class RentalLocationUnitService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for RentalLocationUnit related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public RentalLocationUnitService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into RentalLocationUnit table.
     * 
     * @param newRentalLocationUnit
     * @return reads the input data
     */
    public RentalLocationUnit create(final RentalLocationUnit newRentalLocationUnit) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("rental_location_units")
                .usingGeneratedKeyColumns("id").usingColumns("delete", "created_by", "updated_by", "is_deleted",
                        "status", "rental_location_id", "unit_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("delete", newRentalLocationUnit.getDelete());
        valuesMap.put("created_by", newRentalLocationUnit.getCreatedBy());
        valuesMap.put("updated_by", newRentalLocationUnit.getUpdatedBy());
        valuesMap.put("is_deleted", newRentalLocationUnit.getIsDeleted());
        valuesMap.put("status", newRentalLocationUnit.getStatus());
        valuesMap.put("rental_location_id", newRentalLocationUnit.getRentalLocationId());
        valuesMap.put("unit_id", newRentalLocationUnit.getUnitId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table RentalLocationUnit.
     *
     * @param id
     * @return RentalLocationUnit
     */
    public Optional<RentalLocationUnit> read(final Integer id) {
        final String query = "SELECT delete, created_by, updated_by, is_deleted, status, rental_location_id, unit_id, created_at, updated_at FROM rental_locations WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table RentalLocationUnit.
     *
     * @param id
     * @param newRentalLocationUnit
     * @return RentalLocationUnit
     */
    public RentalLocationUnit update(final Integer id, final RentalLocationUnit newRentalLocationUnit) {
        final String query = "UPDATE rental_locations SET delete = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, rental_location_id = ?, unit_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newRentalLocationUnit.getDelete(), newRentalLocationUnit.getCreatedBy(),
                newRentalLocationUnit.getUpdatedBy(), newRentalLocationUnit.getIsDeleted(),
                newRentalLocationUnit.getStatus(), newRentalLocationUnit.getRentalLocationId(),
                newRentalLocationUnit.getUnitId(), id);
        return read(id).get();
    }

    /**
     * Delete all from RentalLocationUnit.
     * 
     * @param id
     * @return RentalLocationUnit
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM rental_locations WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from RentalLocationUnit.
     *
     * @return RentalLocationUnit
     */
    public Integer delete() {
        final String query = "DELETE FROM rental_locations";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in RentalLocationUnit.
     *
     * @return RentalLocationUnit
     */
    public List<RentalLocationUnit> list() {
        final String query = "SELECT id, delete, created_by, updated_by, is_deleted, status, rental_location_id, unit_id, created_at, updated_at FROM rental_locations";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return RentalLocationUnit
     * @throws SQLException
     */
    private RentalLocationUnit mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final RentalLocationUnit rentalLocationUnit = new RentalLocationUnit();
        rentalLocationUnit.setId(rs.getInt("id"));
        rentalLocationUnit.setDelete(rs.getString("delete"));
        rentalLocationUnit.setCreatedBy(rs.getInt("created_by"));
        rentalLocationUnit.setUpdatedBy(rs.getInt("updated_by"));
        rentalLocationUnit.setIsDeleted(rs.getInt("is_deleted"));
        rentalLocationUnit.setStatus(rs.getInt("status"));
        rentalLocationUnit.setRentalLocationId(rs.getInt("rental_location_id"));
        rentalLocationUnit.setUnitId(rs.getInt("unit_id"));
        rentalLocationUnit.setCreatedAt(rs.getDate("created_at"));
        rentalLocationUnit.setUpdatedAt(rs.getDate("updated_at"));
        return rentalLocationUnit;
    }
}
