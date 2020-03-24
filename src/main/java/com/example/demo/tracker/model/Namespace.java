package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class Namespace extends BaseModel {
    /**
     * tells the name of namespace.
     */
    @NotNull
    private String name;

    /**
     * gets the name of namespace.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of namespace.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

}
