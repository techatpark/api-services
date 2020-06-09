package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class ContractCode {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the contract code.
     */
    private String contractCode;
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
     * @return id
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
     * gets the contract code.
     * 
     * @return String
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * setd the contract code.
     * 
     * @param contractCode
     */
    public void setContractCode(final String contractCode) {
        this.contractCode = contractCode;
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
