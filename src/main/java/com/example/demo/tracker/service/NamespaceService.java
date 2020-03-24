package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     * * this is used to execute a connection with a database.
     * 
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     * 
     */
    private final DataSource dataSource;

    /**
     * * Creates a device service for device related operations. *
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public NamespaceService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into namespace table.
     * 
     * @param newNamespace
     * @return reads the input data
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
        return read(id.intValue()).get();
    }

    /**
     * reads from table namespace.
     * 
     * @param id
     * @return namespace
     */
    public Optional<Namespace> read(final Integer id) {
        final String query = "SELECT id,code,name,active_flag,updated_by,updated_at FROM namespace WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table namespace.
     * 
     * @param id
     * @param namespaceToBeUpdated
     * @return namespace
     */
    public Namespace update(final Integer id, final Namespace namespaceToBeUpdated) {
        final String query = "UPDATE namespace SET code = ?,name = ?,active_flag = ?,updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, namespaceToBeUpdated.getCode(), namespaceToBeUpdated.getName(),
                namespaceToBeUpdated.getStatus().getValue(), id);
        return read(id).get();
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
     * Delete all from namespace.
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
        final String query = "SELECT id,code,name,active_flag,updated_by,updated_at FROM namespace";
        return jdbcTemplate.query(query, this::mapRow);
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
