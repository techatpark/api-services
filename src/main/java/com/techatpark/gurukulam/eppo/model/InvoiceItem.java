package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class InvoiceItem {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the amount.
     */
    private Number amount;
    /**
     * tells the created by value.
     */
    private Integer createdBy;
    /**
     * tells the customer payment id.
     */
    private Integer customerPaymentId;
    /**
     * tells the invoice id.
     */
    private Integer invoiceId;
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
     * tels the created At value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
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
     * gets the amount.
     * 
     * @return Number
     */
    public Number getAmount() {
        return amount;
    }

    /**
     * sets the amount.
     * 
     * @param amount
     */
    public void setAmount(final Number amount) {
        this.amount = amount;
    }

    /**
     * gets the created by value.
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
     * gets the customer payment id.
     * 
     * @return Integer
     */
    public Integer getCustomerPaymentId() {
        return customerPaymentId;
    }

    /**
     * sets the customer payment idea.
     * 
     * @param customerPaymentId
     */
    public void setCustomerPaymentId(final Integer customerPaymentId) {
        this.customerPaymentId = customerPaymentId;
    }

    /**
     * gets the invoice id.
     * 
     * @return Integer
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * sets the invoice id.
     * 
     * @param invoiceId
     */
    public void setInvoiceId(final Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * get updatedby value.
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
     * sets the is deleted value.
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
     * gets the uodated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the uodated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
