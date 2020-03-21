package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Device;
import com.example.demo.tracker.model.Status;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a device service for device related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public DeviceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * INSERT INTO device ( code , namespace_id , gsm_code , device_imei_code,
     * device_model_id , sensor , api_flag , remarks , active_flag , updated_by ,
     * updated_at datetime NOT NULL) VALUES
     * (newDevice.getCode(),newDevice.namespace_id(),newDevice.gsm_code(),newDevice.device_imei_code(),newDevice.device_model_id(),newDevice.sensor(),newDevice.api_flag(),newDevice.remarks(),
     * newDevice.active_flag(),newDevice.updated_by()).
     * 
     * @param newDevice
     * @return reads the inputed data.
     */
    public Device create(final Device newDevice) {
        // INSERT INTO('ALL COLUMN')
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("devices")
                .usingGeneratedKeyColumns("id").usingColumns("code", "namespace_id", "gsm_code", "device_imei_code",
                        "sensor", "api_flag", "remarks", "active_flag", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newDevice.getCode());
        valuesMap.put("namespace_id", newDevice.getName());
        valuesMap.put("gsm_code", newDevice.getGsmCode());
        valuesMap.put("device_imei_code", newDevice.getDeviceIMEICode());
        valuesMap.put("sensor", newDevice.getSensor());
        valuesMap.put("api_flag", newDevice.getApiFlag());
        valuesMap.put("remarks", newDevice.getRemarks());
        valuesMap.put("active_flag", newDevice.getStatus().getValue());
        valuesMap.put("updated_by", 1);
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue());
    }

    /**
     * reads a row of data from database with given id.
     * 
     * @param id
     * @return device
     */
    public Device read(final Integer id) {
        final String query = "SELECT * FROM devices WHERE id = ?";
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
     * @param deviceToBeUpdated
     * @return device
     */
    public Device update(final Integer id, final Device deviceToBeUpdated) {
        final String query = "UPDATE devices SET code = ?,namespace_id = ?,gsm_code = ?, device_imei_code = ?,sensor = ?,api_flag = ?,remarks = ?,active_flag = ?,updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, deviceToBeUpdated.getCode(), deviceToBeUpdated.getName(),
                deviceToBeUpdated.getGsmCode(), deviceToBeUpdated.getDeviceIMEICode(), deviceToBeUpdated.getSensor(),
                deviceToBeUpdated.getApiFlag(), deviceToBeUpdated.getRemarks(),
                deviceToBeUpdated.getStatus().getValue(), id);
        return read(id);
    }

    /**
     * Delete a row with given id.
     * 
     * @param id
     * @return device.
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM devices WHERE id = ?";
        return jdbcTemplate.update(query, new Object[] { id });
    }

    /**
     * Delete all from device.
     * 
     * @return device
     */
    public Integer delete() {
        final String query = "DELETE FROM devices";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in device.
     * 
     * @return device
     */
    public List<Device> list() {
        final String query = "SELECT * FROM devices";
        final List<Device> devices = jdbcTemplate.query(query, this::mapRow);
        return devices;
    }

    /**
     * Find a devie with it IMEI code.
     * 
     * @param deviceIMEICode
     * @return device matching the IMEI code
     */
    public Device findByDeviceIMEICode(final String deviceIMEICode) {
        final String query = "SELECT * FROM devices WHERE device_imei_code = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[] { deviceIMEICode }, this::mapRow);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return device
     * @throws SQLException
     */
    private Device mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Device device = new Device();
        device.setId(rs.getInt("id"));
        device.setCode(rs.getString("code"));
        device.setName(rs.getString("namespace_id"));
        device.setGsmCode(rs.getString("gsm_code"));
        device.setDeviceIMEICode(rs.getString("device_imei_code"));
        device.setSensor(rs.getString("sensor"));
        device.setApiFlag(rs.getInt("api_flag"));
        device.setRemarks(rs.getString("remarks"));
        device.setStatus(Status.of(rs.getInt("active_flag")));
        device.setUpdatedBy(rs.getInt("updated_by"));
        device.setUpdatedAt(rs.getDate("updated_at"));
        return device;
    }

}
