package com.techatpark.gurukulam.eppo.model;

import java.util.Date;

public class Holiday {
    /**
     * tells the id.
     */
    private Integer id;
    /**
     * tells the holidaydate.
     */
    private Date holidayDate;
    /**
     * tells the holiday.
     */
    private String holiday;
    /**
     * tells the is deleted value.
     */
    private short isDeleted;
    /**
     * tells the created by value.
     */
    private Integer createdBy;
    /**
     * tells the updated by value.
     */
    private Integer updatedBy;
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
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * @return Date
     */
    public Date getHolidayDate() {
        return holidayDate;
    }

    /**
     * @param holidayDate
     */
    public void setHolidayDate(final Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    /**
     * @return String
     */
    public String getHoliday() {
        return holiday;
    }

    /**
     * @param holiday
     */
    public void setHoliday(final String holiday) {
        this.holiday = holiday;
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
