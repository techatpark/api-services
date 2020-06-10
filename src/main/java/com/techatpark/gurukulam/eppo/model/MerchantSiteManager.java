package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class MerchantSiteManager {

    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the firstname.
     */
    private String firstName;
    /**
     * tells the middlename .
     */
    private String middleName;
    /**
     * tells the lastname.
     */
    private String lastName;
    /**
     * tells the phone number.
     */
    private String phoneNo;

    /**
     * tells the email id .
     */
    private String emailId;
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
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the role id.
     */
    private Integer roleId;

    /**
     * tells the address id.
     */
    private Integer addressId;
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
     * gets firstname.
     * 
     * @return String
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
     * @return String
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * sets middlename.
     * 
     * @param middleName
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * gets lastname.
     * 
     * @return String
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
     * gets phone number.
     * 
     * @return String
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * sets phone number.
     * 
     * @param phoneNo
     */
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * gets email id.
     * 
     * @return String
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
     * get accountid.
     * 
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * set accountid.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * get roleid.
     * 
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * set roleid.
     * 
     * @param roleId
     */
    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * get addressid.
     * 
     * @return Integer
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * get addressid.
     * 
     * @param addressId
     */
    public void setAddressId(final Integer addressId) {
        this.addressId = addressId;
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
}
