package com.gurukulams.gurukulam.service.connector.postgress;

import com.gurukulams.gurukulam.model.Question;
import com.gurukulams.gurukulam.model.sql.SqlPractice;
import com.gurukulams.gurukulam.service.connector.DatabaseConnector;
import com.gurukulams.gurukulam.service.util.FlywayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * The type Postgress database connector.
 */
@Component
public class PostgressDatabaseConnector extends DatabaseConnector {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(
            PostgressDatabaseConnector.class);

    /**
     * Creates Postgress Connector.
     *
     * @param dataSource the data source
     */
    public PostgressDatabaseConnector(
            @Qualifier("postgresqlDataSource") final DataSource dataSource) {
        super(dataSource);
    }

    /**
     * @param exam
     * @param question
     * @param sqlAnswer
     * @return Boolean
     */
    @Override
    public final Boolean verify(final SqlPractice exam,
                                final Question question,
                                final String sqlAnswer) {
        Boolean isRigntAnswer = false;
        try {
            final String verificationSQL =
                    "SELECT COUNT(*) FROM ( " + question.getAnswer()
                            + " except " + sqlAnswer
                            + " ) AS TOTAL_ROWS";
            final Integer count = this.getCount(verificationSQL, exam);
            isRigntAnswer = (count == 0);

        } catch (final Exception ex) {
            logger.error("Error setting verify method ", ex);
        }
        return isRigntAnswer;
    }

    /**
     * @param exam
     * @return Boolean
     */
    @Override
    public final Boolean loadScript(final SqlPractice exam) {
        final Integer id = exam.getId();
        unloadScript(exam);
        final String query = "CREATE DATABASE EXAM_" + id;
        update(query, exam);
        FlywayUtil.loadScripts(exam, getDataSource());
        return null;
    }

    /**
     * @param exam
     * @return Boolean
     */
    @Override
    public Boolean unloadScript(final SqlPractice exam) {
        final Integer id = exam.getId();
        final String query = "DROP DATABASE IF EXISTS EXAM_" + id;
        update(query, exam);
        return null;
    }


}
