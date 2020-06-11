
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.UnitRent;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UnitRentService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for UnitRent related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UnitRentService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into UnitRent table.
     * 
     * @param newUnitRent
     * @return reads the input data
     */
    public UnitRent create(final UnitRent newUnitRent) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("unit_rents")
                .usingGeneratedKeyColumns("id").usingColumns("rent_amount", "rent_start_date", "rent_end_date",
                        "created_by", "updated_by", "is_deleted", "unit_id", "rent_type");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("rent_amount", newUnitRent.getRentAmount());
        valuesMap.put("rent_start_date", newUnitRent.getRentStartDate());
        valuesMap.put("rent_end_date", newUnitRent.getRentEndDate());
        valuesMap.put("created_by", newUnitRent.getCreatedBy());
        valuesMap.put("updated_by", newUnitRent.getUpdatedBy());
        valuesMap.put("is_deleted", newUnitRent.getIsDeleted());
        valuesMap.put("unit_id", newUnitRent.getUnitId());
        valuesMap.put("rent_type", newUnitRent.getRentType());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table UnitRent.
     *
     * @param id
     * @return UnitRent
     */
    public Optional<UnitRent> read(final Integer id) {
        final String query = "SELECT rent_amount, rent_start_date, rent_end_date, created_by, updated_by, is_deleted, unit_id, rent_type, created_at, updated_at FROM unit_rents WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table UnitRent.
     *
     * @param id
     * @param newUnitRent
     * @return UnitRent
     */
    public UnitRent update(final Integer id, final UnitRent newUnitRent) {
        final String query = "UPDATE unit_rents SET rent_amount = ?, rent_start_date = ?, rent_end_date = ?, created_by = ?, updated_by = ?, is_deleted = ?, unit_id = ?, rent_type = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUnitRent.getRentAmount(), newUnitRent.getRentStartDate(),
                newUnitRent.getRentEndDate(), newUnitRent.getCreatedBy(), newUnitRent.getUpdatedBy(),
                newUnitRent.getIsDeleted(), newUnitRent.getUnitId(), newUnitRent.getRentType(), id);
        return read(id).get();
    }

    /**
     * Delete all from UnitRent.
     * 
     * @param id
     * @return UnitRent
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM unit_rents WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from UnitRent.
     *
     * @return UnitRent
     */
    public Integer delete() {
        final String query = "DELETE FROM unit_rents";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in UnitRent.
     *
     * @return UnitRent
     */
    public List<UnitRent> list() {
        final String query = "SELECT id, rent_amount, rent_start_date, rent_end_date, created_by, updated_by, is_deleted, unit_id, rent_type, created_at, updated_at FROM unit_rents";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return UnitRent
     * @throws SQLException
     */
    private UnitRent mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final UnitRent unitRent = new UnitRent();
        unitRent.setId(rs.getInt("id"));
        unitRent.setRentAmount(rs.getLong("rent_amount"));
        unitRent.setRentStartDate(rs.getDate("rent_start_date"));
        unitRent.setRentEndDate(rs.getDate("rent_end_date"));
        unitRent.setCreatedBy(rs.getInt("created_by"));
        unitRent.setUpdatedBy(rs.getInt("updated_by"));
        unitRent.setIsDeleted(rs.getInt("is_deleted"));
        unitRent.setUnitId(rs.getShort("unit_id"));
        unitRent.setRentType(rs.getString("rent_type"));
        unitRent.setCreatedAt(rs.getDate("created_at"));
        unitRent.setUpdatedAt(rs.getDate("updated_at"));
        return unitRent;
    }
}
