package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class UnitGroup {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the unit group name.
     */
    private String unitGroupName;
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
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the address id.
     */
    private Integer addressId;
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
     * get accountid.
     * 
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * set accountid.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * get addressid.
     * 
     * @return Integer
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * get addressid.
     * 
     * @param addressId
     */
    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
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
     * gets unit group name.
     * 
     * @return String
     */
    public String getUnitGroupName() {
        return unitGroupName;
    }

    /**
     * sets unit group name.
     * 
     * @param unitGroupName
     */
    public void setUnitGroupName(final String unitGroupName) {
        this.unitGroupName = unitGroupName;
    }

}
