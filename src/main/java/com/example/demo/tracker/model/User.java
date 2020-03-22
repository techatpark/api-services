package com.example.demo.tracker.model;

public class User extends BaseModel {
    /**
     * tells user name.
     */
    private String username;
    /**
     * tells user email.
     */
    private String email;
    /**
     * tells user mobile no.
     */
    private String mobile;
    /**
     * tells user last name.
     */
    private String lastname;
    /**
     * tells token.
     */
    private String token;
    /**
     * tells api token.
     */
    private String apiToken;
    /**
     * tells the password of user.
     */
    private String password;

    /**
     * gets user name.
     * 
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets user name.
     * 
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * gets email of user.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email of user.
     * 
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * gets mobile number.
     * 
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * sets mobile number.
     * 
     * @param mobile
     */
    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    /**
     * gets last name of user.
     * 
     * @return last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * sets last name.
     * 
     * @param lastname
     */
    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    /**
     * gets the token.
     * 
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * sets the token.
     * 
     * @param token
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * gets api token.
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
     * gets password.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password.
     * 
     * @param password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

}
