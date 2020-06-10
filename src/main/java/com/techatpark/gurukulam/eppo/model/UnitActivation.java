package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class UnitActivation {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the start date.
     */
    private Date startDate;
    /**
     * tells the end date.
     */
    private Date endDate;
    /**
     * tells the active inactive value.
     */
    private String activeInactive;
    /**
     * tells delete.
     */
    private String delete;
    /**
     * tells the unit id.
     */
    private Integer unitId;
    /**
     * tells about the account created by who.
     */
    private Integer createdBy;
    /**
     * tells about the account updated by who.
     */
    private Integer updatedBy;
    /**
     * tells whether is deleted.
     */
    private Integer isDeleted;
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
     * gets created by.
     * 
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * sets created by.
     * 
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets updated by.
     * 
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets updated by.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * gets is deleted value.
     * 
     * @return Integer
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets is deleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Integer isDeleted) {
        this.isDeleted = isDeleted;
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
     * gets the start date.
     * 
     * @return Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * sets the start date.
     * 
     * @param startDate
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * gets the end date.
     * 
     * @return Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * sets the end date.
     * 
     * @param endDate
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * gets active inactive value.
     * 
     * @return String
     */
    public String getActiveInactive() {
        return activeInactive;
    }

    /**
     * sets active inactive value.
     * 
     * @param activeInactive
     */
    public void setActiveInactive(final String activeInactive) {
        this.activeInactive = activeInactive;
    }

    /**
     * gets dlelete.
     * 
     * @return String
     */
    public String getDelete() {
        return delete;
    }

    /**
     * sets delete.
     * 
     * @param delete
     */
    public void setDelete(final String delete) {
        this.delete = delete;
    }

    /**
     * gets unit id.
     * 
     * @return Integer
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * sets unit id.
     * 
     * @param unitId
     */
    public void setUnitId(final Integer unitId) {
        this.unitId = unitId;
    }

}
