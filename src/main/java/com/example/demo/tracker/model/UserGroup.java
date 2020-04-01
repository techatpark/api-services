package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;

public class UserGroup extends BaseModel {
    /**
     * tells the name of user group.
     */
    @NotNull
    private String name;
    /**
     * tells the namespace id.
     */
    @NotNull
    private Integer namespaceId;
    /**
     * tells the level.
     */
    @NotNull
    private Integer level;

    /**
     * CONSTRAINT name_UNIQUE UNIQUE(name, namespace_id), FOREIGN KEY (namespace_id)
     * REFERENCES namespace(id)
     */

    /**
     * gets the name of user group.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of user group.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets the namespace id.
     * 
     * @return namespace id
     */
    public Integer getNamespaceId() {
        return namespaceId;
    }

    /**
     * sets the namespace id.
     * 
     * @param namespaceId
     */
    public void setNamespaceId(final Integer namespaceId) {
        this.namespaceId = namespaceId;
    }

    /**
     * gets the level.
     * 
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * sets the level.
     * 
     * @param level
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

}
