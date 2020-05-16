package com.example.demo.sql.service.connector.postgress;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.connector.DatabaseConnector;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostgressDatabaseConnector extends DatabaseConnector {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(PostgressDatabaseConnector.class);

    /**
     * Environment to get the values specific to environement.
     */
    @Autowired
    private Environment env;

    /**
     * Creates Postgress Connector.
     * 
     * @param jdbcTemplate
     */
    public PostgressDatabaseConnector(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /**
     * @param exam
     * @param question
     * @param sqlAnswer
     * @return Boolean
     */
    @Override
    public final Boolean verify(final Exam exam, final Question question, final String sqlAnswer) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param exam
     * @param scriptFiles
     * @return Boolean
     */
    @Override
    public final Boolean loadScript(final Exam exam, final Path[] scriptFiles) {
        // 1. Create a new Schema - Schema Name : Exam_<<Examid>>
        final Integer id = exam.getId();
        final String query = "CREATE DATABASE EXAM_" + id;
        getJdbcTemplate().update(query);
        try {
            final Flyway flyway = Flyway.configure().dataSource(getConnection(exam).getMetaData().getURL(),
                    getConnection(exam).getMetaData().getUserName(), env.getProperty("spring.datasource.password"))
                    .load();
            flyway.migrate();
        } catch (SQLException sqlException) {
            logger.error("Error loading script files ", sqlException);
        }
        // 2. Load Script Files
        return null;
    }

    /**
     * @param id
     * @return Boolean
     */
    @Override
    public Boolean unloadScript(final Integer id) {
        final String query = "DROP DATABASE IF EXISTS EXAM_" + id;
        getJdbcTemplate().update(query);
        return null;
    }

    /**
     * Get psotgress connection for the given exam schema.
     * 
     * @param exam
     * @return Connection
     */
    @Override
    public Connection getConnection(final Exam exam) {
        Connection connection = null;
        try {
            connection = getJdbcTemplate().getDataSource().getConnection();
            connection.setSchema("EXAM_" + exam.getId());
        } catch (final SQLException sqlException) {
            logger.error("Error setting schema into the connection ", sqlException);
        }
        return connection;
    }

}
