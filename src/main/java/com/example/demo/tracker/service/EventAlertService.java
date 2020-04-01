package com.example.demo.tracker.service;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.example.demo.tracker.model.EventAlert;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventAlertService {
    /**
     * this is used to execute a connection with a database.
     */
    private final JdbcTemplate jdbcTemplate;
    /**
     * this is used to connect to relational database.
     */
    private final DataSource dataSource;

    /**
     * Creates a service for AlertNotification related operations.
     * 
     * @param jdbcTemplate
     * @param dataSource
     */
    public EventAlertService(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * inserting into eventalert table.
     * 
     * @param newEventAlert
     * @return eventalert
     */
    public EventAlert create(final EventAlert newEventAlert) {
        final String query = null;
        return null;
    }

    /**
     * reading from table with the given id.
     * 
     * @param id
     * @param newEventAlert
     * @return eventalert
     */
    public Optional<EventAlert> read(final Integer id, final EventAlert newEventAlert) {
        final String query = null;
        return null;
    }

    /**
     * update table with given id .
     * 
     * @param id
     * @param newEventAlert
     * @return eventalert
     */
    public EventAlert update(final Integer id, final EventAlert newEventAlert) {
        final String query = null;
        return null;
    }

    /**
     * delete from table with given id.
     * 
     * @param id
     * @return true if deleted
     */
    public Boolean delete(final Integer id) {
        final String query = null;
        return null;
    }

    /**
     * list from table with givn pagenumber and page size.
     * 
     * @param pageNumber
     * @param pageSize
     * @return eventalert
     */
    public List<EventAlert> list(final Integer pageNumber, final Integer pageSize) {
        final String query = null;
        return null;
    }

    /**
     * map data from and to the database table.
     * 
     * @param newEventAlert
     * @return eventalert
     */
    public EventAlert map(final EventAlert newEventAlert) {
        final String query = null;
        return null;
    }
}
