package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class RentalLocation {

    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the rental location name.
     */
    private String rentalLocationName;

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
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    /**
     * default constructor.
     */
    public RentalLocation() {
        super();
    }

    /**
     * constructor.
     * 
     * @param id
     * @param locationName
     */
    public RentalLocation(final Integer id, final String locationName) {
        this.id = id;
        this.rentalLocationName = locationName;
    }

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
     * gets the rental location name.
     * 
     * @return String
     */
    public String getRentalLocationName() {
        return rentalLocationName;
    }

    /**
     * sets the rental location name.
     * 
     * @param rentalLocationName
     */
    public void setRentalLocationName(final String rentalLocationName) {
        this.rentalLocationName = rentalLocationName;
    }

    /**
     * gets the account id.
     * 
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * sets the account id.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

}
