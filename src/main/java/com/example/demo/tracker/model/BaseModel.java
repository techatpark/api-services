package com.example.demo.tracker.model;

import java.util.Date;

/**
 * This is base model.
 */
public class BaseModel {
    /**
     * This is a identitfier.
     */
    private Integer id;
    /**
     * This is string code.
     */
    private String code;

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

    /**
     * This will get the identifier.
     * 
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * This will help as set id.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * This will get code.
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * This will help as set code.
     * 
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

}
