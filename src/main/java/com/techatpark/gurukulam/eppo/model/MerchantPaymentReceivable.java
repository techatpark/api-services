package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class MerchantPaymentReceivable {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the day of month.
     */
    private String dayOfMonth;
    /**
     * tells the percentage receivable.
     */
    private Number percentReceivable;
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
    private Short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the accountid.
     */
    private Integer accountId;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
     */
    private Date updatedAt;

    /**
     * gets the id .
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
     * gets the day of month.
     * 
     * @return String
     */
    public String getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * sets the day of month.
     * 
     * @param dayOfMonth
     */
    public void setDayOfMonth(final String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * gets the percentage receivable.
     * 
     * @return Number
     */
    public Number getPercentReceivable() {
        return percentReceivable;
    }

    /**
     * sets the percentage receivable.
     * 
     * @param percentReceivable
     */
    public void setPercentReceivable(final Number percentReceivable) {
        this.percentReceivable = percentReceivable;
    }

    /**
     * gets created by value.
     * 
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * sets the created by value.
     * 
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets the updated by value.
     * 
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets the updated by value.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * gets the is deleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets the is deleted by value.
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
     * gets the account id.
     * 
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * sets the account id.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * gets the created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets the created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets the updated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
