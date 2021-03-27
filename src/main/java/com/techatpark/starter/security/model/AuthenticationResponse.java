package com.techatpark.starter.security.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AuthenticationResponse {

    private final String authToken;
    private final String refreshToken;

    @JsonCreator
    public AuthenticationResponse(@JsonProperty("authToken") final String authToken,
                                  @JsonProperty("refresh_token") final String refreshToken) {
        this.authToken = authToken;
        this.refreshToken = refreshToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
