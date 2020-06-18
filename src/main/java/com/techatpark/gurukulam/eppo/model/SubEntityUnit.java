package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class SubEntityUnit {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the createdby.
     */
    private Integer createdBy;
    /**
     * tells the updated by.
     */
    private Integer updatedBy;
    /**
     * tells the is deleted.
     */
    private Short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the sub entity id.
     */
    private Integer subEntityId;
    /**
     * tells the unit id.
     */
    private Integer unitId;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated by value.
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
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets is deleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
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
     * gets the subentity id.
     * 
     * @return Integer
     */
    public Integer getSubEntityId() {
        return subEntityId;
    }

    /**
     * sets subentity id.
     * 
     * @param subEntityId
     */
    public void setSubEntityId(final Integer subEntityId) {
        this.subEntityId = subEntityId;
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
     * sets the updated at.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
