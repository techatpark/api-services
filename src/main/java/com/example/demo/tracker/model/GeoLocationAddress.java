package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class GeoLocationAddress extends BaseModel {

    /**
     * CONSTRAINT unx_geo_location_address_1 UNIQUE(latitude, langitude)
     */
    /**
     * tells the latitude of location.
     */
    @NotNull
    private String latitude;
    /**
     * tells longitude of location.
     */
    @NotNull
    private String longitude;
    /**
     * tells the address flag.
     */
    @NotNull
    private Integer addressFlag;
    /**
     * tells the address.
     */
    @Null
    private String address;

    /**
     * gets the latitude of location.
     * 
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * sets the latitude of location.
     * 
     * @param latitude
     */
    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    /**
     * get longitude of location.
     * 
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * sets longitude of location.
     * 
     * @param longitude
     */
    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }

    /**
     * gets the address flag.
     * 
     * @return address flag
     */
    public Integer getAddressFlag() {
        return addressFlag;
    }

    /**
     * sets address flag.
     * 
     * @param addressFlag
     */
    public void setAddressFlag(final Integer addressFlag) {
        this.addressFlag = addressFlag;
    }

    /**
     * gets address.
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address.
     * 
     * @param address
     */
    public void setAddress(final String address) {
        this.address = address;
    }
}
