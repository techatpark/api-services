package com.example.demo.tracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class User extends BaseModel {
    /**
     * tells the user name.
     */
    @NotNull
    private String username;
    /**
     * tells the namespace id.
     */
    @NotNull
    private Integer namespaceId;
    /**
     * tells the user group id.
     */
    @Null
    private Integer userGroupId;
    /**
     * tells the authentication zone id.
     */
    @NotNull
    private Integer authenticationZoneId;
    /**
     * tells the token.
     */
    @Null
    private String token;
    /**
     * tells the api token.
     */
    @Null
    private String apiToken;
    /**
     * tells the email of user.
     */
    @NotNull
    private String email;
    /**
     * tells the mobile number of user.
     */
    @Null
    private String mobile;
    /**
     * tells the name of user.
     */
    @NotNull
    private String name;
    /**
     * tells the last name of user.
     */
    @NotNull
    private String lastName;

    /**
     * gets the user name.
     * 
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the user name.
     * 
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
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
     * gets the user group id.
     * 
     * @return user group id
     */
    public Integer getUserGroupId() {
        return userGroupId;
    }

    /**
     * sets the user group id.
     * 
     * @param userGroupId
     */
    public void setUserGroupId(final Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * gets authentication zone id.
     * 
     * @return authentication zone id
     */
    public Integer getAuthenticationZoneId() {
        return authenticationZoneId;
    }

    /**
     * sets authentication zone id.
     * 
     * @param authenticationZoneId
     */
    public void setAuthenticationZoneId(final Integer authenticationZoneId) {
        this.authenticationZoneId = authenticationZoneId;
    }

    /**
     * gets token.
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * sets token.
     * 
     * @param token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * get api token.
     * 
     * @return api token
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * sets api token.
     * 
     * @param apiToken
     */
    public void setApiToken(final String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * gets email.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email.
     * 
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * get mobile of user.
     * 
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * set mobile of user.
     * 
     * @param mobile
     */
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    /**
     * get name of user.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name of user.
     * 
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * gets last name.
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets last name of user.
     * 
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

}

/**
 * CONSTRAINT username_namespace_UNIQUE UNIQUE(username, namespace_id), FOREIGN
 * KEY (namespace_id) REFERENCES namespace(id), FOREIGN KEY (user_group_id)
 * REFERENCES user_group(id)
 */
