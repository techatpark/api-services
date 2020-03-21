package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Namespace;
import com.example.demo.tracker.model.Status;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class NamespaceService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for namespace related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public NamespaceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * insert into query occurs.
     * 
     * @param newNamespace
     * @return reads the inputted data
     */
    public Namespace create(final Namespace newNamespace) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("namespace")
                .usingGeneratedKeyColumns("id").usingColumns("code", "name", "active_flag", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();

        valuesMap.put("code", newNamespace.getCode());
        valuesMap.put("name", newNamespace.getName());
        valuesMap.put("active_flag", newNamespace.getStatus().getValue());
        valuesMap.put("updated_by", 1);
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue());
    }

    /**
     * reads a row of data from database with given id.
     * 
     * @param id
     * @return namespace
     */
    public Namespace read(final Integer id) {
        final String query = "SELECT * FROM namespace WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * update a table namespace.
     * 
     * @param id
     * @param namespaceToBeUpdated
     * @return namespace
     */
    public Namespace update(final Integer id, final Namespace namespaceToBeUpdated) {
        final String query = "UPDATE namespace SET code = ?,name = ?,active_flag = ?,updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, namespaceToBeUpdated.getCode(), namespaceToBeUpdated.getName(),
                namespaceToBeUpdated.getStatus().getValue(), id);
        return read(id);
    }

    /**
     * Delete a row with given id.
     * 
     * @param id
     * @return namespace.
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM namespace WHERE id = ?";
        return jdbcTemplate.update(query, new Object[] { id });
    }

    /**
     * Delete all from device.
     * 
     * @return namespace
     */
    public Integer delete() {
        final String query = "DELETE FROM namespace";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in namespace.
     * 
     * @return namespace
     */
    public List<Namespace> list() {
        final String query = "SELECT * FROM namespace";
        final List<Namespace> namespace = jdbcTemplate.query(query, this::mapRow);
        return namespace;
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return namespace
     * @throws SQLException
     */
    private Namespace mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Namespace namespace = new Namespace();
        namespace.setId(rs.getInt("id"));
        namespace.setCode(rs.getString("code"));
        namespace.setName(rs.getString("name"));
        namespace.setStatus(Status.of(rs.getInt("active_flag")));
        namespace.setUpdatedBy(rs.getInt("updated_by"));
        namespace.setUpdatedAt(rs.getDate("updated_at"));
        return namespace;
    }

}
