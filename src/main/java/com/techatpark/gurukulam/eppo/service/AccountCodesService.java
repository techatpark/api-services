package com.techatpark.gurukulam.eppo.service;

import java.util.Optional;

import javax.sql.DataSource;

import com.techatpark.gurukulam.eppo.model.AccountCodes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountCodesService {

    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;

    /**
     * @param jdbcTemplate
     * @param dataSource
     */
    public AccountCodesService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    /**
     * Inserting into accountcodes table.
     * 
     * @param accountCodes
     * @return accountcodes
     */
    public AccountCodes create(final AccountCodes accountCodes) {
        return null;
    }

    /**
     * reads from the accountcodes table.
     * 
     * @param id
     * @return accountcodes
     */
    public Optional<AccountCodes> read(final Integer id) {
        return null;
    }

    /**
     * updates table accountcodes using a id.
     * 
     * @param id
     * @param newAccountCodes
     * @return accountcodes
     */
    public AccountCodes update(final Integer id, final AccountCodes newAccountCodes) {
        return null;
    }

    /**
     * deletes a row from the accountcode table with given id.
     * 
     * @param id
     * @return successflag
     */
    public static Boolean delete(final Integer id) {
        return false;
    }

}
