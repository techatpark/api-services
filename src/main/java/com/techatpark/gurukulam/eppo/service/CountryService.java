
package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Country;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Country related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public CountryService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Country table.
     * 
     * @param newCountry
     * @return reads the input data
     */
    public Country create(final Country newCountry) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("countries")
                .usingGeneratedKeyColumns("id")
                .usingColumns("country_name", "two_digit_code", "three_digit_code", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("country_name", newCountry.getCountryName());
        valuesMap.put("two_digit_code", newCountry.getCountryName());
        valuesMap.put("three_digit_code", newCountry.getCountryName());
        valuesMap.put("status", newCountry.getCountryName());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Country.
     *
     * @param id
     * @return Country
     */
    public Optional<Country> read(final Integer id) {
        final String query = "SELECT country_name, two_digit_code, three_digit_code, status, created_at, updated_at FROM countries WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Country.
     *
     * @param id
     * @param newCountry
     * @return Country
     */
    public Country update(final Integer id, final Country newCountry) {
        final String query = "UPDATE countries SET country_name = ?, two_digit_code = ?, three_digit_code = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newCountry.getCountryName(), newCountry.getTwoDigitCode(),
                newCountry.getThreeDigitCode(), newCountry.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Country.
     * 
     * @param id
     * @return Country
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM countries WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Country.
     *
     * @return Country
     */
    public Integer delete() {
        final String query = "DELETE FROM countries";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Country.
     *
     * @return Country
     */
    public List<Country> list() {
        final String query = "SELECT id, country_name, two_digit_code, three_digit_code, status , created_at, updated_at FROM countries";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Country
     * @throws SQLException
     */
    private Country mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Country country = new Country();
        country.setId(rs.getInt("id"));
        country.setCountryName(rs.getString("country_name"));
        country.setTwoDigitCode(rs.getString("two_digit_code"));
        country.setThreeDigitCode(rs.getString("three_digit_code"));
        country.setStatus(rs.getShort("status"));
        country.setCreatedAt(rs.getDate("created_at"));
        country.setUpdatedAt(rs.getDate("updated_at"));
        return country;
    }
}
