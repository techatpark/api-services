package com.example.demo.tracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

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
     * Maps the data from and to the database.
     * 
    */

    private final RowMapper<Namespace> rowMapper = (rs, rowNum) -> {
        final Namespace namespace = new Namespace();
        namespace.setId(rs.getInt("id"));
        namespace.setCode(rs.getString("code"));
        namespace.setName(rs.getString("name"));
        namespace.setStatus(Status.of(rs.getInt("active_flag")));
        namespace.setUpdatedBy(rs.getInt("updated_by"));
        namespace.setUpdatedAt(rs.getDate("updated_at"));
        return namespace;
    }

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
     * @param namespace
     * @return reads the input data
     */
    public Namespace create(final Namespace namespace) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("namespace")
                .usingGeneratedKeyColumns("id").usingColumns("code", "name", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", namespace.getCode());
        valuesMap.put("name", namespace.getName());
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
        final String query = "SELECT id,code,name,active_flag,updated_by,updated_at FROM namespace WHERE id = ? AND active_flag = 1";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, rowMapper));
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
        final String query = "UPDATE namespace SET code = ?,name = ?,active_flag = ?,updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ? ";
        Integer updatedRows = jdbcTemplate.update(query, namespaceToBeUpdated.getCode(), namespaceToBeUpdated.getName(),
                namespaceToBeUpdated.getStatus().getValue(), id);
        return updatedRows == 0 ? null : read(id).get();
    }

    /**
     * Soft Delete a row with given id.
     * 
     * @param id
     * @return successflag.
     */
    public Boolean delete(final Integer id) {
        return delete(id, false);
    }

    /**
     * Soft Delete a row with given id.
     * 
     * @param id
     * @param isHardDelete
     * @return successflag.
     */
    public Boolean delete(final Integer id, final Boolean isHardDelete) {
        final String query = isHardDelete ? "DELETE FROM namespace WHERE id = ?"
                : "UPDATE namespace SET active_flag = 0 WHERE id = ?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        return !(updatedRows == 0);
    }

    /**
     * Soft Delete a row with given code.
     * 
     * @param code
     * @return successflag.
     */
    public Boolean delete(final String code) {
        return delete(code, false);
    }

    /**
     * Soft Delete a row with given code.
     * 
     * @param code
     * @param isHardDelete
     * @return successflag.
     */
    public Boolean delete(final String code, final Boolean isHardDelete) {
        final String query = isHardDelete ? "DELETE FROM namespace WHERE code = ?"
                : "UPDATE namespace SET active_flag = 0 WHERE code = ?";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { code });
        return !(updatedRows == 0);
    }

    /**
     * soft delete all from namespace.
     * 
     * @return namespace
     */
    public Integer delete() {
        return delete(false);
    }

    /**
     * Delete all from namespace.
     * 
     * @param isHardDelete should hard delete or soft delete by updating active_flag
     *                     is 0
     * @return namespace
     */
    public Integer delete(final Boolean isHardDelete) {
        final String query = isHardDelete ? "DELETE FROM namespace" : "UPDATE namespace SET active_flag = 0";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in namespace.
     * 
     * @param pageNumber
     * @param pageSize
     * @return namespace
     */
    public List<Namespace> list(final Integer pageNumber, final Integer pageSize) {
        String query = "SELECT id,code,name,active_flag,updated_by,updated_at FROM namespace";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, rowMapper);
    }

}
