package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class AccountFee {

    /**
     * tells the id of customer.
     */
    private Integer id;
    /**
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the ach return fee.
     */
    private Long achReturnFee;
    /**
     * tells when the account created.
     */
    private Date createdAt;
    /**
     * tells when the account updated.
     */
    private Date updatedAt;

    
    /** gets the id.
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    
    /** sets the id.
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**gets the accountId.
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**sets the account id.
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**gets achreturnfee.
     * @return Integer
     */
    public Long getAchReturnFee() {
        return achReturnFee;
    }

    /**sets achreturnfee.
     * @param achReturnFee
     */
    public void setAchReturnFee(final Long achReturnFee) {
        this.achReturnFee = achReturnFee;
    }

    /**gets created at value.
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**sets created at value.
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
