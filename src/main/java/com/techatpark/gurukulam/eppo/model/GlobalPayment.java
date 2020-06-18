package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class GlobalPayment {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the payment name.
     */
    private String paymentName;
    /**
     * tells the description.
     */
    private String description;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the created at value.
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
     * gets the payment name.
     * 
     * @return String
     */
    public String getPaymentName() {
        return paymentName;
    }

    /**
     * sets the payment name.
     * 
     * @param paymentName
     */
    public void setPaymentName(final String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * gets the description.
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description.
     * 
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
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
