package com.example.demo.tracker.model;

import java.time.LocalDateTime;

public class VehicleDeviceEvent {

    /**
     * tells the name space code.
     */
    private String namespaceCode;
    /**
     * tells the data.
     */
    private String data;
    /**
     * tells the device time.
     */
    private LocalDateTime devicetime;

    /**
     * gets the namespace code.
     * 
     * @return namespace code
     */
    public String getNamespaceCode() {
        return namespaceCode;
    }

    /**
     * sets name space code.
     * 
     * @param namespaceCode
     */
    public void setNamespaceCode(final String namespaceCode) {
        this.namespaceCode = namespaceCode;
    }

    /**
     * gets data.
     * 
     * @return data
     */
    public String getData() {
        return data;
    }

    /**
     * sets data.
     * 
     * @param data
     */
    public void setData(final String data) {
        this.data = data;
    }

    /**
     * gets device time.
     * 
     * @return device time
     */
    public LocalDateTime getDevicetime() {
        return devicetime;
    }

    /**
     * sets device time.
     * 
     * @param devicetime
     */
    public void setDevicetime(final LocalDateTime devicetime) {
        this.devicetime = devicetime;
    }

}
