package com.techatpark.gurukulam.sql.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "h2DataSource")
    @Qualifier("h2DataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    @Primary
    public DataSource h2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2JdbcTemplate")
    @Primary
    public JdbcTemplate h2JdbcTemplate(
            @Qualifier("h2DataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "postgresqlDataSource")
    @Qualifier("postgresqlDataSource")
    @ConfigurationProperties(prefix="spring.datasource.postgresql")
    public DataSource postgresqlDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresqlJdbcTemplate")
    public JdbcTemplate postgresqlJdbcTemplate(
            @Qualifier("postgresqlDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}