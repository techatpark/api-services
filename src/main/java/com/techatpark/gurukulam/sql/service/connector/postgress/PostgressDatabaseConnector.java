package com.techatpark.gurukulam.sql.service.connector.postgress;

import com.techatpark.gurukulam.sql.model.Exam;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import com.techatpark.gurukulam.sql.service.util.FlywayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class PostgressDatabaseConnector extends DatabaseConnector {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(PostgressDatabaseConnector.class);

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
        Boolean isRigntAnswer = false;
        try {
            String verificationSQL = "SELECT COUNT(*) FROM ( " + question.getAnswer() + " except " + sqlAnswer
                    + " ) AS TOTAL_ROWS";
            Integer count = this.getJdbcTemplate().queryForObject(verificationSQL, Integer.class);
            isRigntAnswer = (count == 0);

        } catch (Exception ex) {
            logger.error("Error setting verify method ", ex);
        }
        return isRigntAnswer;
    }

    /**
     * @param exam
     * @return Boolean
     */
    @Override
    public final Boolean loadScript(final Exam exam) {
        final Integer id = exam.getId();
        final String query = "CREATE DATABASE EXAM_" + id;
        getJdbcTemplate().update(query);
        FlywayUtil.loadScripts(exam, getJdbcTemplate().getDataSource());
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
