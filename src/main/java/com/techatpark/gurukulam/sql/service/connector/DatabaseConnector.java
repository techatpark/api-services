package com.techatpark.gurukulam.sql.service.connector;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.model.sql.SqlPractice;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseConnector {

    /**
     * Contains Connector Implementation Mappings.
     */
    private static final Map<String, DatabaseConnector> MAPPING =
            new HashMap<>();
    /**
     * Actual Database Store.
     */
    private final DataSource dataSource;

    /**
     * Creates a Database Connector.
     *
     * @param aDataSource
     */
    public DatabaseConnector(final DataSource aDataSource) {
        this.dataSource = aDataSource;
    }

    /**
     * Gets instance of Database Connector.
     *
     * @param database
     * @param applicationContext
     * @return dbconnector
     */
    public static DatabaseConnector getDatabaseConnector(
            final Database database,
            final ApplicationContext applicationContext) {
        DatabaseConnector databaseConnector = MAPPING.get(database.getValue());
        if (databaseConnector == null) {
            databaseConnector =
                    applicationContext.getBean(database.getConnectorClass());
            MAPPING.put(database.getValue(), databaseConnector);
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
    public abstract Boolean verify(SqlPractice exam, Question question,
                                   String sqlAnswer);

    /**
     * Load the script for the specific exam.
     *
     * @param exam
     * @return successflag
     */
    public abstract Boolean loadScript(SqlPractice exam);

    /**
     * Unload the script for the specific exam.
     *
     * @param exam
     * @return successflag
     */
    public abstract Boolean unloadScript(SqlPractice exam);

    /**
     * Get connection for specific exam.
     *
     * @param exam
     * @return connetion
     */
    protected Connection getConnection(final Practice exam) {
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setSchema("EXAM_" + exam.getId());
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * Get the count from a specific query.
     *
     * @param countQuery
     * @param practice
     * @return count
     */
    protected Integer getCount(final String countQuery,
                               final Practice practice) {
        Integer count = -1;
        try (Connection connection = getConnection(practice);
             Statement statement = connection.createStatement();
             ResultSet r = statement.executeQuery(countQuery)) {
            r.next();
            count = r.getInt(1);

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return count;
    }

    /**
     * Update a database using a query.
     *
     * @param updateQuery
     * @param practice
     * @return numberOfUpdatedRows
     */
    protected Integer update(final String updateQuery,
                             final Practice practice) {
        Integer count = -1;
        try (Connection connection = getConnection(practice);
             Statement statement = connection.createStatement()) {
            count = statement.executeUpdate(updateQuery);
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return count;
    }


    /**
     * Gets Actual Datastore.
     *
     * @return dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }


}
