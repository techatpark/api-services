package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class SubEntityPlan {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the created by value.
     */
    private Integer createdBy;
    /**
     * tells the updated by value.
     */
    private Integer updatedBy;
    /**
     * tells the is deleted value.
     */
    private short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the sub entity id.
     */
    private Integer subEntityId;
    /**
     * tells the eppopay plan id.
     */
    private Integer eppopayPlanId;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
     */
    private Date updatedAt;

    /**
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return short
     */
    public short getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(final short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**
     * @return Integer
     */
    public Integer getSubEntityId() {
        return subEntityId;
    }

    /**
     * @param subEntityId
     */
    public void setSubEntityId(final Integer subEntityId) {
        this.subEntityId = subEntityId;
    }

    /**
     * @return Integer
     */
    public Integer getEppopayPlanId() {
        return eppopayPlanId;
    }

    /**
     * @param eppopayPlanId
     */
    public void setEppopayPlanId(final Integer eppopayPlanId) {
        this.eppopayPlanId = eppopayPlanId;
    }

    /**
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
