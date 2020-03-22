package com.example.demo.tracker.model;

public class TripStationPoint extends BaseModel {

    /**
     * tells the stationCode.
     */
    private String stationCode;
    /**
     * tells the stationname.
     */
    private String stationName;
    /**
     * tells the latitude of station.
     */
    private float latitude;
    /**
     * tells the longitude of station.
     */
    private float longitude;
    /**
     * tells the expected time of arrival.
     */
    private int expectedTime;
    /**
     * tells the sequence.
     */
    private int sequence;
    /**
     * tells the actual arrival time.
     */
    private int actualTime;
    /**
     * tells the distance .
     */
    private double distance;
    /**
     * tells whether it arrived or not.
     * 
     */
    private boolean arrival;
    /**
     * tells whether it departed or not.
     */
    private boolean departure;
    /**
     * tells how many are boarding from station.
     */
    private int boardingSeatCount;
    /**
     * tells how many getting down in station.
     */
    private int droppingSeatCount;

    /**
     * get station code.
     * 
     * @return station code
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * set station code.
     * 
     * @param stationCode
     */
    public void setStationCode(final String stationCode) {
        this.stationCode = stationCode;
    }

    /**
     * get station name.
     * 
     * @return station name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * set station name.
     * 
     * @param stationName
     */
    public void setStationName(final String stationName) {
        this.stationName = stationName;
    }

    /**
     * get latitude.
     * 
     * @return latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * set latitude.
     * 
     * @param latitude
     */
    public void setLatitude(final float latitude) {
        this.latitude = latitude;
    }

    /**
     * get longitude.
     * 
     * @return longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * set longitude.
     * 
     * @param longitude
     */
    public void setLongitude(final float longitude) {
        this.longitude = longitude;
    }

    /**
     * get expected time.
     * 
     * @return expected time
     */
    public int getExpectedTime() {
        return expectedTime;
    }

    /**
     * sets expected time.
     * 
     * @param expectedTime
     */
    public void setExpectedTime(final int expectedTime) {
        this.expectedTime = expectedTime;
    }

    /**
     * get sequence.
     * 
     * @return sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * set sequence.
     * 
     * @param sequence
     */
    public void setSequence(final int sequence) {
        this.sequence = sequence;
    }

    /**
     * get actual time.
     * 
     * @return actual time
     */
    public int getActualTime() {
        return actualTime;
    }

    /**
     * set actual time.
     * 
     * @param actualTime
     */
    public void setActualTime(final int actualTime) {
        this.actualTime = actualTime;
    }

    /**
     * get distance.
     * 
     * @return diatance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * set distance.
     * 
     * @param distance
     */
    public void setDistance(final double distance) {
        this.distance = distance;
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
     * sets departure confirmation.
     * 
     * @param departure
     */
    public void setDeparture(final boolean departure) {
        this.departure = departure;
    }

    /**
     * get no.of seat boarding.
     * 
     * @return boarding seat count
     */
    public int getBoardingSeatCount() {
        return boardingSeatCount;
    }

    /**
     * set boarding seat count.
     * 
     * @param boardingSeatCount
     */
    public void setBoardingSeatCount(final int boardingSeatCount) {
        this.boardingSeatCount = boardingSeatCount;
    }

    /**
     * get no of dropping.
     * 
     * @return dropping seat count
     */
    public int getDroppingSeatCount() {
        return droppingSeatCount;
    }

    /**
     * set dropping seat count.
     * 
     * @param droppingSeatCount
     */
    public void setDroppingSeatCount(final int droppingSeatCount) {
        this.droppingSeatCount = droppingSeatCount;
    }
}
