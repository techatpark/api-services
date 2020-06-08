package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AccountCode {

    /**
     * tells the id of the exam.
     */
    private Integer id;

    /**
     * tells the account code.
     */
    @NotNull
    private String accountCode;

    /**
     * tells the code used.
     */
    @NotNull
    private String codeUsed;

    /**
     * tells the time of creation.
     */
    private Date createdAt;

    /**
     * tells when it is updated.
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
     * get account code.
     * 
     * @return accountCode
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * sets account code.
     * 
     * @param accountCode
     */
    public void setAccountCode(final String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * gets the code used.
     * 
     * @return codeUsed
     */
    public String getCodeUsed() {
        return codeUsed;
    }

    /**
     * sets the code used.
     * 
     * @param codeUsed
     */
    public void setCodeUsed(final String codeUsed) {
        this.codeUsed = codeUsed;
    }

    /**
     * gets the created at details.
     * 
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets the created at.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets the updatedated at details.
     * 
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets the updated at details.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
