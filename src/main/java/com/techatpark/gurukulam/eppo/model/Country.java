package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Country {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the country name.
     */
    private String countryName;
    /**
     * tells the two digit code.
     */
    private String twoDigitCode;
    /**
     * tells the three digit code.
     */
    private String threeDigitCode;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated value.
     */
    private Date updatedAt;

    /**
     * gets the id.
     * 
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets the id.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * gets the country name.
     * 
     * @return String
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * sets the country name.
     * 
     * @param countryName
     */
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    /**
     * gets the two digit code.
     * 
     * @return String
     */
    public String getTwoDigitCode() {
        return twoDigitCode;
    }

    /**
     * sets the two digit code.
     * 
     * @param twoDigitCode
     */
    public void setTwoDigitCode(final String twoDigitCode) {
        this.twoDigitCode = twoDigitCode;
    }

    /**
     * gets the three digit code.
     * 
     * @return String
     */
    public String getThreeDigitCode() {
        return threeDigitCode;
    }

    /**
     * sets the three digit code.
     * 
     * @param threeDigitCode
     */
    public void setThreeDigitCode(final String threeDigitCode) {
        this.threeDigitCode = threeDigitCode;
    }

    /**
     * gets the status.
     * 
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**
     * sets the status.
     * 
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**
     * gets the created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets the created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets the updated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
