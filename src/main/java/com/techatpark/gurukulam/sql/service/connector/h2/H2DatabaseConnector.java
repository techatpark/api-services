package com.techatpark.gurukulam.sql.service.connector.h2;

import com.techatpark.gurukulam.sql.model.Practice;
import com.techatpark.gurukulam.sql.model.Question;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import com.techatpark.gurukulam.sql.service.util.FlywayUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class H2DatabaseConnector extends DatabaseConnector {

    /**
     * logger for thiss class.
     */
    private final Logger logger = LoggerFactory.getLogger(H2DatabaseConnector.class);
    @Value("${spring.datasource.jdbcUrl}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Creates h2 Connector.
     *
     * @param dataSource
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
    public final Boolean verify(final Practice exam, final Question question, final String sqlAnswer) {
        Boolean isRigntAnswer = false;
        try {
            String verificationSQL = "SELECT COUNT(*) FROM ( " + question.getAnswer() + " except " + sqlAnswer
                    + " ) AS TOTAL_ROWS";
            Integer count = this.getCount(verificationSQL, exam);
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
    public final Boolean loadScript(final Practice exam) {
        final Integer id = exam.getId();
        unloadScript(exam);
        String schemaName = "EXAM_" + id;
        HikariConfig config = new HikariConfig();
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
    public Boolean unloadScript(final Practice exam) {
        final Integer id = exam.getId();
        final String query = "DROP SCHEMA IF EXISTS EXAM_" + id;
        update(query, exam);
        return null;
    }


}
