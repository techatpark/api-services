package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AdminUser {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the fullname.
     */
    private String fullName;
    /**
     * tells the email id.
     */
    private String emailId;
    /**
     * tells the contact number .
     */

    private String contactNumber;
    /**
     * tells the status of customer.
     */
    private Short status;
    /**
     * tells whether is deleted.
     */
    private Short isDeleted;
    /**
     * tells about the account created by who.
     */
    private Integer createdBy;
    /**
     * tells about the account updated by who.
     */
    private Integer updatedBy;

    /**
     * tells the role id.
     */
    private Integer roleId;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    /**gets the id.
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**sets the id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**gets full name.
     * @return String
     */
    public String getFullName() {
        return fullName;
    }

    /**sets full name.
     * @param fullName
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**gets emailid.
     * @return String
     */
    public String getEmailId() {
        return emailId;
    }

    /**sets email id.
     * @param emailId
     */
    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    /**gets contact numkber.
     * @return String
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**sets contact number.
     * @param contactNumber
     */
    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**gets status.
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**sets status.
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**gets isdeleted value.
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**sets isdeleted value.
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**gets created by value.
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**sets created by value.
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**gets updated by value.
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**sets updated by value.
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**gets the role id.
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**sets the role id.
     * @param roleId
     */
    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    /**gets created at value.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**sets created at value.
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
