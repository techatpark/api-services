package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Log {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the log event.
     */
    private String logEvent;
    /**
     * tells the reference id.
     */
    private Integer referenceId;
    /**
     * tells the usertype.
     */
    private String userType;
    /**
     * tells the user id.
     */
    private Integer userId;
    /**
     * tells the log message.
     */
    private String logMessage;
    /**
     * tells the log data json.
     */
    private String logDataJson;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
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
     * gets the log event.
     * 
     * @return String
     */
    public String getLogEvent() {
        return logEvent;
    }

    /**
     * sets the log event.
     * 
     * @param logEvent
     */
    public void setLogEvent(final String logEvent) {
        this.logEvent = logEvent;
    }

    /**
     * gets reference id.
     * 
     * @return Integer
     */
    public Integer getReferenceId() {
        return referenceId;
    }

    /**
     * sets reference id.
     * 
     * @param referenceId
     */
    public void setReferenceId(final Integer referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * gets the user type.
     * 
     * @return String
     */
    public String getUserType() {
        return userType;
    }

    /**
     * sets the user type.
     * 
     * @param userType
     */
    public void setUserType(final String userType) {
        this.userType = userType;
    }

    /**
     * sets the user id.
     * 
     * @return Integer
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * gets the user id.
     * 
     * @param userId
     */
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    /**
     * gets the log message.
     * 
     * @return String
     */
    public String getLogMessage() {
        return logMessage;
    }

    /**
     * sets the log message.
     * 
     * @param logMessage
     */
    public void setLogMessage(final String logMessage) {
        this.logMessage = logMessage;
    }

    /**
     * gets log data json.
     * 
     * @return String
     */
    public String getLogDataJson() {
        return logDataJson;
    }

    /**
     * sets log data json.
     * 
     * @param logDataJson
     */
    public void setLogDataJson(final String logDataJson) {
        this.logDataJson = logDataJson;
    }

    /**
     * gets the created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets the created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets the updated at value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
