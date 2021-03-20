package com.techatpark.starter.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AuthenticationResponse {

    private final String authToken;

    @JsonCreator
    public AuthenticationResponse(@JsonProperty("authToken") final String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
