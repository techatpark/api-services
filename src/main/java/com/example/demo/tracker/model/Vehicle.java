package com.example.demo.tracker.model;

public class Vehicle extends BaseModel {

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
    private Integer overspeedLimit;
    /**
     * tells the device attached to this vehicle.
     */
    private Device device;
    /**
     * tells vehicle type .
     */
    private VehicleType vehicleType;

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
    public Integer getOverspeedLimit() {
        return overspeedLimit;
    }

    /**
     * sets the over limit of the vehicle.
     * 
     * @param overspeedLimit
     */
    public void setOverspeedLimit(final Integer overspeedLimit) {
        this.overspeedLimit = overspeedLimit;
    }

    /**
     * gets device.
     * 
     * @return device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * sets device.
     * 
     * @param device
     */
    public void setDevice(final Device device) {
        this.device = device;
    }

    /**
     * gets vehicle type.
     * 
     * @return vehicle type
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * sets vehicle type.
     * 
     * @param vehicleType
     */
    public void setVehicleType(final VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

}
