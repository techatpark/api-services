package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Invoice {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the invoice number.
     */
    private String invoiceNumber;
    /**
     * tells the invoice date.
     */
    private Date invoiceDate;
    /**
     * tells the invoice to party.
     */
    private String invoiceToParty;
    /**
     * tells the invoice to party id.
     */
    private Integer invoiceToPartyId;
    /**
     * tells the amount.
     */
    private Number amount;
    /**
     * tells the transaction id.
     */
    private Integer transactionId;
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
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the uodated at value.
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
     * gets the invoice number.
     * 
     * @return String
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * sets the invoice number.
     * 
     * @param invoiceNumber
     */
    public void setInvoiceNumber(final String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * gets the invoice date.
     * 
     * @return Date
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * sets the invoice date.
     * 
     * @param invoiceDate
     */
    public void setInvoiceDate(final Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * gets th invoice tp party.
     * 
     * @return String
     */
    public String getInvoiceToParty() {
        return invoiceToParty;
    }

    /**
     * sets the invoice to party.
     * 
     * @param invoiceToParty
     */
    public void setInvoiceToParty(final String invoiceToParty) {
        this.invoiceToParty = invoiceToParty;
    }

    /**
     * gets the invoice to party id.
     * 
     * @return Integer
     */
    public Integer getInvoiceToPartyId() {
        return invoiceToPartyId;
    }

    /**
     * sets the invoice to party id.
     * 
     * @param invoiceToPartyId
     */
    public void setInvoiceToPartyId(final Integer invoiceToPartyId) {
        this.invoiceToPartyId = invoiceToPartyId;
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
     * gets the transaction id.
     * 
     * @return Integer
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * sets the transaction id.
     * 
     * @param transactionId
     */
    public void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
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
     * sets the created at vlue.
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
     * sets tha uodated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
