package com.techatpark.gurukulam.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.techatpark.gurukulam.service.connector.DatabaseConnector;
import com.techatpark.gurukulam.service.connector.h2.H2DatabaseConnector;
import com.techatpark.gurukulam.service.connector.postgress.PostgressDatabaseConnector;

/**
 * defines database type.
 */
public enum Database {

    /**
     * h2 database.
     */
    H2("h2", H2DatabaseConnector.class),
    /**
     * postgresql database.
     */
    POSTGRES("postgresql", PostgressDatabaseConnector.class);

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
     * @param aValue
     * @param aClazz
     */
    Database(final String aValue, final Class<? extends DatabaseConnector>
            aClazz) {
        this.value = aValue;
        this.clazz = aClazz;
    }

    /**
     * this method is used to set status from a json.
     *
     * @param value
     * @return database
     */
    @JsonCreator
    public static Database of(final String value) {
        for (Database database
                : values()) {
            if (value.equals(database.value)) {
                return database;
            }
        }

        return null;
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
