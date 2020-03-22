package com.example.demo.tracker.model;

public class VehicleMovementSummary {
    /**
     * tells date time.
     */
    private String datetime;
    /**
     * tells the stop.
     */
    private double stop;
    /**
     * tells running time.
     */
    private double running;
    /**
     * tells the halt time.
     */
    private double halt;
    /**
     * tells the speed of vehicle.
     */
    private double speed;
    /**
     * tells the distance covered.
     */
    private double distance;
    /**
     * tells the time.
     */
    private double time;
    /**
     * tells odometer reading.
     */
    private double odometer;

    /**
     * get date time.
     * 
     * @return date time
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * sets date time.
     * 
     * @param datetime
     */
    public void setDatetime(final String datetime) {
        this.datetime = datetime;
    }

    /**
     * gets stop.
     * 
     * @return stop
     */
    public double getStop() {
        return stop;
    }

    /**
     * sets stop.
     * 
     * @param stop
     */
    public void setStop(final double stop) {
        this.stop = stop;
    }

    /**
     * gets running time.
     * 
     * @return running
     */
    public double getRunning() {
        return running;
    }

    /**
     * sets running time.
     * 
     * @param running
     */
    public void setRunning(final double running) {
        this.running = running;
    }

    /**
     * get halt value.
     * 
     * @return halt
     */

    public double getHalt() {
        return halt;
    }

    /**
     * sets halt value.
     * 
     * @param halt
     */
    public void setHalt(final double halt) {
        this.halt = halt;
    }

    /**
     * gets speed.
     * 
     * @return speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * sets speed.
     * 
     * @param speed
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * gets distance.
     * 
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets distance.
     * 
     * @param distance
     */
    public void setDistance(final double distance) {
        this.distance = distance;
    }

    /**
     * gets time.
     * 
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * sets time.
     * 
     * @param time
     */
    public void setTime(final double time) {
        this.time = time;
    }

    /**
     * gets odometer.
     * 
     * @return odometer
     */
    public double getOdometer() {
        return odometer;
    }

    /**
     * sets odometer.
     * 
     * @param odometer
     */
    public void setOdometer(final double odometer) {
        this.odometer = odometer;
    }

}
