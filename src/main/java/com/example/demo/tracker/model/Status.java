package com.example.demo.tracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * defining a status.
 */
public enum Status {
    /**
     * available status.
     */
    ACTIVE(1), INACTIVE(0);

    /**
     * this valule must be either 0 or 1.
     */
    private final Integer value;

    /**
     * constructor to create status.
     * 
     * @param value
     */
    Status(final Integer value) {
        this.value = value;
    }

    /**
     * get the value of status.
     * 
     * @return value.
     */
    @JsonValue
    public Integer getValue() {
        return value;
    }

    /**
     * this method is used to set status from a json.
     * 
     * @param value
     * @return status
     */
    @JsonCreator
    public static Status of(final Integer value) {
        return value == 0 ? INACTIVE : ACTIVE;
    }
}
