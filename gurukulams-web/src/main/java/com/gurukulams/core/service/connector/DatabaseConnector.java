package com.gurukulams.core.service.connector;

import com.gurukulams.core.model.Database;
import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.service.connector.h2.H2DatabaseConnector;
import com.gurukulams.core.service.connector.postgress.PostgressDatabaseConnector;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Database connector.
 */
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
     * @param aDataSource the a data source
     */
    public DatabaseConnector(final DataSource aDataSource) {
        this.dataSource = aDataSource;
    }

    /**
     * Gets instance of Database Connector.
     *
     * @param database           the database
     * @param applicationContext the application context
     * @return dbconnector database connector
     */
    public static DatabaseConnector getDatabaseConnector(
            final Database database,
            final ApplicationContext applicationContext) {
        DatabaseConnector databaseConnector = MAPPING.get(database.getValue());
        if (databaseConnector == null) {
            switch (database) {
                case H2 -> MAPPING.put(database.getValue(),
                        applicationContext
                                .getBean(H2DatabaseConnector.class));
                case POSTGRES -> MAPPING.put(database.getValue(),
                        applicationContext
                                .getBean(PostgressDatabaseConnector.class));
                default -> throw
                        new IllegalArgumentException("Unreachable Statement");
            }
            databaseConnector = MAPPING.get(database.getValue());
        }
        return databaseConnector;
    }

    /**
     * verify the given question with the answer.
     *
     * @param exam      the exam
     * @param question  the question
     * @param sqlAnswer the sql answer
     * @return successflag boolean
     */
    public abstract Boolean verify(SqlPractice exam, Question question,
                                   String sqlAnswer);

    /**
     * Load the script for the specific exam.
     *
     * @param exam the exam
     * @return successflag boolean
     */
    public abstract Boolean loadScript(SqlPractice exam);

    /**
     * Unload the script for the specific exam.
     *
     * @param exam the exam
     * @return successflag boolean
     */
    public abstract Boolean unloadScript(SqlPractice exam);

    /**
     * Get connection for specific exam.
     *
     * @param exam the exam
     * @return connetion connection
     */
    protected Connection getConnection(final SqlPractice exam) {
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            if (exam.getDatabase() != Database.H2) {
                connection.setSchema("EXAM_" + exam.getId());
            }

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * Get the count from a specific query.
     *
     * @param countQuery the count query
     * @param practice   the practice
     * @return count count
     */
    protected Integer getCount(final String countQuery,
                               final SqlPractice practice) {
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
     * @param updateQuery the update query
     * @param practice    the practice
     * @return numberOfUpdatedRows integer
     */
    protected Integer update(final String updateQuery,
                             final SqlPractice practice) {
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
     * @return dataSource data source
     */
    public DataSource getDataSource() {
        return dataSource;
    }


}
