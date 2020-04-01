package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.AuditLog;
import com.example.demo.tracker.model.Status;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
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
    public AuditLogService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into audit log table.
     * 
     * @param newAuditLog
     * @return audit log
     */
    public AuditLog create(final AuditLog newAuditLog) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("audit_log")
                .usingGeneratedKeyColumns("id")
                .usingColumns("code", "namespace_id", "table_name", "event", "log1", "log2", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newAuditLog.getCode());
        valuesMap.put("namespace_id", newAuditLog.getNamespaceId());
        valuesMap.put("table_name", newAuditLog.getTableName());
        valuesMap.put("event", newAuditLog.getEvent());
        valuesMap.put("log1", newAuditLog.getLog1());
        valuesMap.put("log2", newAuditLog.getLog2());
        valuesMap.put("updated_by", 1);
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from the table audit log with the given id.
     * 
     * @param id
     * @return audit log
     */
    public Optional<AuditLog> read(final Integer id) {
        final String query = "SELECT id, code, namespace_id, table_name, event, log1, log2, active_flag, updated_by, updated_at FROM audit_log WHERE id = ? AND active_flag = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update the audit log table.
     * 
     * @param id
     * @param newAuditLog
     * @return audit log table
     */
    public AuditLog update(final Integer id, final AuditLog newAuditLog) {
        final String query = "UPDATE audit_log SET code = ?, namespace_id = ?, table_name = ?, event = ?, log1 = ?, log2 = ?, updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ? AND activeFlag = ? AND updatedAt = ?";
        Integer updatedRows = jdbcTemplate.update(query, newAuditLog.getCode(), newAuditLog.getNamespaceId(),
                newAuditLog.getTableName(), newAuditLog.getEvent(), newAuditLog.getLog1(), newAuditLog.getLog2(),
                newAuditLog.getUpdatedBy(), newAuditLog.getUpdatedAt());
        return updatedRows == 0 ? null : read(id).get();
    }

    /**
     * deletes a data from audit log table with given id.
     * 
     * @param id
     * @return true if deleted
     */
    public Boolean delete(final Integer id) {
        final String query = null;
        return true;
    }

    /**
     * lists the data from table with given page number and size of page.
     * 
     * @param pageNumber
     * @param pageSize
     * @return audit log
     */
    public List<AuditLog> list(final Integer pageNumber, final Integer pageSize) {

        String query = "SELECT id, code, namespace_id, table_name, event, log1, log2, activeFlag, updatedBy, updatedAt FROM audit_log";
        query = query + "LIMIT" + pageSize + "OFFSET" + (pageNumber - 1);
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return auditlog
     * @throws SQLException
     */
    private AuditLog mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final AuditLog auditLog = new AuditLog();
        auditLog.setId(rs.getInt("id"));
        auditLog.setCode(rs.getString("code"));
        auditLog.setNamespaceId(rs.getString("namespace_id"));
        auditLog.setTableName(rs.getString("table_name"));
        auditLog.setEvent(rs.getString("event"));
        auditLog.setLog1(rs.getString("log1"));
        auditLog.setLog2(rs.getString("log2"));
        auditLog.setStatus(Status.of(rs.getInt("active_flag")));
        auditLog.setUpdatedBy(rs.getInt("updated_by"));
        auditLog.setUpdatedAt(rs.getTimestamp("updated_at"));
        return auditLog;
    }
}
