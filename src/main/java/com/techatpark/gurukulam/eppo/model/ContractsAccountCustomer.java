package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class ContractsAccountCustomer {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the account customer id.
     */
    private Integer accountCustomerId;
    /**
     * tells the contract id.
     */
    private Integer contractId;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
     */
    private Date updatedAt;

    /**
     * gets the id .
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
     * gets the account customer id.
     * 
     * @return Integer
     */
    public Integer getAccountCustomerId() {
        return accountCustomerId;
    }

    /**
     * sets the account customer id.
     * 
     * @param accountCustomerId
     */
    public void setAccountCustomerId(final Integer accountCustomerId) {
        this.accountCustomerId = accountCustomerId;
    }

    /**
     * gets the contract id.
     * 
     * @return Integer
     */
    public Integer getContractId() {
        return contractId;
    }

    /**
     * sets the contract id.
     * 
     * @param contractId
     */
    public void setContractId(final Integer contractId) {
        this.contractId = contractId;
    }

    /**
     * gets created at value.
     * 
     * @return date
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
     * @return date
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
