package com.example.demo.sql.service.connector.postgress;

import java.nio.file.Path;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.connector.DatabaseConnector;

import org.springframework.jdbc.core.JdbcTemplate;

public class PostgressDatabaseConnector extends DatabaseConnector {
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

}
