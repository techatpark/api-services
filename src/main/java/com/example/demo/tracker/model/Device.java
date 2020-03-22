package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class Device extends BaseModel {

    /**
     * gsm code of the device.
     */
    private String gsmCode;
    /**
     * IMEI code of the device.
     * 
     * @see <a href=
     *      "https://support.bell.ca/mobility/smartphones_and_mobile_internet/what_is_an_imei_number_and_how_can_i_find_mine">IMEI
     *      code</a>
     */
    private String deviceIMEICode;
    /**
     * Sensor of the device.
     */
    @NotNull(message = "Sensor cannot be null")
    private String sensor;
    /**
     * Remark about the device.
     */
    private String remarks;
    /**
     * api flag for device.
     * 
     */
    private int apiFlag;

    /**
     * this will get the gsmcode of device.
     * 
     * @return gsmcode.
     */
    public String getGsmCode() {
        return gsmCode;
    }

    /**
     * this will set the gsmcode of the device.
     * 
     * @param gsmCode
     */
    public void setGsmCode(final String gsmCode) {
        this.gsmCode = gsmCode;
    }

    /**
     * this will get the IMEICode of the device.
     * 
     * @return IMEI Code
     */
    public String getDeviceIMEICode() {
        return deviceIMEICode;
    }

    /**
     * this will set the imei code of the device.
     * 
     * @param deviceIMEICode
     */
    public void setDeviceIMEICode(final String deviceIMEICode) {
        this.deviceIMEICode = deviceIMEICode;
    }

    /**
     * this will get the sensor of the device.
     * 
     * @return sensor
     */

    public String getSensor() {
        return sensor;
    }

    /**
     * this will set the sensor of the device.
     * 
     * @param sensor
     */

    public void setSensor(final String sensor) {
        this.sensor = sensor;
    }

    /**
     * this will get the remarks about the device.
     * 
     * @return remarks
     */

    public String getRemarks() {
        return remarks;
    }

    /**
     * this will set the remarks about the device.
     * 
     * @param remarks
     */
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * this will get the apiflag of the device.
     * 
     * @return apiflag
     */
    public int getApiFlag() {
        return apiFlag;
    }

    /**
     * this will set apiflag.
     * 
     * @param apiFlag
     */
    public void setApiFlag(final int apiFlag) {
        this.apiFlag = apiFlag;
    }

}
