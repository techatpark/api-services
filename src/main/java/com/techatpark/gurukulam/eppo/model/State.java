package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class State {

    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the state name.
     */
    private String stateName;
    /**
     * tells the state code.
     */
    private String stateCode;
    /**
     * tells the country code.
     */
    private Integer countryId;
    /**
     * tells the status .
     */
    private Integer status;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
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
     * gets status.
     * 
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * sets status.
     * 
     * @param status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * gets created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets updated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * gets state name.
     * 
     * @return String
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * sets state name.
     * 
     * @param stateName
     */
    public void setStateName(final String stateName) {
        this.stateName = stateName;
    }

    /**
     * gets state code.
     * 
     * @return String
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * sets state code.
     * 
     * @param stateCode
     */
    public void setStateCode(final String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * gets country code.
     * 
     * @return Integer
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * sets country code.
     * 
     * @param countryId
     */
    public void setCountryId(final Integer countryId) {
        this.countryId = countryId;
    }
}
