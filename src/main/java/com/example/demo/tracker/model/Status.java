package com.example.demo.tracker.model;

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
    public Integer getValue() {
        return value;
    }
}
