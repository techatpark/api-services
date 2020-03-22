package com.example.demo.tracker.model;

import java.time.LocalDateTime;

public class TripDetails extends BaseModel {
    /**
     * tells the bus type for trip.
     */
    private String busType;
    /**
     * tells the code of schedule for trip.
     */
    private String scheduleCode;
    /**
     * tells the name of schedule for trip.
     */
    private String scheduleName;
    /**
     * tells the service number for trip.
     */
    private String serviceNumber;
    /**
     * tells the driver details for trip.
     */
    private String driverDetails;
    /**
     * tells the from station name.
     */
    private String fromStationName;
    /**
     * tells the name of destination.
     */
    private String toStationName;
    /**
     * tells the trips date and time.
     */
    private LocalDateTime tripDateTime;
    /**
     * tells starting date and time.
     */
    private LocalDateTime originDateTime;
    /**
     * tells the destination date and time.
     */
    private LocalDateTime destinationDateTime;
    /**
     * tells whether its arrived or not.
     */
    private boolean arrival;
    /**
     * tells whether departed or not.
     */
    private boolean departure;
    /**
     * tells the route of trip.
     */
    private String viaStations;

    /**
     * gets bus type.
     * 
     * @return bus type
     */
    public String getBusType() {
        return busType;
    }

    /**
     * sets bus type.
     * 
     * @param busType
     */
    public void setBusType(final String busType) {
        this.busType = busType;
    }

    /**
     * gets the schedule code.
     * 
     * @return schedule code
     */
    public String getScheduleCode() {
        return scheduleCode;
    }

    /**
     * sets the schedule code.
     * 
     * @param scheduleCode
     */
    public void setScheduleCode(final String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    /**
     * gets schedule name.
     * 
     * @return schedule name
     */
    public String getScheduleName() {
        return scheduleName;
    }

    /**
     * sets schedule name.
     * 
     * @param scheduleName
     */
    public void setScheduleName(final String scheduleName) {
        this.scheduleName = scheduleName;
    }

    /**
     * get service number.
     * 
     * @return service number
     */
    public String getServiceNumber() {
        return serviceNumber;
    }

    /**
     * sets service number.
     * 
     * @param serviceNumber
     */
    public void setServiceNumber(final String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    /**
     * get driver details.
     * 
     * @return driver details
     */
    public String getDriverDetails() {
        return driverDetails;
    }

    /**
     * set driver details.
     * 
     * @param driverDetails
     */
    public void setDriverDetails(final String driverDetails) {
        this.driverDetails = driverDetails;
    }

    /**
     * get from station name.
     * 
     * @return from station name
     */
    public String getFromStationName() {
        return fromStationName;
    }

    /**
     * sets from station name.
     * 
     * @param fromStationName
     */
    public void setFromStationName(final String fromStationName) {
        this.fromStationName = fromStationName;
    }

    /**
     * gets to station name.
     * 
     * @return tostation name
     */
    public String getToStationName() {
        return toStationName;
    }

    /**
     * set to station name.
     * 
     * @param toStationName
     */
    public void setToStationName(final String toStationName) {
        this.toStationName = toStationName;
    }

    /**
     * gets trip date and time.
     * 
     * @return trip date time
     */
    public LocalDateTime getTripDateTime() {
        return tripDateTime;
    }

    /**
     * sets trip date and time.
     * 
     * @param tripDateTime
     */
    public void setTripDateTime(final LocalDateTime tripDateTime) {
        this.tripDateTime = tripDateTime;
    }

    /**
     * gets trip starting date and time.
     * 
     * @return get origin date time
     */
    public LocalDateTime getOriginDateTime() {
        return originDateTime;
    }

    /**
     * sets origin date and time.
     * 
     * @param originDateTime
     */
    public void setOriginDateTime(final LocalDateTime originDateTime) {
        this.originDateTime = originDateTime;
    }

    /**
     * gets destination date and time.
     * 
     * @return destination time date
     */
    public LocalDateTime getDestinationDateTime() {
        return destinationDateTime;
    }

    /**
     * sets destination date time.
     * 
     * @param destinationDateTime
     */
    public void setDestinationDateTime(final LocalDateTime destinationDateTime) {
        this.destinationDateTime = destinationDateTime;
    }

    /**
     * gets arrival confirmation.
     * 
     * @return arrival
     */
    public boolean isArrival() {
        return arrival;
    }

    /**
     * sets arrival confirmation.
     * 
     * @param arrival
     */
    public void setArrival(final boolean arrival) {
        this.arrival = arrival;
    }

    /**
     * gets departure confirmation.
     * 
     * @return departure
     */
    public boolean isDeparture() {
        return departure;
    }

    /**
     * sets departure.
     * 
     * @param departure
     */
    public void setDeparture(final boolean departure) {
        this.departure = departure;
    }

    /**
     * gets via station value.
     * 
     * @return via stations
     */
    public String getViaStations() {
        return viaStations;
    }

    /**
     * sets via station.
     * 
     * @param viaStations
     */
    public void setViaStations(final String viaStations) {
        this.viaStations = viaStations;
    }
}
