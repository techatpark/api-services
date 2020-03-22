package com.example.demo.tracker.model;

public class Notification extends BaseModel {

    private String refferenceCode;
    private String participantAddress;
    private Intger transactionCount;
    private String requestLog;
    private String responseLog;
    private String notificationMode;

    public String getRefferenceCode() {
        return refferenceCode;
    }

    public void setRefferenceCode(final String refferenceCode) {
        this.refferenceCode = refferenceCode;
    }

    public String getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(final String participantAddress) {
        this.participantAddress = participantAddress;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(final int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(final String requestLog) {
        this.requestLog = requestLog;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(final String responseLog) {
        this.responseLog = responseLog;
    }

    public String getNotificationMode() {
        return notificationMode;
    }

    public void setNotificationMode(final String notificationMode) {
        this.notificationMode = notificationMode;
    }

}