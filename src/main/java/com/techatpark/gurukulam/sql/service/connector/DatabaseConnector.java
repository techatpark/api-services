package com.techatpark.gurukulam.sql.service.connector;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Exam;
import com.techatpark.gurukulam.sql.model.Question;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseConnector {

    /**
     * Contains Connector Implementation Mappings.
     */
    private static final Map<String, DatabaseConnector> mapping = new HashMap<>();
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
     * Gets instance of Database Connector.
     *
     * @param database
     * @param applicationContext
     * @return dbconnector
     */
    public static DatabaseConnector getDatabaseConnector(final Database database,
                                                         final ApplicationContext applicationContext) {
        DatabaseConnector databaseConnector = mapping.get(database.getValue());
        if (databaseConnector == null) {
            try {
                JdbcTemplate jdbcTemplate = applicationContext.getBean(database.getValue() + "JdbcTemplate",JdbcTemplate.class);
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
     * @return successflag
     */
    public abstract Boolean loadScript(Exam exam);

    /**
     * Unload the script for the specific exam.
     *
     * @param id
     * @return successflag
     */
    public abstract Boolean unloadScript(Integer id);

    /**
     * Get connection for specific exam.
     *
     * @param exam
     * @return connetion
     */
    public abstract Connection getConnection(Exam exam);

    /**
     * Gets Actual Datastore.
     *
     * @return jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
