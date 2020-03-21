package com.example.demo.tracker.model;

import java.sql.Date;

public class Namespace {
    /**
     * id of the namespace.
     * 
     */
    private Integer id;

    /**
     * code of namespace.
     */
    private String code;

    /**
     * name of namespace.
     */
    private String name;

    /**
     * Tells whether the device is active, when set to 1.
     */
    private Status status;

    /**
     * tells who updated recently.
     */
    private Integer updatedBy;
    /**
     * tells when updated.
     */
    private Date updatedAt;

    /**
     * gets id.
     * 
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * sets id.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * get code.
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * sets code.
     * 
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * get name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * get the updated by data.
     * 
     * @return updated by
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets the updated by.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * gets updated at value.
     * 
     * @return updated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets updated at.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * gets the status.
     * 
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * sets the status.
     * 
     * @param status
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

}
