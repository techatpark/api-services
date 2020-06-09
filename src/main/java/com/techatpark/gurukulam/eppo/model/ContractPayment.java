package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class ContractPayment {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the due number.
     */
    private Short dueNumber;
    /**
     * tells the rent amount.
     */
    private Number rentAmount;
    /**
     * tells the carried over rent amount.
     */
    private Number carriedOverRentAmount;
    /**
     * tells the carried over rent fee.
     */
    private Number carriedOverRentFee;
    /**
     * tells the rent relief amount.
     */
    private Number rentReliefAmount;
    /**
     * tells the adjustment amount.
     */
    private Number adjustmentAmount;
    /**
     * tells the merchant due date.
     */
    private Date merchantDueDate;
    /**
     * tells the net amount.
     */
    private Number netAmount;
    /**
     * tells the eppopay plan dates json.
     */
    private String eppopayPlanDatesJson;
    /**
     * tells the adjusment comments.
     */
    private String adjustmentComment;
    /**
     * tells the contract id.
     */
    private Integer contractId;
    /**
     * tells the eppopay plan id.
     */
    private Integer eppopayPlanId;
    /**
     * tells the created by value.
     */
    private Integer createdBy;
    /**
     * tells the updated by value.
     */
    private Integer updatedBy;
    /**
     * tells the status.
     */
    private Short status;
    /**
     * tells the is deleted value.
     */
    private Short isDeleted;
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
     * gets the due number.
     * 
     * @return Short
     */
    public Short getDueNumber() {
        return dueNumber;
    }

    /**
     * sets the due number.
     * 
     * @param dueNumber
     */
    public void setDueNumber(final Short dueNumber) {
        this.dueNumber = dueNumber;
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
     * gets carried over rent amount.
     * 
     * @return Number
     */
    public Number getCarriedOverRentAmount() {
        return carriedOverRentAmount;
    }

    /**
     * sets the carried over rent amount.
     * 
     * @param carriedOverRentAmount
     */
    public void setCarriedOverRentAmount(final Number carriedOverRentAmount) {
        this.carriedOverRentAmount = carriedOverRentAmount;
    }

    /**
     * gets the carried over rent fee.
     * 
     * @return Number
     */
    public Number getCarriedOverRentFee() {
        return carriedOverRentFee;
    }

    /**
     * sets the carried over rent fee.
     * 
     * @param carriedOverRentFee
     */
    public void setCarriedOverRentFee(final Number carriedOverRentFee) {
        this.carriedOverRentFee = carriedOverRentFee;
    }

    /**
     * gets the rent relief amount.
     * 
     * @return Number
     */
    public Number getRentReliefAmount() {
        return rentReliefAmount;
    }

    /**
     * sets the rent relief amount.
     * 
     * @param rentReliefAmount
     */
    public void setRentReliefAmount(final Number rentReliefAmount) {
        this.rentReliefAmount = rentReliefAmount;
    }

    /**
     * gets the adjustment amount.
     * 
     * @return Number
     */
    public Number getAdjustmentAmount() {
        return adjustmentAmount;
    }

    /**
     * sets the adjustment amount.
     * 
     * @param adjustmentAmount
     */
    public void setAdjustmentAmount(final Number adjustmentAmount) {
        this.adjustmentAmount = adjustmentAmount;
    }

    /**
     * gets the merchant due date.
     * 
     * @return Date
     */
    public Date getMerchantDueDate() {
        return merchantDueDate;
    }

    /**
     * sets the merchant due date.
     * 
     * @param merchantDueDate
     */
    public void setMerchantDueDate(final Date merchantDueDate) {
        this.merchantDueDate = merchantDueDate;
    }

    /**
     * gets net amount.
     * 
     * @return Number
     */
    public Number getNetAmount() {
        return netAmount;
    }

    /**
     * sets net amount.
     * 
     * @param netAmount
     */
    public void setNetAmount(final Number netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * gets eppopay plan dates json.
     * 
     * @return String
     */
    public String getEppopayPlanDatesJson() {
        return eppopayPlanDatesJson;
    }

    /**
     * sets eppopay plan dates json.
     * 
     * @param eppopayPlanDatesJson
     */
    public void setEppopayPlanDatesJson(final String eppopayPlanDatesJson) {
        this.eppopayPlanDatesJson = eppopayPlanDatesJson;
    }

    /**
     * gets the adjustment comment.
     * 
     * @return String
     */
    public String getAdjustmentComment() {
        return adjustmentComment;
    }

    /**
     * sets the adjustment comments.
     * 
     * @param adjustmentComment
     */
    public void setAdjustmentComment(final String adjustmentComment) {
        this.adjustmentComment = adjustmentComment;
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
     * gets the eppopay plan id.
     * 
     * @return Integer
     */
    public Integer getEppopayPlanId() {
        return eppopayPlanId;
    }

    /**
     * sets the eppopay plan id.
     * 
     * @param eppopayPlanId
     */
    public void setEppopayPlanId(final Integer eppopayPlanId) {
        this.eppopayPlanId = eppopayPlanId;
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
     * gets status .
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
     * gets is deleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets the is deleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
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
