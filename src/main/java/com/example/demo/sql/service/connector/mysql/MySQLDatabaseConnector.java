package com.example.demo.sql.service.connector.mysql;

import java.nio.file.Path;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.connector.DatabaseConnector;

import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLDatabaseConnector extends DatabaseConnector {

    /**
     * Creates MySQL Connector.
     * @param jdbcTemplate
     */
    public MySQLDatabaseConnector(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public final Boolean verify(final Exam exam, final Question question, final String sqlAnswer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Boolean loadScript(final Exam exam, final Path[] scriptFiles) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * upload script.
     */
    @Override
    public Boolean unloadScript(final Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    

}
