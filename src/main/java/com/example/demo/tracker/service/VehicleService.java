package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Status;
import com.example.demo.tracker.model.Vehicle;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    /** * this is used to execute a connection with a database. */
    private final JdbcTemplate jdbcTemplate;
    /** * this is used to connect to relational database. */
    private final DataSource dataSource;

    /**
     * * Creates a device service for device related operations. * *
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public VehicleService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into happens here.
     * 
     * @param newVehicle
     * @return reads the inputed data
     */
    public Vehicle create(final Vehicle newVehicle) {
        // INSERT INTO('ALL COLUMN')
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("vehicle")
                .usingGeneratedKeyColumns("id").usingColumns("code", "namespace_id", "device_id", "register_number",
                        "mobile_number", "overspeed_limit", "vehicle_type_id", "active_flag", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newVehicle.getCode());
        valuesMap.put("namespace_id", newVehicle.getName());
        valuesMap.put("device_id", newVehicle.getDevice());
        valuesMap.put("register_number", newVehicle.getRegisterNumber());
        valuesMap.put("mobile_number", newVehicle.getMobileNumber());
        valuesMap.put("overspeed_limit", newVehicle.getOverspeedLimit());
        valuesMap.put("vehicle_type_id", newVehicle.getVehicleType());
        valuesMap.put("active_flag", newVehicle.getStatus().getValue());
        valuesMap.put("updated_by", 1);
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue());
    }

    /**
     * reads a row of data from database with given id.
     * 
     * @param id
     * @return Vehicle
     */
    public Vehicle read(final Integer id) {
        final String query = "SELECT * FROM vehicle WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update a table in database.
     * 
     * @param id
     * @param vehicleToBeUpdated
     * @return Vehicle
     */
    public Vehicle update(final Integer id, final Vehicle vehicleToBeUpdated) {
        final String query = "UPDATE vehicle SET code = ?,namespace_id = ?,device_id = ?, register_number = ?,mobile_number = ?,overspeed_limit = ?,vehicle_type_id = ?,active_flag = ?,updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, vehicleToBeUpdated.getCode(), vehicleToBeUpdated.getName(),
                vehicleToBeUpdated.getDevice(), vehicleToBeUpdated.getRegisterNumber(),
                vehicleToBeUpdated.getMobileNumber(), vehicleToBeUpdated.getOverspeedLimit(),
                vehicleToBeUpdated.getVehicleType(), vehicleToBeUpdated.getStatus().getValue(), id);
        return read(id);
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return vehicle
     * @throws SQLException
     */
    private Vehicle mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getInt("id"));
        vehicle.setCode(rs.getString("code"));
        vehicle.setName(rs.getString("namespace_id"));
        vehicle.setDevice(rs.getInt("device_id"));
        vehicle.setRegisterNumber(rs.getString("register_number"));
        vehicle.setMobileNumber(rs.getString("mobile_number"));
        vehicle.setOverspeedLimit(rs.getInt("overspeed_limit"));
        vehicle.setVehicleType(rs.getInt("vehicle_type_id"));
        vehicle.setActiveFlag(Status.of(rs.getInt("active_flag")));
        vehicle.setUpdatedBy(rs.getInt("updated_by"));
        vehicle.setUpdatedAt(rs.getDate("updated_at"));
        return vehicle;
    }

    /**
     * Delete a row with given id.
     * 
     * @param id
     * @return vehicle
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM vehicle WHERE id = ?";
        return jdbcTemplate.update(query, new Object[] { id });
    }

    /**
     * Delete all from device.
     * 
     * @return vehicle
     */
    public Integer delete() {
        final String query = "DELETE FROM vehicle";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in device.
     * 
     * @return device
     */
    public List<Vehicle> list() {
        final String query = "SELECT * FROM vehicle";
        final List<Vehicle> devices = jdbcTemplate.query(query, this::mapRow);
        return vehicle;
    }

}
