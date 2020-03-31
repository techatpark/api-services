package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class GeoLocationAddressDetails extends BaseModel {
    /**
     * tells the location address code.
     */
    @NotNull
    private String locationAddressCode;
    /**
     * tells the road name.
     */
    @NotNull
    private String road;
    /**
     * tells the area name.
     */
    @NotNull
    private String area;
    /**
     * tells the landmark details.
     */
    @NotNull
    private String landmark;
    /**
     * tells the city.
     */
    @NotNull
    private String city;
    /**
     * tells the state.
     */
    @NotNull
    private String state;
    /**
     * tells the postal code.
     */
    @NotNull
    private String postalCode;

    /**
     * gets the location address code.
     * 
     * @return location address code.
     */
    public String getLocationAddressCode() {
        return locationAddressCode;
    }

    /**
     * sets location address code.
     * 
     * @param locationAddressCode
     */
    public void setLocationAddressCode(final String locationAddressCode) {
        this.locationAddressCode = locationAddressCode;
    }

    /**
     * get the details of road.
     * 
     * @return road
     */
    public String getRoad() {
        return road;
    }

    /**
     * sets the details of road.
     * 
     * @param road
     */
    public void setRoad(final String road) {
        this.road = road;
    }

    /**
     * gets area.
     * 
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * sets the area.
     * 
     * @param area
     */
    public void setArea(final String area) {
        this.area = area;
    }

    /**
     * gets the landmark.
     * 
     * @return landmark
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * sets landmark.
     * 
     * @param landmark
     */
    public void setLandmark(final String landmark) {
        this.landmark = landmark;
    }

    /**
     * gets city.
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * sets city.
     * 
     * @param city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * get state details.
     * 
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * sets state details.
     * 
     * @param state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * get postal code.
     * 
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * sets postal code.
     * 
     * @param postalCode
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

}
