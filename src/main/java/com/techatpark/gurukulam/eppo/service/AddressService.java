package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Address;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Address related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AddressService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Address table.
     * 
     * @param newAddress
     * @return reads the input data
     */
    public Address create(final Address newAddress) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("addresses")
                .usingGeneratedKeyColumns("id").usingColumns("address_1", "address_2", "city", "zip_code", "state_id",
                        "country_id", "latitude", "longitude");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("address_1", newAddress.getAddress1());
        valuesMap.put("address_2", newAddress.getAddress1());
        valuesMap.put("city", newAddress.getAddress1());
        valuesMap.put("zip_code", newAddress.getAddress1());
        valuesMap.put("state_id", newAddress.getAddress1());
        valuesMap.put("country_id", newAddress.getAddress1());
        valuesMap.put("latitude", newAddress.getAddress1());
        valuesMap.put("longitude", newAddress.getAddress1());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Address.
     *
     * @param id
     * @return Address
     */
    public Optional<Address> read(final Integer id) {
        final String query = "SELECT id, address_1, address_2, city, zip_code, state_id, country_id, latitude, longitude, created_at, updated_at FROM addresses WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Address.
     *
     * @param id
     * @param newAddress
     * @return Address
     */
    public Address update(final Integer id, final Address newAddress) {
        final String query = "UPDATE addresses SET address_1 = ?, address_2 = ?, city = ?, zip_code = ?, state_id = ?, country_id = ?, latitude = ?, longitude = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAddress.getAddress1(), newAddress.getAddress2(), newAddress.getCity(),
                newAddress.getZipCode(), newAddress.getStateId(), newAddress.getCountryId(), newAddress.getLatitude(),
                newAddress.getLongitude(), id);
        return read(id).get();
    }

    /**
     * Delete all from Address.
     * 
     * @param id
     * @return Address
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM addresses WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Address.
     *
     * @return Address
     */
    public Integer delete() {
        final String query = "DELETE FROM addresses";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Address.
     *
     * @return Address
     */
    public List<Address> list() {
        final String query = "SELECT id, address_1, address_2, city, zip_code, state_id, country_id, latitude, longitude, created_at, updated_at FROM addresses";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Address
     * @throws SQLException
     */
    private Address mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setAddress1(rs.getString("address_1"));
        address.setAddress2(rs.getString("address_2"));
        address.setCity(rs.getString("city"));
        address.setZipCode(rs.getString("zip_code"));
        address.setId(rs.getInt("state_id"));
        address.setId(rs.getInt("country_id"));
        address.setLatitude(rs.getInt("latitude"));
        address.setLongitude(rs.getInt("longitude"));
        address.setCreatedAt(rs.getDate("created_at"));
        address.setUpdatedAt(rs.getDate("updated_at"));
        return address;
    }
}
