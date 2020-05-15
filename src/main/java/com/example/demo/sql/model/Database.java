package com.example.demo.sql.model;

import com.example.demo.sql.service.connector.DatabaseConnector;
import com.example.demo.sql.service.connector.mysql.MySQLDatabaseConnector;
import com.example.demo.sql.service.connector.postgress.PostgressDatabaseConnector;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * defines database type.
 */
public enum Database {

    /**
     * available database.
     */
    MYSQL("mysql", MySQLDatabaseConnector.class), POSTGRES("postgres", PostgressDatabaseConnector.class);

    /**
     * value will be either mysql or postgres.
     */
    private final String value;

    /**
     * implementation class for connctor.
     */
    private final Class<? extends DatabaseConnector> clazz;

    /**
     * constructor to create type of database.
     * 
     * @param value
     * @param clazz
     */
    Database(final String value, final Class<? extends DatabaseConnector> clazz) {
        this.value = value;
        this.clazz = clazz;
    }

    /**
     * get the value of database.
     * 
     * @return value.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    

    /**
     * this method is used to set status from a json.
     * 
     * @param value
     * @return database
     */
    @JsonCreator
    public static Database of(final String value) {
        return value.equals(POSTGRES.value) ? POSTGRES : MYSQL;
    }

    /**
     * get Class thatimplements given Database Connector.
     * @return clazz
     */
    public Class<? extends DatabaseConnector> getConnectorClass() {
        return clazz;
    }

}
