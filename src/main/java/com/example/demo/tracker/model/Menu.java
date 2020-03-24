package com.example.demo.tracker.model;

public class Menu extends BaseModel {
    /**
     * tells the name of menu.
     */
    private String name;
    /**
     * tells the link to menu.
     */
    private String link;
    /**
     * tells action code of menu.
     */
    private String actionCode;
    /**
     * tells the lookup id.
     */
    private Integer lookupId;
    /**
     * tells the default flag set.
     */
    private Integer defaultFlag;
    /**
     * tells display flag.
     */
    private Integer displayFlag;
    /**
     * tells the product type id.
     */
    private Integer productTypeId;

    /**
     * gets name of menu.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name for menu.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets link for menu.
     * 
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     * sets link for menu.
     * 
     * @param link
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * gets action code.
     * 
     * @return action code
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * sets action code.
     * 
     * @param actionCode
     */
    public void setActionCode(final String actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * gets look up id.
     * 
     * @return lookup id
     */
    public Integer getLookupId() {
        return lookupId;
    }

    /**
     * sets look up id.
     * 
     * @param lookupId
     */
    public void setLookupId(final Integer lookupId) {
        this.lookupId = lookupId;
    }

    /**
     * get default flag.
     * 
     * @return default flag
     */
    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    /**
     * sets default flag.
     * 
     * @param defaultFlag
     */
    public void setDefaultFlag(final Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    /**
     * gets display flag.
     * 
     * @return display flag
     */
    public Integer getDisplayFlag() {
        return displayFlag;
    }

    /**
     * sets diaplay flag.
     * 
     * @param displayFlag
     */
    public void setDisplayFlag(final Integer displayFlag) {
        this.displayFlag = displayFlag;
    }

    /**
     * gets product type id.
     * 
     * @return product type id
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * sets product type id.
     * 
     * @param productTypeId
     */
    public void setProductTypeId(final Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
