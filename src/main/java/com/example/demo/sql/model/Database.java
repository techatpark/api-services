package com.example.demo.sql.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * defines database type.
 */
public enum Database {

    /**
     * available database.
     */
    MYSQL("mysql"), POSTGRES("postgres");

    /**
     * value will be either mysql or postgres.
     */
    private final String value;

    /**
     * constructor to create type of database.
     * 
     * @param value
     */
    Database(final String value) {
        this.value = value;
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

}
