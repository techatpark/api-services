package com.gurukulams.core.service.connector;

import com.gurukulams.core.model.Question;
import com.gurukulams.core.model.sql.SqlPractice;
import com.gurukulams.core.service.util.FlywayUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * The type H 2 database connector.
 */
@Component
public final class H2DatabaseConnector extends DatabaseConnector {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(
            H2DatabaseConnector.class);
    /**
     * declare a string jdbcUrl.
     */
    @Value("${spring.datasource.jdbcUrl}")
    private String jdbcUrl;
    /**
     * declare a string userName.
     */
    @Value("${spring.datasource.username}")
    private String username;
    /**
     * declare a string password.
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Creates h2 Connector.
     *
     * @param dataSource the data source
     */
    public H2DatabaseConnector(final DataSource dataSource) {
        super(dataSource);
    }

    /**
     * @param exam
     * @param question
     * @param sqlAnswer
     * @return Boolean
     */
    @Override
    public Boolean verify(final SqlPractice exam,
                                final Question question,
                                final String sqlAnswer) {
        Boolean isRigntAnswer = false;
        try {
            final String verificationSQL = "SELECT COUNT(*) FROM ( "
                    + question.getAnswer()
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
    public Boolean loadScript(final SqlPractice exam) {
        final Integer id = exam.getId();
        unloadScript(exam);
        final String schemaName = "EXAM_" + id;
        final String query = "DROP SCHEMA IF EXISTS " + schemaName;
        update(query, exam);
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl.replaceAll("practice_db", schemaName));
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        FlywayUtil.loadScripts(exam, new HikariDataSource(config));
        return null;
    }

    /**
     * @param exam
     * @return Boolean
     */
    @Override
    public Boolean unloadScript(final SqlPractice exam) {
        final Integer id = exam.getId();
        final String query = "DROP SCHEMA IF EXISTS EXAM_" + id;
        update(query, exam);
        return null;
    }


}
