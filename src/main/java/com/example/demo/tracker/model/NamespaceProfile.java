package com.example.demo.tracker.model;

public class NamespaceProfile extends BaseModel {
    /**
     * tells the value for over speed.
     */
    private String overSpeed;
    /**
     * tells the domain url.
     */
    private String domainURL;
    /**
     * tells the email notification flag.
     */
    private boolean emailNotificationFlag;
    /**
     * tells the sender mail name.
     */
    private String sendarMailName;
    /**
     * tells the email copy address.
     */
    private String emailCopyAddress;

    /**
     * gets over speed value.
     * 
     * @return overSpeed
     */
    public String getOverSpeed() {
        return overSpeed;
    }

    /**
     * sets the over speed.
     * 
     * @param overSpeed
     */
    public void setOverSpeed(final String overSpeed) {
        this.overSpeed = overSpeed;
    }

    /**
     * gets domain url.
     * 
     * @return domainURL
     */
    public String getDomainURL() {
        return domainURL;
    }

    /**
     * set sdomain url.
     * 
     * @param domainURL
     */
    public void setDomainURL(final String domainURL) {
        this.domainURL = domainURL;
    }

    /**
     * gets the flag value.
     * 
     * @return notification flag.
     */
    public boolean isEmailNotificationFlag() {
        return emailNotificationFlag;
    }

    /**
     * sets email notification flag.
     * 
     * @param emailNotificationFlag
     */
    public void setEmailNotificationFlag(final boolean emailNotificationFlag) {
        this.emailNotificationFlag = emailNotificationFlag;
    }

    /**
     * get sender email name.
     * 
     * @return sender email
     */
    public String getSendarMailName() {
        return sendarMailName;
    }

    /**
     * set sender email name.
     * 
     * @param sendarMailName
     */
    public void setSendarMailName(final String sendarMailName) {
        this.sendarMailName = sendarMailName;
    }

    /**
     * get email copy address.
     * 
     * @return email copy address
     */
    public String getEmailCopyAddress() {
        return emailCopyAddress;
    }

    /**
     * set email copy address.
     * 
     * @param emailCopyAddress
     */
    public void setEmailCopyAddress(final String emailCopyAddress) {
        this.emailCopyAddress = emailCopyAddress;
    }

}
