package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AccountCustomer {
    /**
     * teels the id of customer.
     */
    private Integer id;
    /**
     * tells the firstname of customers.
     */
    private String firstName;
    /**
     * tells the middlename of customer.
     */
    private String middleName;
    /**
     * tells the lastname of customer.
     */
    private String lastName;

    /**
     * tells the email id of customer.
     */
    private String emailId;
    /**
     * tells the primary phone number of customer.
     */
    private String primaryPhoneNumber;
    /**
     * tells the secondary phone number of customer.
     */
    private String secondaryPhoneNumber;
    /**
     * tells the date of birth of customer.
     */
    private Date dob;

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
     * tells the account id.
     */
    private Integer accountId;
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
     * @return id
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
     * gets firstname.
     * 
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets firstname.
     * 
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets middlename.
     * 
     * @return middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * sets middle name.
     * 
     * @param middleName
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * gets lastname.
     * 
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets lastname.
     * 
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets email id.
     * 
     * @return email id
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * sets email id.
     * 
     * @param emailId
     */
    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    /**
     * get primaryphonenumber.
     * 
     * @return PrimaryPhoneNumber
     */
    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * sets PrimaryPhoneNumber.
     * 
     * @param primaryPhoneNumber
     */
    public void setPrimaryPhoneNumber(final String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /**
     * gets SecondaryPhoneNumber.
     * 
     * @return SecondaryPhoneNumber
     */
    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    /**
     * sets SecondaryPhoneNumber.
     * 
     * @param secondaryPhoneNumber
     */
    public void setSecondaryPhoneNumber(final String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    /**
     * get date of birth.
     * 
     * @return dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * sets DOB.
     * 
     * @param dob
     */
    public void setDob(final Date dob) {
        this.dob = dob;
    }

    /**
     * gets created by.
     * 
     * @return CreatedBy
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
     * @return updatedBy
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
     * @return isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets is deleted.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * gets status.
     * 
     * @return status
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
     * get accountId.
     * 
     * @return accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * sets addressId.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * gets created at value.
     * 
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets created at.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets updated at value.
     * 
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets updated at.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
