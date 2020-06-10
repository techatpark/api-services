package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class User {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the az ad id.
     */
    private String azAdId;
    /**
     * tells the role Id.
     */
    private Integer roleId;
    /**
     * tells the user reference id.
     */
    private Integer userRefId;
    /**
     * tells whether is deleted.
     */
    private Integer isDeleted;
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
     * gets the az ad id.
     * 
     * @return String
     */
    public String getAzAdId() {
        return azAdId;
    }

    /**
     * sets the az ad id.
     * 
     * @param azAdId
     */
    public void setAzAdId(final String azAdId) {
        this.azAdId = azAdId;
    }

    /**
     * gets the role id.
     * 
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * sets the role id.
     * 
     * @param roleId
     */
    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * gets user reference id.
     * 
     * @return Integer
     */
    public Integer getUserRefId() {
        return userRefId;
    }

    /**
     * sets user reference id.
     * 
     * @param userRefId
     */
    public void setUserRefId(final Integer userRefId) {
        this.userRefId = userRefId;
    }
}
