package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AccountCode {

    /**
     * tells the id of customer.
     */
    private Integer id;
    /**
     * tells the account code.
     */
    private String accountCode;
    /**
     * tells the code used.
     */
    private String codeUsed;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    
    /** gets id.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    
    /** sets id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**gets accountcode.
     * @return accountCode
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**sets acocunt code.
     * @param accountCode
     */
    public void setAccountCode(final String accountCode) {
        this.accountCode = accountCode;
    }

    /**gets code used.
     * @return codeUsed
     */
    public String getCodeUsed() {
        return codeUsed;
    }

    /**sets code used.
     * @param codeUsed
     */
    public void setCodeUsed(final String codeUsed) {
        this.codeUsed = codeUsed;
    }

    /**gets created date.
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**sets created at details.
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**gets updated at details.
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**sets updated at.
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
