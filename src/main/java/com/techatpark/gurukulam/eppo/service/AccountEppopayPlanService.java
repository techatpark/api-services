package com.techatpark.gurukulam.eppo.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import com.techatpark.gurukulam.eppo.model.AccountEppopayPlan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
@Service
public class AccountEppopayPlanService {
/**
* this is used to execute a connection with a database.
*/
private final JdbcTemplate jdbcTemplate;
/**
* this is used to connect to relational database.
*/
private final DataSource dataSource;
/** 
* Creates a service for AccountEppopayPlan related operations.
* @param jdbcTemplate
* @param dataSource
*/
public AccountEppopayPlanService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
this.dataSource = dataSource;
this.jdbcTemplate = jdbcTemplate;
}
/**
* inserting into AccountEppopayPlan table.
* 
* @param newAccountEppopayPlan
* @return reads the input data
*/
public AccountEppopayPlan create(final AccountEppopayPlan newAccountEppopayPlan) {
final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_eppopay_plans")
.usingGeneratedKeyColumns("id").usingColumns("created_by", "updated_by", "is_deleted", "status", "account_id", "eppopay_plan_id");
final Map<String, Object> valuesMap = new HashMap<>();
valuesMap.put("created_by", newAccountEppopayPlan.getCreatedBy());
valuesMap.put("updated_by", newAccountEppopayPlan.getUpdatedBy());
valuesMap.put("is_deleted", newAccountEppopayPlan.getIsDeleted());
valuesMap.put("status", newAccountEppopayPlan.getStatus());
valuesMap.put("account_id", newAccountEppopayPlan.getAccountId());
valuesMap.put("eppopay_plan_id", newAccountEppopayPlan.getEppopayPlanId());
// Actual Query Execution happens
final Number id = insert.executeAndReturnKey(valuesMap);
return read(id.intValue()).get();
}
/**
* reads from table AccountEppopayPlan.
*
* @param id
* @return AccountEppopayPlan
*/
public Optional<AccountEppopayPlan> read(final Integer id) {
final String query = "SELECT created_by, updated_by, is_deleted, status, account_id, eppopay_plan_id, created_at, updated_at FROM account_eppopay_plans WHERE id = ?";
try {
return Optional.of(jdbcTemplate.queryForObject(query, new Object[] { id }, this::mapRow));
} catch (EmptyResultDataAccessException e) {
return Optional.empty();
}
}
/**
* update table AccountEppopayPlan.
*
* @param id
* @param newAccountEppopayPlan
* @return AccountEppopayPlan
*/
public AccountEppopayPlan update(final Integer id, final AccountEppopayPlan newAccountEppopayPlan) {
final String query = "UPDATE account_eppopay_plans SET created_by = ?, updated_by = ?, is_deleted = ?, status = ?, account_id = ?, eppopay_plan_id = ?, created_at = CURRENT_TIMESTAMP, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
jdbcTemplate.update(query, newAccountEppopayPlan.getCreatedBy(), newAccountEppopayPlan.getUpdatedBy(), newAccountEppopayPlan.getIsDeleted(), newAccountEppopayPlan.getStatus(), newAccountEppopayPlan.getAccountId(), newAccountEppopayPlan.getEppopayPlanId(), id);
return read(id).get();
}
/**
* Delete all from AccountEppopayPlan.
*@param id
* @return AccountEppopayPlan
*/
public Integer delete(final Integer id) {
final String query = "DELETE FROM account_eppopay_plans";
return jdbcTemplate.update(query);
}
/**
* Delete all from AccountEppopayPlan.
*
* @return AccountEppopayPlan
*/
public Integer delete() {
final String query = "DELETE FROM account_eppopay_plans";
return jdbcTemplate.update(query);
}
/**
* gets a list of all in AccountEppopayPlan.
*
* @return AccountEppopayPlan
*/
public List<AccountEppopayPlan> list() {
final String query = "SELECT id, created_by, updated_by, is_deleted, status, account_id, eppopay_plan_id, created_at, updated_at FROM account_eppopay_plans";
return jdbcTemplate.query(query, this::mapRow);
}
/**
* Maps the data from and to the database.
*
* @param rs
* @param rowNum
* @return AccountEppopayPlan
* @throws SQLException
*/
private AccountEppopayPlan mapRow(final ResultSet rs, final int rowNum) throws SQLException {
final AccountEppopayPlan accountEppopayPlan = new AccountEppopayPlan();
accountEppopayPlan.setId(rs.getInt("id"));
accountEppopayPlan.setCreatedBy(rs.getInt("created_by"));
accountEppopayPlan.setUpdatedBy(rs.getInt("updated_by"));
accountEppopayPlan.setIsDeleted(rs.getInt("is_deleted"));
accountEppopayPlan.setStatus(rs.getInt("status"));
accountEppopayPlan.setAccountId(rs.getInt("account_id"));
accountEppopayPlan.setEppopayPlanId(rs.getInt("eppopay_plan_id"));
accountEppopayPlan.setCreatedAt(rs.getDate("created_at"));
accountEppopayPlan.setUpdatedAt(rs.getDate("updated_at"));
return accountEppopayPlan;
}
}
 
