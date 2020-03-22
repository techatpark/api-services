package com.example.demo.tracker.model;

public class Notification extends BaseModel {
    /**
     * refference code of notification.
     */
    private String refferenceCode;
    /**
     * address of participant.
     */
    private String participantAddress;
    /**
     * count of transactions.
     */
    private Integer transactionCount;
    /**
     * tells the request log.
     */
    private String requestLog;
    /**
     * tells response log.
     */
    private String responseLog;
    /**
     * mode of notification.
     */
    private String notificationMode;

    /**
     * gets refference code.
     * 
     * @return refference code
     */
    public String getRefferenceCode() {
        return refferenceCode;
    }

    /**
     * sets refference code.
     * 
     * @param refferenceCode
     */
    public void setRefferenceCode(final String refferenceCode) {
        this.refferenceCode = refferenceCode;
    }

    /**
     * gets participant address.
     * 
     * @return participant address
     */
    public String getParticipantAddress() {
        return participantAddress;
    }

    /**
     * sets participant address.
     * 
     * @param participantAddress
     */
    public void setParticipantAddress(final String participantAddress) {
        this.participantAddress = participantAddress;
    }

    /**
     * gets transaction count.
     * 
     * @return transaction count
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * sets transaction count.
     * 
     * @param transactionCount
     */
    public void setTransactionCount(final int transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * gets the request log.
     * 
     * @return request log
     */
    public String getRequestLog() {
        return requestLog;
    }

    /**
     * sets request log.
     * 
     * @param requestLog
     */
    public void setRequestLog(final String requestLog) {
        this.requestLog = requestLog;
    }

    /**
     * gets response log.
     * 
     * @return response log
     */
    public String getResponseLog() {
        return responseLog;
    }

    /**
     * sets response log.
     * 
     * @param responseLog
     */
    public void setResponseLog(final String responseLog) {
        this.responseLog = responseLog;
    }

    /**
     * gets the notification mode.
     * 
     * @return notification mode
     */
    public String getNotificationMode() {
        return notificationMode;
    }

    /**
     * sets notification mode.
     * 
     * @param notificationMode
     */
    public void setNotificationMode(final String notificationMode) {
        this.notificationMode = notificationMode;
    }

}
