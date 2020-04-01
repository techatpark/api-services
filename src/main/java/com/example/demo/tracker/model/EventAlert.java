package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class EventAlert extends BaseModel {
    /**
     * FOREIGN KEY (namespace_id) REFERENCES namespace(id)
     */

    /**
     * tells the namespace id.
     */
    @NotNull
    private Integer namespaceId;
    /**
     * tells the name of event alert.
     */
    @NotNull
    private String name;
    /**
     * tells the vehicle code.
     */
    @NotNull
    private String vehicleCodes;
    /**
     * tells the event type id.
     */
    @NotNull
    private Integer eventTypeId;
    /**
     * tells the alert notification code.
     */
    @NotNull
    private String alertNotificationCodes;
    /**
     * tells the day of the week.
     */
    @NotNull
    private String dayOfWeek;

    /**
     * gets namespace id.
     * 
     * @return namespace id
     */
    public Integer getNamespaceId() {
        return namespaceId;
    }

    /**
     * sets the namespace id.
     * 
     * @param namespaceId
     */
    public void setNamespaceId(final Integer namespaceId) {
        this.namespaceId = namespaceId;
    }

    /**
     * gets the name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets the vehicle code.
     * 
     * @return vehicle code
     */
    public String getVehicleCodes() {
        return vehicleCodes;
    }

    /**
     * sets vehicle codes.
     * 
     * @param vehicleCodes
     */
    public void setVehicleCodes(final String vehicleCodes) {
        this.vehicleCodes = vehicleCodes;
    }

    /**
     * gets event type id.
     * 
     * @return event type id
     */
    public Integer getEventTypeId() {
        return eventTypeId;
    }

    /**
     * sets event type id.
     * 
     * @param eventTypeId
     */
    public void setEventTypeId(final Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    /**
     * gets alert notification code.
     * 
     * @return alert notification code
     */
    public String getAlertNotificationCodes() {
        return alertNotificationCodes;
    }

    /**
     * sets alert notofication code.
     * 
     * @param alertNotificationCodes
     */
    public void setAlertNotificationCodes(final String alertNotificationCodes) {
        this.alertNotificationCodes = alertNotificationCodes;
    }

    /**
     * gets day of the week.
     * 
     * @return day of week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * sets day of week.
     * 
     * @param dayOfWeek
     */
    public void setDayOfWeek(final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
