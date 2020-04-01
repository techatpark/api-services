package com.example.demo.tracker.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.GeoLocationAddress;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationAddressService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AlertNotification related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public GeoLocationAddressService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into GeoLocationAddress table.
     * 
     * @param newGeoLocationAddress
     * @return geoLocationAddress
     */
    public GeoLocationAddress create(final GeoLocationAddress newGeoLocationAddress) {
        final String query = null;
        return null;
    }

    /**
     * reading from table with the given id.
     * 
     * @param id
     * @param newGeoLocationAddress
     * @return geoLocationAddress
     */
    public Optional<GeoLocationAddress> read(final Integer id, final GeoLocationAddress newGeoLocationAddress) {
        final String query = null;
        return null;
    }

    /**
     * update table with given id .
     * 
     * @param id
     * @param newGeoLocationAddress
     * @return geoLocationAddress
     */
    public GeoLocationAddress update(final Integer id, final GeoLocationAddress newGeoLocationAddress) {
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
        return null;
    }

    /**
     * list from table with givn pagenumber and page size.
     * 
     * @param pageNumber
     * @param pageSize
     * @return geoLocationAddress
     */
    public List<GeoLocationAddress> list(final Integer pageNumber, final Integer pageSize) {
        final String query = null;
        return null;
    }

    /**
     * map data from and to the database table.
     * 
     * @param newGeoLocationAddress
     * @return geoLocationAddress
     */
    public GeoLocationAddress map(final GeoLocationAddress newGeoLocationAddress) {
        final String query = null;
        return null;
    }
}
