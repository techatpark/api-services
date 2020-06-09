package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Account {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the account code.
     */
    private String accountCode;
    /**
     * tells the account name.
     */
    private String accountName;
    /**
     * tells the phone number.
     */
    private String phoneNo;
    /**
     * tells the company name.
     */
    private String companyName;
    /**
     * tells the email id .
     */
    private String emailId;
    /**
     * tells the tax id .
     */
    private String taxId;

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

    
    /** gets account code.
     * @return String
     */
    public String getAccountCode() {
        return accountCode;
    }

    
    /** sets account code.
     * @param accountCode
     */
    public void setAccountCode(final String accountCode) {
        this.accountCode = accountCode;
    }

    
    /** gets account name.
     * @return String
     */
    public String getAccountName() {
        return accountName;
    }

    
    /** sets account name.
     * @param accountName
     */
    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    
    /** gets phone number.
     * @return String
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    
    /** sets phone number.
     * @param phoneNo
     */
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    
    /** gets company name.
     * @return String
     */
    public String getCompanyName() {
        return companyName;
    }

    
    /** sets company name.
     * @param companyName
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    
    /** gets email id.
     * @return String
     */
    public String getEmailId() {
        return emailId;
    }

    
    /** sets email id.
     * @param emailId
     */
    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    
    /** gets tax id.
     * @return String
     */
    public String getTaxId() {
        return taxId;
    }

    
    /** sets taxid.
     * @param taxId
     */
    public void setTaxId(final String taxId) {
        this.taxId = taxId;
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

    
    /** sets value of is deleted.
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

    
    /** sets dstatus.
     * @param status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    
    /** gets created at value.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    
    /** sets created at value.
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
