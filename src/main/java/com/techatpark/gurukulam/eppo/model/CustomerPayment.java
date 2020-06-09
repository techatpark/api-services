package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class CustomerPayment {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the payment date.
     */
    private Date paymentDate;
    /**
     * tells the amount.
     */
    private Number amount;
    /**
     * tells the smoothing fee.
     */
    private Number smoothingFee;
    /**
     * tells the eppopay commission.
     */
    private Number eppopayCommission;
    /**
     * tells the netamount.
     */
    private Number netAmount;
    /**
     * tells the contract payment id.
     */
    private Integer contractPaymentId;
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
     * tells the active or inactive value.
     */
    private String activeInactive;
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
     * gets the payment date.
     * 
     * @return Date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * sets the payment date.
     * 
     * @param paymentDate
     */
    public void setPaymentDate(final Date paymentDate) {
        this.paymentDate = paymentDate;
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
     * gets the smoothing fees.
     * 
     * @return Number
     */
    public Number getSmoothingFee() {
        return smoothingFee;
    }

    /**
     * sets the smoothing fees.
     * 
     * @param smoothingFee
     */
    public void setSmoothingFee(final Number smoothingFee) {
        this.smoothingFee = smoothingFee;
    }

    /**
     * gets the eppopay commission.
     * 
     * @return Number
     */
    public Number getEppopayCommission() {
        return eppopayCommission;
    }

    /**
     * sets the eppopay commission.
     * 
     * @param eppopayCommission
     */
    public void setEppopayCommission(final Number eppopayCommission) {
        this.eppopayCommission = eppopayCommission;
    }

    /**
     * gets the net amount.
     * 
     * @return Number
     */
    public Number getNetAmount() {
        return netAmount;
    }

    /**
     * sets the net amount.
     * 
     * @param netAmount
     */
    public void setNetAmount(final Number netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * gets the contract payment id.
     * 
     * @return Integer
     */
    public Integer getContractPaymentId() {
        return contractPaymentId;
    }

    /**
     * sets the contract payment id.
     * 
     * @param contractPaymentId
     */
    public void setContractPaymentId(final Integer contractPaymentId) {
        this.contractPaymentId = contractPaymentId;
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
     * gets the isdeleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets the isdeleted value.
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
     * gets the activeinactive value.
     * 
     * @return String
     */
    public String getActiveInactive() {
        return activeInactive;
    }

    /**
     * sets the activeinactive value.
     * 
     * @param activeInactive
     */
    public void setActiveInactive(final String activeInactive) {
        this.activeInactive = activeInactive;
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
