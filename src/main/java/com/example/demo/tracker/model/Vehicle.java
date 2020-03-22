package com.example.demo.tracker.model;

public class Vehicle {

    /**
     * tells registration number of vehicle.
     */
    private String registerNumber;
    /**
     * tells mobile number associated with vehicle.
     */
    private String mobileNumber;
    /**
     * tells speed limit of vehicle.
     */
    private int overspeedLimit;

    /**
     * gets registration number.
     * 
     * @return registration number
     */
    public String getRegisterNumber() {
        return registerNumber;
    }

    /**
     * sets registration number.
     * 
     * @param registerNumber
     */
    public void setRegisterNumber(final String registerNumber) {
        this.registerNumber = registerNumber;
    }

    /**
     * gets mobile number associated with the vehicle.
     * 
     * @return mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * sets the mobile number associated with the vehicle.
     * 
     * @param mobileNumber
     */
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * gets the over speed limit.
     * 
     * @return speed limit
     */
    public int getOverspeedLimit() {
        return overspeedLimit;
    }

    /**
     * sets the over limit of the vehicle.
     * 
     * @param overspeedLimit
     */
    public void setOverspeedLimit(final int overspeedLimit) {
        this.overspeedLimit = overspeedLimit;
    }

}
