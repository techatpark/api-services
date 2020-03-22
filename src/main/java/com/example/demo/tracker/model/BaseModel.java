package com.example.demo.tracker.model;

/**
 * This is base model.
 */
public class BaseModel {
    /**
     * This is a identitfier.
     */
    private Integer id;
    /**
     * This is string code.
     */
    private String code;
    /**
     * This is string name.
     */
    private String name;

    /**
     * This will get the identifier.
     * 
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * This will help as set id.
     * 
     * @param id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * This will get code.
     * 
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * This will help as set code.
     * 
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * This will get name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This will help as set name.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }
}
