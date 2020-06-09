package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class BankAccountVerification {
    /**
     * tells the id.
     */
    private Integer id;

    /**
     * tells the sub dollar amount 1.
     */
    private Number subdollarAmount1;
    /**
     * tells the sub dollar amount 2.
     */
    private Number subdollarAmount2;
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
    private Short isDeleted;
    /**
     * tells the bank account id.
     */
    private Integer bankAccountId;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    
    /** gets the id.
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    
    /** sets the id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    
    /** gets the sub dollar amount 1.
     * @return Number
     */
    public Number getSubdollarAmount1() {
        return subdollarAmount1;
    }

    
    /** sets the sub dollar amount 1.
     * @param subdollarAmount1
     */
    public void setSubdollarAmount1(final Number subdollarAmount1) {
        this.subdollarAmount1 = subdollarAmount1;
    }

    
    /** gets the sub dollar amount 2.
     * @return Number
     */
    public Number getSubdollarAmount2() {
        return subdollarAmount2;
    }

    
    /** sets the sub dollar amount 2.
     * @param subdollarAmount2
     */
    public void setSubdollarAmount2(final Number subdollarAmount2) {
        this.subdollarAmount2 = subdollarAmount2;
    }

    
    /** gets the created by.
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    
    /** sets the created by.
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

    
    /** sets the updated by.
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    
    /** gets is deleted value.
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    
    /** sets the is deleted value.
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    
    /** gets the bank account id.
     * @return Integer
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    
    /** sets the bank account id.
     * @param bankAccountId
     */
    public void setBankAccountId(final Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    
    /** gets created at value.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    
    /** sets the created at value.
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    
    /** gets updated at value.
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    
    /** sets updated at value.
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
