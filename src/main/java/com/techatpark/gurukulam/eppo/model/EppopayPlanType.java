package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class EppopayPlanType {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the plan type name.
     */
    private String planTypeName;
    /**
     * tells the plan type description.
     */
    private String planTypeDescription;
    /**
     * tells the created at value.
     */
    private Date createdAt;
    /**
     * tells the updates at value.
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
     * gets the plan type name.
     * 
     * @return String
     */
    public String getPlanTypeName() {
        return planTypeName;
    }

    /**
     * sets the plan type name.
     * 
     * @param planTypeName
     */
    public void setPlanTypeName(final String planTypeName) {
        this.planTypeName = planTypeName;
    }

    /**
     * gets the plan type description.
     * 
     * @return String
     */
    public String getPlanTypeDescription() {
        return planTypeDescription;
    }

    /**
     * sets the plan type description.
     * 
     * @param planTypeDescription
     */
    public void setPlanTypeDescription(final String planTypeDescription) {
        this.planTypeDescription = planTypeDescription;
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
