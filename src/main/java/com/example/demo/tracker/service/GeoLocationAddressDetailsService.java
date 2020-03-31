package com.example.demo.tracker.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.GeoLocationAddressDetails;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationAddressDetailsService {

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
    public GeoLocationAddressDetailsService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into geolocationaddressdetails table.
     * 
     * @param newGeoLocationDetails
     * @return geolocationaddressdetails
     */
    public GeoLocationAddressDetails create(final GeoLocationAddressDetails newGeoLocationDetails) {
        final String query = null;
        return null;

    }

    /**
     * reading from table with the given id.
     * 
     * @param id
     * @param newGeoLocationDetails
     * @return GeoLocationAddressDetails
     */
    public Optional<GeoLocationAddressDetails> read(final Integer id,
            final GeoLocationAddressDetails newGeoLocationDetails) {
        final String query = null;
        return null;
    }

    /**
     * update table with given id .
     * 
     * @param id
     * @param newGeoLocationDetails
     * @return newGeoLocationDetails
     */
    public GeoLocationAddressDetails update(final Integer id, final GeoLocationAddressDetails newGeoLocationDetails) {

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
     * @return GeoLocationAddressDetails
     */
    public List<GeoLocationAddressDetails> list(final Integer pageNumber, final Integer pageSize) {

        final String query = null;
        return null;
    }

    /**
     * map data from and to the database table.
     * 
     * @param newGeoLocationDetails
     * @return GeoLocationAddressDetails
     */
    public GeoLocationAddressDetails map(final GeoLocationAddressDetails newGeoLocationDetails) {
        final String query = null;
        return null;
    }

}
