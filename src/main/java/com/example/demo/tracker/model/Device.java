package com.example.demo.tracker.model;

import java.util.Date;

public class Device {
    /**
     * id of the device.
     */
    private int id;
    /**
     * code of the device.
     */
    private String code;
    /**
     * name of the device.
     */
    private String name;
    /**
     * Tells whether the device is active, when set to 1.
     */
    private int activeFlag;
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
     * tells who updated recently.
     */
    private Integer updatedBy;

    /**
     * tells when updated recently.
     */
    private Date updatedAt;

    /**
     * <p>
     * This will get Id of a device.
     * </p>
     * 
     * @return id of the device
     */
    public int getId() {
        return id;
    }

    /**
     * <p>
     * This will get the code of the device.
     * </p>
     * 
     * @return code of the device
     */

    public String getCode() {
        return code;
    }

    /**
     * <p>
     * sets the code of the device.
     * </p>
     * 
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * This will get the name of the device.
     * 
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * This will set the name of the device.
     * 
     * @param name
     */

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * This will get the activeflag of the device.
     * 
     * @return 1 if device active, 0 if not active
     */
    public int getActiveFlag() {
        return activeFlag;
    }

    /**
     * this will set the activeflag.
     * 
     * @param activeFlag
     */
    public void setActiveFlag(final int activeFlag) {
        this.activeFlag = activeFlag;
    }

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

    /**
     * this will set id of device.
     * 
     * @param id
     */

    public void setId(final int id) {
        this.id = id;
    }

    /**
     * this will get the updated by value.
     * 
     * @return updated by
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * this will set updated by.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * this will get updated at date and timestamp.
     * 
     * @return updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * this will set updated at date ane time stamp.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
