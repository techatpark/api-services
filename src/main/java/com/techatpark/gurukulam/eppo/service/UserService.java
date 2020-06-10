package com.techatpark.gurukulam.eppo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for User related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public UserService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into User table.
     * 
     * @param newUser
     * @return reads the input data
     */
    public User create(final User newUser) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("")
                .usingGeneratedKeyColumns("id").usingColumns();
        final Map<String, Object> valuesMap = new HashMap<>();

        valuesMap.put("az_ad_id", newUser.getAzAdId());
        valuesMap.put("role_id", newUser.getRoleId());
        valuesMap.put("user_ref_id", newUser.getUserRefId());
        valuesMap.put("is_deleted", newUser.getIsDeleted());
        valuesMap.put("created_at", newUser.getCreatedAt());
        valuesMap.put("updated_at", newUser.getUpdatedAt());
        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table User.
     *
     * @param id
     * @return User
     */
    public Optional<User> read(final Integer id) {
        final String query = "SELECT  az_ad_id, role_id, user_ref_id, is_deleted, created_at, updated_at FROM users WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * update table User.
     *
     * @param id
     * @param newUser
     * @return User
     */
    public User update(final Integer id, final User newUser) {
        final String query = "UPDATE users  SET az_ad_id=?, role_id=?, user_ref_id=?, is_deleted = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(query, newUser.getAzAdId(), newUser.getRoleId(), newUser.getUserRefId(),
                newUser.getIsDeleted(), newUser.getCreatedAt(), newUser.getUpdatedAt(), id);
        return read(id).get();
    }

    /**
     * Delete all from User.
     * 
     * @param id
     * @return User
     */
    public Integer delete(final Integer id) {
        final String query = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(query);
    }

    /**
     * Delete all from User.
     *
     * @return User
     */
    public Integer delete() {
        final String query = "DELETE FROM users";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in User.
     *
     * @return User
     */
    public List<User> list() {
        final String query = "SELECT id, az_ad_id, role_id, user_ref_id, is_deleted, created_at, updated_at FROM users";
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return User
     * @throws SQLException
     */
    private User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final User user = new User();
        user.setId(rs.getInt("id"));
        user.setAzAdId(rs.getString("az_ad_id"));
        user.setIsDeleted(rs.getInt("is_deleted"));
        user.setUserRefId(rs.getInt("user_ref_id"));
        user.setRoleId(rs.getInt("role_id"));
        user.setCreatedAt(rs.getDate("created_at"));
        user.setUpdatedAt(rs.getDate("updated_at"));
        return user;
    }
}
