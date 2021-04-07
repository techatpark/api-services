package com.techatpark.gurukulam.sql.service.connector;

import com.techatpark.gurukulam.sql.model.Database;
import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.model.Question;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
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
    private static final Map<String, DatabaseConnector> mapping = new HashMap<>();
    /**
     * Actual Database Store.
     */
    private final DataSource dataSource;

    /**
     * Creates a Database Connector.
     *
     * @param dataSource
     */
    public DatabaseConnector(final DataSource dataSource) {
        this.dataSource = dataSource;
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
                DataSource dataSource = applicationContext.getBean(database.getValue() + "DataSource", DataSource.class);
                databaseConnector = database.getConnectorClass().getDeclaredConstructor(DataSource.class)
                        .newInstance(dataSource);
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
    public abstract Boolean verify(Practice exam, Question question, String sqlAnswer);

    /**
     * Load the script for the specific exam.
     *
     * @param exam
     * @return successflag
     */
    public abstract Boolean loadScript(Practice exam);

    /**
     * Unload the script for the specific exam.
     *
     * @param exam
     * @return successflag
     */
    public abstract Boolean unloadScript(Practice exam);

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

    protected Integer getCount(final String countQuery, final Practice practice) {
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

    protected Integer update(final String updateQuery, final Practice practice) {
        Integer count = -1;
        try (Connection connection = getConnection(practice);
             Statement statement = connection.createStatement();) {
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
