package com.gurukulams.gurukulam.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * The type Data source configuration.
 */
@Configuration
public class DataSourceConfiguration {
    /**
     * build a datasource for h2 database.
     *
     * @return DataSource data source
     */
    @Bean(name = "h2DataSource")
    @Qualifier("h2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * H 2 jdbc template jdbc template.
     *
     * @param dataSource the data source
     * @return JdbcTemplate jdbc template
     */
    @Bean(name = "h2JdbcTemplate")
    @Primary
    public JdbcTemplate h2JdbcTemplate(
            final @Qualifier("h2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * build a datasource for postgres database.
     *
     * @return DataSource data source
     */
    @Bean(name = "postgresqlDataSource")
    @Qualifier("postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    @Lazy(true)
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build();
    }


}
