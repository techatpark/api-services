package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class StatusDetail {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the status value.
     */
    private String statusValue;
    /**
     * tells the module name.
     */
    private String moduleName;
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
     * gets the status value.
     * 
     * @return String
     */
    public String getStatusValue() {
        return statusValue;
    }

    /**
     * sets the status value.
     * 
     * @param statusValue
     */
    public void setStatusValue(final String statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * gets the module name.
     * 
     * @return String
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * sets the module name.
     * 
     * @param moduleName
     */
    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }
}
