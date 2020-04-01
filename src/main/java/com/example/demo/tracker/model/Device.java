package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Device extends BaseModel {

    /**
     * tells the namespace id.
     */
    @NotNull
    private Integer namespaceId;
    /**
     * tells gsm code.
     */
    @NotNull
    private String gsmCode;
    /**
     * tells device imei code.
     */
    @NotNull
    private String deviceImeiCode;
    /**
     * tells device model id.
     */
    @NotNull
    private Integer deviceModelId;
    /**
     * tells the sensor.
     */
    @Null
    private String sensor;
    /**
     * tells the api flag.
     */
    @NotNull
    private Integer apiFlag;
    /**
     * tells the remarks.
     */
    @Null
    private String remarks;

    /**
     * gets namespace id.
     * 
     * @return namespace id.
     */
    public Integer getNamespaceId() {
        return namespaceId;
    }

    /**
     * sets namespace Id.
     * 
     * @param namespaceId
     */
    public void setNamespaceId(final Integer namespaceId) {
        this.namespaceId = namespaceId;
    }

    /**
     * gets gsm code.
     * 
     * @return gsm code
     */
    public String getGsmCode() {
        return gsmCode;
    }

    /**
     * sets gsm code.
     * 
     * @param gsmCode
     */
    public void setGsmCode(final String gsmCode) {
        this.gsmCode = gsmCode;
    }

    /**
     * gets device Imei code.
     * 
     * @return imei code
     */
    public String getDeviceImeiCode() {
        return deviceImeiCode;
    }

    /**
     * sets device imei code.
     * 
     * @param deviceImeiCode
     */
    public void setDeviceImeiCode(final String deviceImeiCode) {
        this.deviceImeiCode = deviceImeiCode;
    }

    /**
     * get device model id.
     * 
     * @return device model id
     */
    public Integer getDeviceModelId() {
        return deviceModelId;
    }

    /**
     * set device model id.
     * 
     * @param deviceModelId
     */
    public void setDeviceModelId(final Integer deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    /**
     * get sensor details.
     * 
     * @return sensor
     */
    public String getSensor() {
        return sensor;
    }

    /**
     * set sensor details.
     * 
     * @param sensor
     */
    public void setSensor(final String sensor) {
        this.sensor = sensor;
    }

    /**
     * gets api flag.
     * 
     * @return api flag
     */
    public Integer getApiFlag() {
        return apiFlag;
    }

    /**
     * sets api flag.
     * 
     * @param apiFlag
     */
    public void setApiFlag(final Integer apiFlag) {
        this.apiFlag = apiFlag;
    }

    /**
     * gets remarks.
     * 
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * sets remarks.
     * 
     * @param remarks
     */
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * FOREIGN KEY (namespace_id) REFERENCES namespace(id), CONSTRAINT uq_device
     * UNIQUE(namespace_id, device_imei_code), CONSTRAINT uq_device2
     * UNIQUE(namespace_id, gsm_code)
     */

}
