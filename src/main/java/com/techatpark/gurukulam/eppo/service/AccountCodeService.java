package com.techatpark.gurukulam.eppo.service;

import javax.sql.DataSource;
import com.techatpark.gurukulam.eppo.model.AccountCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
@Service
public class AccountCodeService {
/**
* this is used to execute a connection with a database.
*/
private final JdbcTemplate jdbcTemplate;
/**
* this is used to connect to relational database.
*/
private final DataSource dataSource;
/** 
* Creates a service for AccountCode related operations.
* @param jdbcTemplate
* @param dataSource
*/
public AccountCodeService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
this.dataSource = dataSource;
this.jdbcTemplate = jdbcTemplate;
}
/**
* inserting into AccountCode table.
* 
* @param newAccountCode
* @return reads the input data
*/
public AccountCode create(final AccountCode newAccountCode) {
final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("account_codes")
.usingGeneratedKeyColumns("id").usingColumns("account_code", "code_used");
return read(id.intValue()).get();
}
}
  