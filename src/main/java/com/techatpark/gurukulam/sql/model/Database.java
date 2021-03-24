package com.techatpark.gurukulam.sql.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.techatpark.gurukulam.sql.service.connector.DatabaseConnector;
import com.techatpark.gurukulam.sql.service.connector.postgress.PostgressDatabaseConnector;

/**
 * defines database type.
 */
public enum Database {

    /**
     * available database.
     */
    POSTGRES("postgres", PostgressDatabaseConnector.class);

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
     * this method is used to set status from a json.
     *
     * @param value
     * @return database
     */
    @JsonCreator
    public static Database of(final String value) {
        return value.equals(POSTGRES.value) ? POSTGRES : null;
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
     * get Class thatimplements given Database Connector.
     *
     * @return clazz
     */
    public Class<? extends DatabaseConnector> getConnectorClass() {
        return clazz;
    }

}
