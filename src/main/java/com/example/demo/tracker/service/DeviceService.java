package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Device;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    public DeviceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Device create(Device newDevice) {
        /**
         * INSERT INTO device ( code , namespace_id , gsm_code , device_imei_code,
         * device_model_id , sensor , api_flag , remarks , active_flag , updated_by ,
         * updated_at datetime NOT NULL) VALUES
         * (newDevice.getCode(),newDevice.namespace_id(),newDevice.gsm_code(),newDevice.device_imei_code(),newDevice.device_model_id(),newDevice.sensor(),newDevice.api_flag(),newDevice.remarks(),
         * newDevice.active_flag(),newDevice.updated_by())
         * 
         */
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
        .withTableName("tableName")
        .usingGeneratedKeyColumns("id");
        Map<String,Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newDevice.getCode());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue());
    }

    public Device read(Integer id) {
        final String query = "SELECT * FROM devices WHERE id = ?";
        return jdbcTemplate.queryForObject(query,new Object[]{id},this::mapRow);
    }

    public Device mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Device device = new Device();
        device.setId(rs.getInt("id"));
        device.setCode(rs.getString("code"));
        return device;
    }

}