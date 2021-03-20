package com.techatpark.starter.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AuthenticationRequest {

    private final String userName;
    private final String password;

    @JsonCreator
    public AuthenticationRequest(@JsonProperty("userName") final String userName, @JsonProperty("password") final String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
