package com.example.demo.sql.service.connector;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.sql.model.Database;
import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DatabaseConnector {

    /**
     * Contains Connector Implementation Mappings.
     */
    private static Map<String, DatabaseConnector> mapping = new HashMap<>();

    /**
     * Gets instance of Database Connector.
     * 
     * @param database
     * @param jdbcTemplate
     * @return dbconnector
     */
    public static DatabaseConnector getDatabaseConnector(final Database database, final JdbcTemplate jdbcTemplate) {
        DatabaseConnector databaseConnector = mapping.get(database.getValue());
        if (databaseConnector == null) {
            try {
                databaseConnector = database.getConnectorClass().getDeclaredConstructor(JdbcTemplate.class)
                        .newInstance(jdbcTemplate);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                // Unreachable Block. Ignore Catching
            }
            mapping.put(database.getValue(), databaseConnector);
        }
        return databaseConnector;
    }

    /**
     * Actual Database Store.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Creates a Database Connector.
     * 
     * @param jdbcTemplate
     */
    public DatabaseConnector(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * verify the given question with the answer.
     * 
     * @param exam
     * @param question
     * @param sqlAnswer
     * @return successflag
     */
    public abstract Boolean verify(Exam exam, Question question, String sqlAnswer);

    /**
     * Load the script for the specific exam.
     * 
     * @param exam
     * @param scriptFiles
     * @return successflag
     */
    public abstract Boolean loadScript(Exam exam, Path[] scriptFiles);

    /**
     * Unload the script for the specific exam.
     * 
     * @param id
     * @return successflag
     */
    public abstract Boolean unloadScript(Integer id);

    /**
     * Gets Actual Datastore.
     * @return jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
