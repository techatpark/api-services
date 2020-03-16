package com.example.demo.tracker.model;

/**
 * this model defines device
 */
public class Device {
    private Long id;
    private String code;
    private String imeicode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImeicode() {
        return imeicode;
    }

    public void setImeicode(String imeicode) {
        this.imeicode = imeicode;
    }
}