package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class UnitRent {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the rent amount.
     */
    private Long rentAmount;
    /**
     * tells the start date.
     */
    private Date rentStartDate;
    /**
     * tells the end date.
     */
    private Date rentEndDate;
    /**
     * tells the unit id.
     */
    private Short unitId;
    /**
     * tells the rent type.
     */
    private String rentType;
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
     * gets the rent amount.
     * 
     * @return Long
     */
    public Long getRentAmount() {
        return rentAmount;
    }

    /**
     * sets the rent amount.
     * 
     * @param rentAmount
     */
    public void setRentAmount(final Long rentAmount) {
        this.rentAmount = rentAmount;
    }

    /**
     * gets the rent start date.
     * 
     * @return Date
     */
    public Date getRentStartDate() {
        return rentStartDate;
    }

    /**
     * sets the rent start date.
     * 
     * @param rentStartDate
     */
    public void setRentStartDate(final Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    /**
     * gets the rent end date.
     * 
     * @return Date
     */
    public Date getRentEndDate() {
        return rentEndDate;
    }

    /**
     * sets the rent end date.
     * 
     * @param rentEndDate
     */
    public void setRentEndDate(final Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    /**
     * gets unit id.
     * 
     * @return Short
     */
    public Short getUnitId() {
        return unitId;
    }

    /**
     * sets unit id.
     * 
     * @param unitId
     */
    public void setUnitId(final Short unitId) {
        this.unitId = unitId;
    }

    /**
     * gets the rent type.
     * 
     * @return String
     */
    public String getRentType() {
        return rentType;
    }

    /**
     * sets the rent type.
     * 
     * @param rentType
     */
    public void setRentType(final String rentType) {
        this.rentType = rentType;
    }

}
