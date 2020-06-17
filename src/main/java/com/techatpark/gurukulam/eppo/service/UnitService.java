
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Unit;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Unit related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UnitService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Unit table.
     * 
     * @param newUnit
     * @return reads the input data
     */
    public Unit create(final Unit newUnit) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("units")
                .usingGeneratedKeyColumns("id").usingColumns("unit_identifier", "unit_name", "building_name",
                        "square_feet", "no_of_bathrooms", "no_of_bedrooms", "other_information", "created_by",
                        "updated_by", "is_deleted", "status", "unit_type_id", "unit_group_id", "address_id",
                        "account_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("unit_identifier", newUnit.getUnitIdentifier());
        valuesMap.put("unit_name", newUnit.getUnitIdentifier());
        valuesMap.put("building_name", newUnit.getUnitIdentifier());
        valuesMap.put("square_feet", newUnit.getUnitIdentifier());
        valuesMap.put("no_of_bathrooms", newUnit.getUnitIdentifier());
        valuesMap.put("no_of_bedrooms", newUnit.getUnitIdentifier());
        valuesMap.put("other_information", newUnit.getUnitIdentifier());
        valuesMap.put("created_by", newUnit.getUnitIdentifier());
        valuesMap.put("updated_by", newUnit.getUnitIdentifier());
        valuesMap.put("is_deleted", newUnit.getUnitIdentifier());
        valuesMap.put("status", newUnit.getUnitIdentifier());
        valuesMap.put("unit_type_id", newUnit.getUnitIdentifier());
        valuesMap.put("unit_group_id", newUnit.getUnitIdentifier());
        valuesMap.put("address_id", newUnit.getUnitIdentifier());
        valuesMap.put("account_id", newUnit.getUnitIdentifier());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Unit.
     *
     * @param id
     * @return Unit
     */
    public Optional<Unit> read(final Integer id) {
        final String query = "SELECT unit_identifier, unit_name, building_name, square_feet, no_of_bathrooms, no_of_bedrooms, other_information, created_by, updated_by, is_deleted, status, unit_type_id, unit_group_id, address_id, account_id, created_at, updated_at FROM units WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Unit.
     *
     * @param id
     * @param newUnit
     * @return Unit
     */
    public Unit update(final Integer id, final Unit newUnit) {
        final String query = "UPDATE units SET unit_identifier = ?, unit_name = ?, building_name = ?, square_feet = ?, no_of_bathrooms = ?, no_of_bedrooms = ?, other_information = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, unit_type_id = ?, unit_group_id = ?, address_id = ?, account_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUnit.getUnitIdentifier(), newUnit.getUnitName(), newUnit.getBuildingName(),
                newUnit.getSquareFeet(), newUnit.getNoOfBathrooms(), newUnit.getNoOfBedrooms(),
                newUnit.getOtherInformation(), newUnit.getCreatedBy(), newUnit.getUpdatedBy(), newUnit.getIsDeleted(),
                newUnit.getStatus(), newUnit.getUnitTypeId(), newUnit.getUnitGroupId(), newUnit.getAddressId(),
                newUnit.getAccountId(), id);
        return read(id).get();
    }

    /**
     * Delete all from Unit.
     * 
     * @param id
     * @return Unit
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM units WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Unit.
     *
     * @return Unit
     */
    public Integer delete() {
        final String query = "DELETE FROM units";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Unit.
     *
     * @return Unit
     */
    public List<Unit> list() {
        final String query = "SELECT id, unit_identifier, unit_name, building_name, square_feet, no_of_bathrooms, no_of_bedrooms, other_information, created_by, updated_by, is_deleted, status, unit_type_id, unit_group_id, address_id, account_id, created_at, updated_at FROM units";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Unit
     * @throws SQLException
     */
    private Unit mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Unit unit = new Unit();
        unit.setId(rs.getInt("id"));
        unit.setUnitIdentifier(rs.getString("unit_identifier"));
        unit.setUnitName(rs.getString("unit_name"));
        unit.setBuildingName(rs.getString("building_name"));
        unit.setSquareFeet(rs.getInt("square_feet"));
        unit.setNoOfBathrooms(rs.getInt("no_of_bathrooms"));
        unit.setNoOfBedrooms(rs.getInt("no_of_bedrooms"));
        unit.setOtherInformation(rs.getString("other_information"));
        unit.setCreatedBy(rs.getInt("created_by"));
        unit.setUpdatedBy(rs.getInt("updated_by"));
        unit.setIsDeleted(rs.getInt("is_deleted"));
        unit.setStatus(rs.getInt("status"));
        unit.setUnitTypeId(rs.getInt("unit_type_id"));
        unit.setUnitGroupId(rs.getInt("unit_group_id"));
        unit.setAddressId(rs.getInt("address_id"));
        unit.setAccountId(rs.getInt("account_id"));
        unit.setCreatedAt(rs.getDate("created_at"));
        unit.setUpdatedAt(rs.getDate("updated_at"));
        return unit;
    }
}
