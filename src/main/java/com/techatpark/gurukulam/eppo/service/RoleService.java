package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.Role;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for Role related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public RoleService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into Role table.
     * 
     * @param newRole
     * @return reads the input data
     */
    public Role create(final Role newRole) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("roles")
                .usingGeneratedKeyColumns("id")
                .usingColumns("role_name", "role_system", "created_by", "updated_by", "is_deleted", "status");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("role_name", newRole.getRoleName());
        valuesMap.put("role_system", newRole.getRoleSystem());
        valuesMap.put("created_by", newRole.getCreatedBy());
        valuesMap.put("updated_by", newRole.getUpdatedBy());
        valuesMap.put("is_deleted", newRole.getIsDeleted());
        valuesMap.put("status", newRole.getStatus());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table Role.
     *
     * @param id
     * @return Role
     */
    public Optional<Role> read(final Integer id) {
        final String query = "SELECT role_name, role_system, created_by, updated_by, is_deleted, status, created_at, updated_at FROM roles WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table Role.
     *
     * @param id
     * @param newRole
     * @return Role
     */
    public Role update(final Integer id, final Role newRole) {
        final String query = "UPDATE roles SET role_name = ?, role_system = ?, created_by = ?, updated_by = ?, is_deleted = ?, status = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newRole.getRoleName(), newRole.getRoleSystem(), newRole.getCreatedBy(),
                newRole.getUpdatedBy(), newRole.getIsDeleted(), newRole.getStatus(), id);
        return read(id).get();
    }

    /**
     * Delete all from Role.
     * 
     * @param id
     * @return Role
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM roles WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from Role.
     *
     * @return Role
     */
    public Integer delete() {
        final String query = "DELETE FROM roles";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in Role.
     *
     * @return Role
     */
    public List<Role> list() {
        final String query = "SELECT id, role_name, role_system, created_by, updated_by, is_deleted, status, created_at, updated_at FROM roles";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return Role
     * @throws SQLException
     */
    private Role mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setRoleName(rs.getString("role_name"));
        role.setRoleSystem(rs.getString("role_system"));
        role.setCreatedBy(rs.getInt("created_by"));
        role.setUpdatedBy(rs.getInt("updated_by"));
        role.setIsDeleted(rs.getInt("is_deleted"));
        role.setStatus(rs.getInt("status"));
        role.setCreatedAt(rs.getDate("created_at"));
        role.setUpdatedAt(rs.getDate("updated_at"));
        return role;
    }
}
