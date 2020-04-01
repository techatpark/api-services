package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.GeoLocationAddressDetails;
import com.example.demo.tracker.model.Status;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("geo_location_address_details")
                .usingGeneratedKeyColumns("id").usingColumns("location_address_code", "road", "area", "landmark",
                        "city", "state", "postal_code", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("location_address_code", newGeoLocationDetails.getLocationAddressCode());
        valuesMap.put("road", newGeoLocationDetails.getRoad());
        valuesMap.put("area", newGeoLocationDetails.getArea());
        valuesMap.put("landmark", newGeoLocationDetails.getLandmark());
        valuesMap.put("city", newGeoLocationDetails.getCity());
        valuesMap.put("state", newGeoLocationDetails.getState());
        valuesMap.put("postal_code", newGeoLocationDetails.getPostalCode());
        valuesMap.put("updated_by", 1);
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();

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
        final String query = "SELECT id, location_address_code, road, area, landmark, city, state, postal_code, active_flag, updated_by, updated_at FROM geo_location_address_details WHERE id = ? AND active_flag = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table with given id .
     * 
     * @param id
     * @param newGeoLocationDetails
     * @return GeoLocationAddressDetails
     */
    public GeoLocationAddressDetails update(final Integer id, final GeoLocationAddressDetails newGeoLocationDetails) {

        final String query = "UPDATE geo_location_address_details SET location_address_code = ?, road = ?, area = ?, landmark = ?, city = ?, state = ?, postal_code = ?, updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ? AND activeFlag = ? AND updatedAt = ?";
        Integer updatedRows = jdbcTemplate.update(query, newGeoLocationDetails.getLocationAddressCode(),
                newGeoLocationDetails.getRoad(), newGeoLocationDetails.getArea(), newGeoLocationDetails.getLandmark(),
                newGeoLocationDetails.getCity(), newGeoLocationDetails.getState(),
                newGeoLocationDetails.getPostalCode(), newGeoLocationDetails.getUpdatedBy(),
                newGeoLocationDetails.getUpdatedAt());
        return updatedRows == 0 ? null : read(id).get();
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
        String query = "SELECT id, location_address_code, road, area, landmark, city, state, postal_code, active_flag, updated_by, updated_at FROM geo_location_address_details";
        query = query + "LIMIT" + pageSize + "OFFSET" + (pageNumber - 1);
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return geoLocationAddressDetails
     * @throws SQLException
     */
    private GeoLocationAddressDetails mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final GeoLocationAddressDetails geoLocationAddressDetails = new GeoLocationAddressDetails();
        geoLocationAddressDetails.setId(rs.getInt("id"));
        geoLocationAddressDetails.setLocationAddressCode(rs.getString("location_address_code"));
        geoLocationAddressDetails.setRoad(rs.getString("road"));
        geoLocationAddressDetails.setArea(rs.getString("area"));
        geoLocationAddressDetails.setLandmark(rs.getString("landmark"));
        geoLocationAddressDetails.setCity(rs.getString("city"));
        geoLocationAddressDetails.setState(rs.getString("state"));
        geoLocationAddressDetails.setPostalCode(rs.getString("postal_code"));
        geoLocationAddressDetails.setStatus(Status.of(rs.getInt("active_flag")));
        geoLocationAddressDetails.setUpdatedBy(rs.getInt("updated_by"));
        geoLocationAddressDetails.setUpdatedAt(rs.getTimestamp("updated_at"));
        return geoLocationAddressDetails;
    }

}
