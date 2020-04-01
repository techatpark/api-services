package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class AlertNotification extends BaseModel {
    /**
     * FOREIGN KEY (namespace_id) REFERENCES namespace(id)
     */

    /**
     * tells the namespaceid.
     */
    @NotNull
    private Integer namespaceId;
    /**
     * tells the name of alert notofication.
     */
    @NotNull
    private String name;
    /**
     * tells the mobile number.
     */
    @NotNull
    private String mobileNumbers;
    /**
     * tells the reference code id.
     */
    @NotNull
    private String referenceCodes;

    /**
     * gets namespaceid.
     * 
     * @return namespace id
     */
    public Integer getNamespaceId() {
        return namespaceId;
    }

    /**
     * sets namaespace id.
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
     * gets mobile number.
     * 
     * @return mobile number
     */
    public String getMobileNumbers() {
        return mobileNumbers;
    }

    /**
     * sets mobile number.
     * 
     * @param mobileNumbers
     */
    public void setMobileNumbers(final String mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    /**
     * gets reference code.
     * 
     * @return reference code
     */
    public String getReferenceCodes() {
        return referenceCodes;
    }

    /**
     * set reference code.
     * 
     * @param referenceCodes
     */
    public void setReferenceCodes(final String referenceCodes) {
        this.referenceCodes = referenceCodes;
    }

}
