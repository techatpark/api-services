package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.ReportQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class ReportQueryService {
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
    public ReportQueryService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into reportquery table.
     * 
     * @param newReportQuery
     * @return reads the input data
     */
    public ReportQuery create(final ReportQuery newReportQuery) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("reportQuery")
                .usingGeneratedKeyColumns("id").usingColumns("code", "name", "description", "query", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newReportQuery.getCode());
        valuesMap.put("name", newReportQuery.getName());
        valuesMap.put("description", newReportQuery.getDescription());
        valuesMap.put("updated_by", 1);
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from the table reportquery.
     * 
     * @param id
     * @return reportquery
     */
    public Optional<ReportQuery> read(final Integer id) {
        final String query = "SELECT id, code, name, description, query, active_flag, updated_at, updated_by FROM reportquery WHERE id = ? AND active_flag = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * updates a table with id.
     * 
     * @param id
     * @param reportQuery
     * @return updated report query
     */
    public ReportQuery update(final Integer id, final ReportQuery reportQuery) {
        final String query = null;
        return null;
    }

    /**
     * gives the list of data from table.
     * 
     * @param pageNumber
     * @param pageSize
     * @return list of reportquery
     */
    public List<ReportQuery> list(final Integer pageNumber, final Integer pageSize) {
        final String query = null;
        return null;

    }

    /**
     * deletes from table with id.
     * 
     * @param id
     * @return success
     */
    public Boolean delete(final Integer id) {
        final String query = null;
        return false;
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return reportQuery
     * @throws SQLException
     */
    private ReportQuery mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final ReportQuery reportQuery = new ReportQuery();
        reportQuery.setId(rs.getInt("id"));
        reportQuery.setCode(rs.getString("code"));
        reportQuery.setName(rs.getString("name"));
        reportQuery.setDescription(rs.getString("description"));
        reportQuery.setUpdatedBy(rs.getInt("updated_by"));
        reportQuery.setUpdatedAt(rs.getTimestamp("updated_at"));
        return reportQuery;
    }

}
