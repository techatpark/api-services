package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class SubEntity {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the sub entity name.
     */
    private String subEntityName;
    /**
     * tells the created by value.
     */
    private Integer createdBy;
    /**
     * tells the updated by value.
     */
    private Integer updatedBy;
    /**
     * tells the is deleted value.
     */
    private short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the legal state code.
     */
    private String legalStateCode;
    /**
     * tells the default.
     */
    private String isDefault;
    /**
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the bank account id.
     */
    private Integer bankAccountId;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updated at value.
     */
    private Date updatedAt;

    /**
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getSubEntityName() {
        return subEntityName;
    }

    /**
     * @param subEntityName
     */
    public void setSubEntityName(final String subEntityName) {
        this.subEntityName = subEntityName;
    }

    /**
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return short
     */
    public short getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(final short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**
     * @return String
     */
    public String getLegalStateCode() {
        return legalStateCode;
    }

    /**
     * @param legalStateCode
     */
    public void setLegalStateCode(final String legalStateCode) {
        this.legalStateCode = legalStateCode;
    }

    /**
     * @return String
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(final String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return Integer
     */
    public Integer getBankAccountId() {
        return bankAccountId;
    }

    /**
     * @param bankAccountId
     */
    public void setBankAccountId(final Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
