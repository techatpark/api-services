package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Address {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     *  tells the address line 1..
     */
    private String address1;
    /**
     *  tells the address line 2.
     */
    private String address2;
    /**
     *  tells the city.
     */
    private String city;
    /**
     *  tells the zip code.
     */
    private String zipCode;
    /**
     *  tells the state id.
     */
    private Integer stateId;
    /**
     *  tells the country id.
     */
    private Integer countryId;
    /**
     *  tells the latitude.
     */
    private Number latitude;
    /**
     *  tells the longitude.
     */
    private Number longitude;
    /**
     * tells the updatedAt. 
     */
    private Date createdAt;
    /**
     * tells the updatedAt.
     */
    private Date updatedAt;
	
    /** gets the id.
     * @return Integer
     */
    public Integer getId() {
		return id;
	}
	
    /** sets the id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    
    /** gets the address line 1.
     * @return String
     */
    public String getAddress1() {
        return address1;
    }

    
    /** sets the address line 1.
     * @param address1
     */
    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    
    /** gets the address line 2.
     * @return String
     */
    public String getAddress2() {
        return address2;
    }

    
    /** sets the address line 2.
     * @param address2
     */
    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    
    /** gets the city.
     * @return String
     */
    public String getCity() {
        return city;
    }

    
    /** sets the city.
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    
    /** gets the zip code.
     * @return String
     */
    public String getZipCode() {
        return zipCode;
    }

    
    /** sets the zip code.
     * @param zipCode
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    
    /** gets the state.
     * @return Integer
     */
    public Integer getStateId() {
        return stateId;
    }

    
    /** sets the state.
     * @param stateId
     */
    public void setStateId(final Integer stateId) {
        this.stateId = stateId;
    }

    
    /** gets country id.
     * @return Integer
     */
    public Integer getCountryId() {
        return countryId;
    }

    
    /** sets country id.
     * @param countryId
     */
    public void setCountryId(final Integer countryId) {
        this.countryId = countryId;
    }

    
    /** gets the latitude value.
     * @return Number
     */
    public Number getLatitude() {
        return latitude;
    }

    
    /** sets the latitude value.
     * @param latitude
     */
    public void setLatitude(final Number latitude) {
        this.latitude = latitude;
    }

    
    /** gets the longitude value.
     * @return Number
     */
    public Number getLongitude() {
        return longitude;
    }

    
    /** sets the longitude value.
     * @param longitude
     */
    public void setLongitude(final Number longitude) {
        this.longitude = longitude;
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

    
    /** sets updated value.
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
		this.updatedAt = updatedAt;
	}
  }
