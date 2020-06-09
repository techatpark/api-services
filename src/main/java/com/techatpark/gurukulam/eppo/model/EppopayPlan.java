package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class EppopayPlan {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the plan name.
     */
    private String planName;
    /**
     * tells the frequency.
     */
    private String frequency;
    /**
     * tells the rule json.
     */
    private String ruleJson;
    /**
     * tells the fees json.
     */
    private String feesJson;
    /**
     * tells the plan type.
     */
    private String planType;
    /**
     * tells the eppopay fee json.
     */
    private String eppopayFeeJson;
    /**
     * tells the approval required.
     */
    private Short approvalRequired;
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
     * tells the eppopay plan type id.
     */
    private Integer eppopayPlanTypeId;
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
     * gets the plan name.
     * 
     * @return String
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * sets the plan name.
     * 
     * @param planName
     */
    public void setPlanName(final String planName) {
        this.planName = planName;
    }

    /**
     * gets the frequency.
     * 
     * @return String
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * sets the frequency.
     * 
     * @param frequency
     */
    public void setFrequency(final String frequency) {
        this.frequency = frequency;
    }

    /**
     * gets the rule json.
     * 
     * @return String
     */
    public String getRuleJson() {
        return ruleJson;
    }

    /**
     * sets the rule json.
     * 
     * @param ruleJson
     */
    public void setRuleJson(final String ruleJson) {
        this.ruleJson = ruleJson;
    }

    /**
     * gets the fees json.
     * 
     * @return String
     */
    public String getFeesJson() {
        return feesJson;
    }

    /**
     * sets the fees json.
     * 
     * @param feesJson
     */
    public void setFeesJson(final String feesJson) {
        this.feesJson = feesJson;
    }

    /**
     * gets the plan type.
     * 
     * @return String
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * sets the plan type .
     * 
     * @param planType
     */
    public void setPlanType(final String planType) {
        this.planType = planType;
    }

    /**
     * gets the eppopay fee json.
     * 
     * @return String
     */
    public String getEppopayFeeJson() {
        return eppopayFeeJson;
    }

    /**
     * sets the eppopay fee json.
     * 
     * @param eppopayFeeJson
     */
    public void setEppopayFeeJson(final String eppopayFeeJson) {
        this.eppopayFeeJson = eppopayFeeJson;
    }

    /**
     * gets the approval required.
     * 
     * @return Short
     */
    public Short getApprovalRequired() {
        return approvalRequired;
    }

    /**
     * sets the approval required.
     * 
     * @param approvalRequired
     */
    public void setApprovalRequired(final Short approvalRequired) {
        this.approvalRequired = approvalRequired;
    }

    /**
     * gets the created by value.
     * 
     * @return Integer
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * sets the created by value.
     * 
     * @param createdBy
     */
    public void setCreatedBy(final Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * gets the updated by value.
     * 
     * @return Integer
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * sets the updated by value.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(final Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * gets the is deleted value.
     * 
     * @return Short
     */
    public Short getIsDeleted() {
        return isDeleted;
    }

    /**
     * sets the isdeleted value.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(final Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * gets the eppopay plan type id.
     * 
     * @return Integer
     */
    public Integer getEppopayPlanTypeId() {
        return eppopayPlanTypeId;
    }

    /**
     * sets the eppopay plan type id.
     * 
     * @param eppopayPlanTypeId
     */
    public void setEppopayPlanTypeId(final Integer eppopayPlanTypeId) {
        this.eppopayPlanTypeId = eppopayPlanTypeId;
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
