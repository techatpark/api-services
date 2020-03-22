package com.example.demo.tracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VehicleType {
    /**
     * tells about vehicles and its code,id.
     */
    BUS("BUS", 1), VAN("VAN", 2), TRUCK("TRUCK", 3), BIKE("BIKE", 4), CAR("CAR", 5);

    /**
     * tells the id.
     */
    private final int id;
    /**
     * tells the code.
     */
    private final String code;

    /**
     * constructor for a vehicle type.
     * 
     * @param code
     * @param id
     */
    VehicleType(final String code, final int id) {
        this.code = code;
        this.id = id;
    }

    /**
     * gets id of vehicle.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * gets code.
     * 
     * @return code
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * gets vehicle type from id.
     * 
     * @param id
     * @return type
     */
    public static VehicleType of(final int id) {
        final VehicleType[] values = values();
        for (final VehicleType errorCode : values) {
            if (errorCode.getId() == id) {
                return errorCode;
            }
        }
        return BUS;
    }

    /**
     * gets vehicle type from code.
     * 
     * @param code
     * @return type
     */
    @JsonCreator
    public static VehicleType of(final String code) {
        final VehicleType[] values = values();
        for (final VehicleType mode : values) {
            if (mode.getCode().equalsIgnoreCase(code)) {
                return mode;
            }
        }
        return BUS;
    }

}
