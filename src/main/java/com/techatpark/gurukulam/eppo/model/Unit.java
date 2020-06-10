package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Unit {
    /**
     * tells the id .
     */
    private Integer id;
    /**
     * tells the unit identifier.
     */
    private String unitIdentifier;
    /**
     * tells the unit name.
     */
    private String unitName;
    /**
     * tells the building name.
     */
    private String buildingName;
    /**
     * tells the square feet.
     */
    private Number squareFeet;
    /**
     * tells the no of bathrooms.
     */
    private Integer noOfBathrooms;
    /**
     * tells the no of becrooms.
     */
    private Integer noOfBedrooms;
    /**
     * tells other information.
     */
    private String otherInformation;
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
     * tells the unit type id.
     */
    private Integer unitTypeId;
    /**
     * tells the unit group id.
     */
    private Integer unitGroupId;
    /**
     * tells the account id.
     */
    private Integer accountId;
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

    /**
     * gets the UnitIdentifier.
     * 
     * @return String
     */
    public String getUnitIdentifier() {
        return unitIdentifier;
    }

    /**
     * sets the UnitIdentifier.
     * 
     * @param unitIdentifier
     */
    public void setUnitIdentifier(final String unitIdentifier) {
        this.unitIdentifier = unitIdentifier;
    }

    /**
     * gets the UnitName.
     * 
     * @return String
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * sets the UnitName.
     * 
     * @param unitName
     */
    public void setUnitName(final String unitName) {
        this.unitName = unitName;
    }

    /**
     * gets the BuildingName.
     * 
     * @return String
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * sets the BuildingName.
     * 
     * @param buildingName
     */
    public void setBuildingName(final String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * gets Square Feet.
     * 
     * @return Number
     */
    public Number getSquareFeet() {
        return squareFeet;
    }

    /**
     * sets Square Feet.
     * 
     * @param squareFeet
     */
    public void setSquareFeet(final Number squareFeet) {
        this.squareFeet = squareFeet;
    }

    /**
     * gets the No Of Bathrooms.
     * 
     * @return Integer
     */
    public Integer getNoOfBathrooms() {
        return noOfBathrooms;
    }

    /**
     * sets the No Of Bathrooms.
     * 
     * @param noOfBathrooms
     */
    public void setNoOfBathrooms(final Integer noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    /**
     * gets the No Of Bedrooms.
     * 
     * @return Integer
     */
    public Integer getNoOfBedrooms() {
        return noOfBedrooms;
    }

    /**
     * sets the No Of Bedrooms.
     * 
     * @param noOfBedrooms
     */
    public void setNoOfBedrooms(final Integer noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    /**
     * gets the Other Information.
     * 
     * @return String
     */
    public String getOtherInformation() {
        return otherInformation;
    }

    /**
     * sets the Other Information.
     * 
     * @param otherInformation
     */
    public void setOtherInformation(final String otherInformation) {
        this.otherInformation = otherInformation;
    }

    /**
     * gets the Unit Type Id.
     * 
     * @return Integer
     */
    public Integer getUnitTypeId() {
        return unitTypeId;
    }

    /**
     * sets the Unit Type Id.
     * 
     * @param unitTypeId
     */
    public void setUnitTypeId(final Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    /**
     * gets the Unit Group Id.
     * 
     * @return Integer
     */
    public Integer getUnitGroupId() {
        return unitGroupId;
    }

    /**
     * sets the Unit Group Id.
     * 
     * @param unitGroupId
     */
    public void setUnitGroupId(final Integer unitGroupId) {
        this.unitGroupId = unitGroupId;
    }

}
