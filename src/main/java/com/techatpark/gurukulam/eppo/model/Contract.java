package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Contract {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the rent amount.
     */
    private Number rentAmount;
    /**
     * tells the unique account number.
     */
    private String uniqueAccountNumber;
    /**
     * tells the verification pin.
     */
    private String verificationPin;
    /**
     * tells the landlord bank account id.
     */
    private Integer landlordBankAccountId;
    /**
     * tells the customer primary bank account id.
     */
    private Integer customerPrimaryBankAccountId;
    /**
     * tells the customer secondary bank account id.
     */
    private Integer customerSecondaryBankAccountId;
    /**
     * tells the start date.
     */
    private Date startDate;
    /**
     * tells the end date.
     */
    private Date endDate;
    /**
     * tells the month to month details.
     */
    private boolean monthOnMonth;
    /**
     * tells the customer preferred payment plan dates json.
     */
    private String customerPreferredPaymentPlanDatesJson;
    /**
     * tells the pause customer date.
     */
    private Date pauseCustomerDate;
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
    private Short isDeleted;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the account id.
     */
    private Integer accountId;
    /**
     * tells the customer id.
     */
    private Integer customerId;
    /**
     * tells the unit id.
     */
    private Integer unitId;
    /**
     * tells the customer preffered payment plan id.
     */
    private Integer customerPreferredPaymentPlanId;
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
     * gets the rent amount.
     * 
     * @return Number
     */
    public Number getRentAmount() {
        return rentAmount;
    }

    /**
     * sets the rent amount.
     * 
     * @param rentAmount
     */
    public void setRentAmount(final Number rentAmount) {
        this.rentAmount = rentAmount;
    }

    /**
     * gets the unique account number.
     * 
     * @return String
     */
    public String getUniqueAccountNumber() {
        return uniqueAccountNumber;
    }

    /**
     * sets the uniques account number.
     * 
     * @param uniqueAccountNumber
     */
    public void setUniqueAccountNumber(final String uniqueAccountNumber) {
        this.uniqueAccountNumber = uniqueAccountNumber;
    }

    /**
     * gets verifiaction pin.
     * 
     * @return String
     */
    public String getVerificationPin() {
        return verificationPin;
    }

    /**
     * sets verification pin.
     * 
     * @param verificationPin
     */
    public void setVerificationPin(final String verificationPin) {
        this.verificationPin = verificationPin;
    }

    /**
     * gets the landlord bank account id.
     * 
     * @return Integer
     */
    public Integer getLandlordBankAccountId() {
        return landlordBankAccountId;
    }

    /**
     * sets the landlord bank acocunt id.
     * 
     * @param landlordBankAccountId
     */
    public void setLandlordBankAccountId(final Integer landlordBankAccountId) {
        this.landlordBankAccountId = landlordBankAccountId;
    }

    /**
     * gets customer primary bank account id.
     * 
     * @return Integer
     */
    public Integer getCustomerPrimaryBankAccountId() {
        return customerPrimaryBankAccountId;
    }

    /**
     * sets customer primary bank account id.
     * 
     * @param customerPrimaryBankAccountId
     */
    public void setCustomerPrimaryBankAccountId(final Integer customerPrimaryBankAccountId) {
        this.customerPrimaryBankAccountId = customerPrimaryBankAccountId;
    }

    /**
     * gets customer secondary bank account id.
     * 
     * @return Integer
     */
    public Integer getCustomerSecondaryBankAccountId() {
        return customerSecondaryBankAccountId;
    }

    /**
     * set customer secondary bank account id.
     * 
     * @param customerSecondaryBankAccountId
     */
    public void setCustomerSecondaryBankAccountId(final Integer customerSecondaryBankAccountId) {
        this.customerSecondaryBankAccountId = customerSecondaryBankAccountId;
    }

    /**
     * gets the start date.
     * 
     * @return Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * sets the start date.
     * 
     * @param startDate
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * gets the end date.
     * 
     * @return Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * sets the end date.
     * 
     * @param endDate
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * gets the ismonth on month value.
     * 
     * @return boolean
     */
    public boolean isMonthOnMonth() {
        return monthOnMonth;
    }

    /**
     * sets the is month on month value.
     * 
     * @param monthOnMonth
     */
    public void setMonthOnMonth(final boolean monthOnMonth) {
        this.monthOnMonth = monthOnMonth;
    }

    /**
     * gets the Customer Preferred Payment Plan Dates Json.
     * 
     * @return String
     */
    public String getCustomerPreferredPaymentPlanDatesJson() {
        return customerPreferredPaymentPlanDatesJson;
    }

    /**
     * sets the Customer Preferred Payment Plan Dates Json.
     * 
     * @param customerPreferredPaymentPlanDatesJson
     */
    public void setCustomerPreferredPaymentPlanDatesJson(final String customerPreferredPaymentPlanDatesJson) {
        this.customerPreferredPaymentPlanDatesJson = customerPreferredPaymentPlanDatesJson;
    }

    /**
     * gets the pause customer date.
     * 
     * @return Date
     */
    public Date getPauseCustomerDate() {
        return pauseCustomerDate;
    }

    /**
     * sets the pause customer date.
     * 
     * @param pauseCustomerDate
     */
    public void setPauseCustomerDate(final Date pauseCustomerDate) {
        this.pauseCustomerDate = pauseCustomerDate;
    }

    /**
     * gets created by value.
     * 
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * sets created by value.
     * 
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets updated by value.
     * 
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets updated by value.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * gets isdeleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets is deleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * gets the status.
     * 
     * @return Short
     */
    public Short getStatus() {
        return status;
    }

    /**
     * sets the status.
     * 
     * @param status
     */
    public void setStatus(final Short status) {
        this.status = status;
    }

    /**
     * gets account id.
     * 
     * @return Integer
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * sets account id.
     * 
     * @param accountId
     */
    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * gets customer id.
     * 
     * @return Integer
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * sets customer id.
     * 
     * @param customerId
     */
    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * gets unit id.
     * 
     * @return Integer
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * sets unit id.
     * 
     * @param unitId
     */
    public void setUnitId(final Integer unitId) {
        this.unitId = unitId;
    }

    /**
     * gets customer preferred payment plan id.
     * 
     * @return Integer
     */
    public Integer getCustomerPreferredPaymentPlanId() {
        return customerPreferredPaymentPlanId;
    }

    /**
     * sets customer preferred payment plan id.
     * 
     * @param customerPreferredPaymentPlanId
     */
    public void setCustomerPreferredPaymentPlanId(final Integer customerPreferredPaymentPlanId) {
        this.customerPreferredPaymentPlanId = customerPreferredPaymentPlanId;
    }

    /**
     * gets created at value.
     * 
     * @return Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * sets created at value.
     * 
     * @param createdAt
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * gets updated by value.
     * 
     * @return Date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * sets updated at value.
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
