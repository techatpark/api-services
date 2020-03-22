package com.example.demo.tracker.model;

public class Notification extends BaseModel {

     /**
      * mode of notification. ======= this is refference Code.
      */
     private String refferenceCode;
     /**
      * this is the participant Address.
      */
     private String participantAddress;
     /**
      * this is the no. of transactions.
      */
     private Integer transactionCount;
     /**
      * This shows the request log.
      */
     private String requestLog;
     /**
      * This is response log.
      */
     private String responseLog;
     /**
      * This is notification Mode.
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
      * This let's us set participant address.
      * 
      * @param participantAddress
      */
     public void setParticipantAddress(final String participantAddress) {
          this.participantAddress = participantAddress;
     }

     /**
      * gets transaction count.
      * 
      * @return transcation count
      */
     public int getTransactionCount() {
          return transactionCount;
     }

     /**
      * This is to set transaction count.
      * 
      * @param transactionCount
      */
     public void setTransactionCount(final int transactionCount) {
          this.transactionCount = transactionCount;
     }

     /**
      * This is to get the request log.
      * 
      * @return request log
      */
     public String getRequestLog() {
          return requestLog;
     }

     /**
      * This is to set request log.
      * 
      * @param requestLog
      */
     public void setRequestLog(final String requestLog) {
          this.requestLog = requestLog;
     }

     /**
      * This is to get respinse log.
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
      * @return notification mode ======= this is to get Notification Mode.
      */
     public String getNotificationMode() {
          return notificationMode;
     }

     /**
      * to something.
      * 
      * @param notificationMode
      */
     public void setNotificationMode(final String notificationMode) {
          this.notificationMode = notificationMode;
     }

}
