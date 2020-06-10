package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AdminUser;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AdminUser related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public AdminUserService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into AdminUser table.
     * 
     * @param newAdminUser
     * @return reads the input data
     */
    public AdminUser create(final AdminUser newAdminUser) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("admin_users")
                .usingGeneratedKeyColumns("id").usingColumns("full_name", "email_id", "contact_number", "status",
                        "is_deleted", "created_by", "updated_by", "role_id");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("full_name", newAdminUser.getFullName());
        valuesMap.put("email_id", newAdminUser.getEmailId());
        valuesMap.put("contact_number", newAdminUser.getContactNumber());
        valuesMap.put("status", newAdminUser.getStatus());
        valuesMap.put("is_deleted", newAdminUser.getIsDeleted());
        valuesMap.put("created_by", newAdminUser.getCreatedBy());
        valuesMap.put("updated_by", newAdminUser.getUpdatedBy());
        valuesMap.put("role_id", newAdminUser.getRoleId());

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table AdminUser.
     *
     * @param id
     * @return AdminUser
     */
    public Optional<AdminUser> read(final Integer id) {
        final String query = "SELECT full_name, email_id, contact_number, status, is_deleted, created_by, updated_by, role_id, created_at, updated_at FROM admin_users WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table AdminUser.
     *
     * @param id
     * @param newAdminUser
     * @return AdminUser
     */
    public AdminUser update(final Integer id, final AdminUser newAdminUser) {
        final String query = "UPDATE admin_users SET full_name = ?, email_id = ?, contact_number = ?, status = ?, is_deleted = ?, created_by = ?, updated_by = ?, role_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newAdminUser.getFullName(), newAdminUser.getEmailId(),
                newAdminUser.getContactNumber(), newAdminUser.getStatus(), newAdminUser.getIsDeleted(),
                newAdminUser.getCreatedBy(), newAdminUser.getUpdatedBy(), newAdminUser.getRoleId(), id);
        return read(id).get();
    }

    /**
     * Delete all from AdminUser.
     * 
     * @param id
     * @return AdminUser
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM admin_users WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from AdminUser.
     *
     * @return AdminUser
     */
    public Integer delete() {
        final String query = "DELETE FROM admin_users";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in AdminUser.
     *
     * @return AdminUser
     */
    public List<AdminUser> list() {
        final String query = "SELECT id, full_name, email_id, contact_number, status, is_deleted, created_by, updated_by, role_id, created_at, updated_at FROM admin_users";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return AdminUser
     * @throws SQLException
     */
    private AdminUser mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final AdminUser adminUser = new AdminUser();
        adminUser.setId(rs.getInt("id"));
        adminUser.setFullName(rs.getString("full_name"));
        adminUser.setEmailId(rs.getString("email_id"));
        adminUser.setContactNumber(rs.getString("contact_number"));
        adminUser.setStatus(rs.getShort("status"));
        adminUser.setIsDeleted(rs.getShort("is_deleted"));
        adminUser.setCreatedBy(rs.getInt("created_by"));
        adminUser.setUpdatedBy(rs.getInt("updated_by"));
        adminUser.setRoleId(rs.getInt("role_id"));
        adminUser.setCreatedAt(rs.getDate("created_at"));
        adminUser.setUpdatedAt(rs.getDate("updated_at"));
        return adminUser;
    }
}
