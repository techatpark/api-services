package com.example.demo.sql.model;

import java.lang.reflect.InvocationTargetException;

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
     * get the database connector.
     * 
     * @return value.
     */
    public DatabaseConnector getConnector() {
        try {
            return this.clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
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
