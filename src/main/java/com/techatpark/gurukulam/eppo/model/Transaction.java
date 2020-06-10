package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Transaction {

    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the transaction date.
     */
    private Date transactionDate;
    /**
     * tells the paymenttype.
     */
    private String paymentType;
    /**
     * tells the bank account id.
     */
    private Integer bankAccountId;
    /**
     * tells the amount.
     */
    private Long amount;
    /**
     * tells the payment provider.
     */
    private String paymentProvider;
    /**
     * tells the provider transaction id.
     */
    private Integer providerTransactionId;
    /**
     * tells the provider transaction status.
     */
    private String providerTransactionStatus;
    /**
     * tells the parent transaction id.
     */
    private Integer parentTransactionId;
    /**
     * tells the customer payment id.
     */
    private Integer customerPaymentId;
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
     * gets transaction date.
     * 
     * @return Date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * sets transaction date.
     * 
     * @param transactionDate
     */
    public void setTransactionDate(final Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * gets the payment type.
     * 
     * @return String
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * sets the payment type.
     * 
     * @param paymentType
     */
    public void setPaymentType(final String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * gets bank account id.
     * 
     * @return Integer
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    /**
     * sets bank account id.
     * 
     * @param bankAccountId
     */
    public void setBankAccountId(final Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * gets the amount.
     * 
     * @return Long
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * sets the amount.
     * 
     * @param amount
     */
    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    /**
     * gets the payment provider.
     * 
     * @return String
     */
    public String getPaymentProvider() {
        return paymentProvider;
    }

    /**
     * sets the payment provider.
     * 
     * @param paymentProvider
     */
    public void setPaymentProvider(final String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    /**
     * gets provider transaction id.
     * 
     * @return Integer
     */
    public Integer getProviderTransactionId() {
        return providerTransactionId;
    }

    /**
     * sets provider transaction id.
     * 
     * @param providerTransactionId
     */
    public void setProviderTransactionId(final Integer providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
    }

    /**
     * gets provider transaction status.
     * 
     * @return String
     */
    public String getProviderTransactionStatus() {
        return providerTransactionStatus;
    }

    /**
     * sets provider transaction status.
     * 
     * @param providerTransactionStatus
     */
    public void setProviderTransactionStatus(final String providerTransactionStatus) {
        this.providerTransactionStatus = providerTransactionStatus;
    }

    /**
     * gets parent transaction id.
     * 
     * @return Integer
     */
    public Integer getParentTransactionId() {
        return parentTransactionId;
    }

    /**
     * sets parent transaction id.
     * 
     * @param parentTransactionId
     */
    public void setParentTransactionId(final Integer parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    /**
     * gets customer payment id.
     * 
     * @return Integer
     */
    public Integer getCustomerPaymentId() {
        return customerPaymentId;
    }

    /**
     * sets customer payment id.
     * 
     * @param customerPaymentId
     */
    public void setCustomerPaymentId(final Integer customerPaymentId) {
        this.customerPaymentId = customerPaymentId;
    }

}
