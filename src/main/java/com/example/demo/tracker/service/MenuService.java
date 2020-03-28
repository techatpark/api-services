package com.example.demo.tracker.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.Menu;
import com.example.demo.tracker.model.Status;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
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
    public MenuService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into menu table.
     * 
     * @param newMenu
     * @return reads the input data
     */
    public Menu create(final Menu newMenu) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("menu")
                .usingGeneratedKeyColumns("Id").usingColumns("code", "name", "link", "action_code", "lookup_id",
                        "default_flag", "display_flag", "product_type_id", "updated_by");
        final Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("code", newMenu.getCode());
        valuesMap.put("name", newMenu.getName());
        valuesMap.put("link", newMenu.getLink());
        valuesMap.put("action_code", newMenu.getActionCode());
        valuesMap.put("lookup_id", newMenu.getLookupId());
        valuesMap.put("default_flag", newMenu.getDefaultFlag());
        valuesMap.put("display_flag", newMenu.getDisplayFlag());
        valuesMap.put("product_type_id", newMenu.getProductTypeId());

        valuesMap.put("updated_by", 1);

        // Actual Query Execution happens
        final Number id = insert.executeAndReturnKey(valuesMap);
        return read(id.intValue()).get();
    }

    /**
     * reads from table menu.
     * 
     * @param id
     * @return menu
     */
    public Optional<Menu> read(final Integer id) {
        final String query = "Select id,code,name,link,action_code,lookup_id,default_flag,display_flag,product_type_id,active_flag,updated_by,updated_at FROM menu WHERE id = ? AND active_flag = 1";
        try {
            return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    /**
     * Delete a row with given id.
     * 
     * @param id
     * @return success flag.
     */
    public Boolean delete(final Integer id) {
        final String query = "UPDATE menu SET active_flag = 0 WHERE id = ? AND active_flag = 1";
        Integer updatedRows = jdbcTemplate.update(query, new Object[] { id });
        return !(updatedRows == 0);
    }

    /**
     * soft delete all from menu.
     * 
     * @return menu
     */
    public Integer delete() {
        return delete(false);
    }

    /**
     * Delete all from menu.
     * 
     * @param isHardDelete should hard delete or soft delete by updating active_flag
     *                     is 0
     * @return menu
     */
    public Integer delete(final Boolean isHardDelete) {
        final String query = isHardDelete ? "DELETE FROM menu" : "UPDATE menu SET active_flag = 0";
        return jdbcTemplate.update(query);
    }

    /**
     * gets a list of all in menu.
     * 
     * @param pageNumber
     * @param pageSize
     * @return menu
     */
    public List<Menu> list(final Integer pageNumber, final Integer pageSize) {
        String query = "SELECT id,code,name,link,action_code,lookup_id,default_flag,display_flag,product_type_id,active_flag,updated_by,updated_at FROM menu";
        query = query + " LIMIT " + pageSize + " OFFSET " + (pageNumber - 1);
        return jdbcTemplate.query(query, this::mapRow);
    }

    /**
     * update table menu. this call might fail if the id is unavailable, active flag
     * is not true or updated at is not matching.
     * 
     * @param id
     * @param menuToBeUpdated
     * @return menu
     */
    public Menu update(final Integer id, final Menu menuToBeUpdated) {
        final String query = "UPDATE menu SET code = ?,name = ?,link = ?,action_code = ?,lookup_id = ?,default_flag = ?,display_flag = ?,product_type_id = ?, updated_by = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ? AND active_flag = 1 AND updated_at = ?";
        Integer updatedRows = jdbcTemplate.update(query, menuToBeUpdated.getCode(), menuToBeUpdated.getName(),
                menuToBeUpdated.getLink(), menuToBeUpdated.getActionCode(), menuToBeUpdated.getLookupId(),
                menuToBeUpdated.getDefaultFlag(), menuToBeUpdated.getDisplayFlag(), menuToBeUpdated.getProductTypeId(),
                id, menuToBeUpdated.getUpdatedAt());
        return updatedRows == 0 ? null : read(id).get();
    }

    /**
     * Maps the data from and to the database.
     * 
     * @param rs
     * @param rowNum
     * @return menu
     * @throws SQLException
     */
    private Menu mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Menu menu = new Menu();
        menu.setId(rs.getInt("id"));
        menu.setCode(rs.getString("code"));
        menu.setName(rs.getString("name"));
        menu.setLink(rs.getString("link"));
        menu.setActionCode(rs.getString("action_code"));
        menu.setLookupId(rs.getInt("lookup_id"));
        menu.setDefaultFlag(rs.getInt("default_flag"));
        menu.setDisplayFlag(rs.getInt("display_flag"));
        menu.setProductTypeId(rs.getInt("product_type_id"));
        menu.setStatus(Status.of(rs.getInt("active_flag")));
        menu.setUpdatedBy(rs.getInt("updated_by"));
        menu.setUpdatedAt(rs.getTimestamp("updated_at"));
        return menu;
    }

}
