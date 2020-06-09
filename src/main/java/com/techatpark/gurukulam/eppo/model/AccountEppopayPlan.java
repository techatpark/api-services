package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AccountEppopayPlan {
    
 /**
     * tells the id of customer.
     */
    private Integer id;

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
     * tells the status of customer.
     */
    private Integer status;
    /**
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the eppopay plan id.
     */
    private Integer eppopayPlanId;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    
    /** gets id.
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    
    /** sets id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    
    /** gets created by.
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    
    /** sets created by.
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    
    /** gets updated by.
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    
    /** sets updated by.
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    
    /** gets is deleted value.
     * @return Integer
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    
    /** sets is deleted value.
     * @param isDeleted
     */
    public void setIsDeleted(final Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    
    /** gets status.
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }

    
    /** sets status.
     * @param status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    
    /** gets accountid.
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    
    /** sets account id.
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    
    /** gets eppopay plan id.
     * @return Integer
     */
    public Integer getEppopayPlanId() {
        return eppopayPlanId;
    }

    
    /** sets eppopay plan id.
     * @param eppopayPlanId
     */
    public void setEppopayPlanId(final Integer eppopayPlanId) {
        this.eppopayPlanId = eppopayPlanId;
    }

    
    /** gets created at.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    
    /** sets created at.
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    
    /** gets apdated at.
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    
    /** sets updated at.
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
