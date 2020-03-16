package com.example.demo.tracker.model;

/**
 * this model defines device
 */
public class Device {

    private int id;
	private String code;
	private String name;
	private int activeFlag;
    private String gsmCode;
	private String deviceIMEICode;
	// 1111 Engine,AC,Diesel,vibrationACC
	private String sensor;
	private String remarks;
    private int apiFlag;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getGsmCode() {
        return gsmCode;
    }

    public void setGsmCode(String gsmCode) {
        this.gsmCode = gsmCode;
    }

    public String getDeviceIMEICode() {
        return deviceIMEICode;
    }

    public void setDeviceIMEICode(String deviceIMEICode) {
        this.deviceIMEICode = deviceIMEICode;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getApiFlag() {
        return apiFlag;
    }

    public void setApiFlag(int apiFlag) {
        this.apiFlag = apiFlag;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}