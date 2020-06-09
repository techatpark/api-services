package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AccountContractOffset {
    
    /**
     * tells the is.
     */
    private Integer id;
    /**
     * tells the account code.
     */
    private String accountCode;
    /**
     * tells the contract offset.
     */
    private Integer contractOffset;
    /**
     * tells when it was created.
     */
    private Date createdAt;
    /**
     * tells when updated.
     */
    private Date updatedAt;

    
    /** gets id.
     * @return Integer
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

    /**gets account code.
     * @return String
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**sets accountcode.
     * @param accountCode
     */
    public void setAccountCode(final String accountCode) {
        this.accountCode = accountCode;
    }

    /**gets contractoffset.
     * @return Integer
     */
    public Integer getContractOffset() {
        return contractOffset;
    }

    /**sets contract offset.
     * @param contractOffset
     */
    public void setContractOffset(final Integer contractOffset) {
        this.contractOffset = contractOffset;
    }

    /**gets created at.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**sets created at.
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**gets updated at.
     * @return Date
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

