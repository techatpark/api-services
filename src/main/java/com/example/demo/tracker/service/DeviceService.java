package com.example.demo.tracker.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Device;

import org.springframework.jdbc.core.JdbcTemplate;
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
     * Creates a service for Menu related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public DeviceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into geolocationaddressdetails table.
     * 
     * @param newDevice
     * @return device
     */
    public Device create(final Device newDevice) {
        final String query = null;
        return null;

    }

    /**
     * reading from table with the given id.
     * 
     * @param id
     * @param newDevice
     * @return device
     */
    public Optional<Device> read(final Integer id, final Device newDevice) {
        final String query = null;
        return null;
    }

    /**
     * update table with given id .
     * 
     * @param id
     * @param newDevice
     * @return device
     */
    public Device update(final Integer id, final Device newDevice) {

        final String query = null;
        return null;
    }

    /**
     * delete from table with given id.
     * 
     * @param id
     * @return true if deleted
     */
    public Boolean delete(final Integer id) {
        final String query = null;
        return true;
    }

    /**
     * list from table with givn pagenumber and page size.
     * 
     * @param pageNumber
     * @param pageSize
     * @return device
     */
    public List<Device> list(final Integer pageNumber, final Integer pageSize) {

        final String query = null;
        return null;
    }

    /**
     * map data from and to the database table.
     * 
     * @param newDevice
     * @return device
     */
    public Device map(final Device newDevice) {
        final String query = null;
        return null;
    }

}
