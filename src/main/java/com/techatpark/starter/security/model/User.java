package com.techatpark.starter.security.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    /**
     * name.
     */
    private String name;
    /**
     * imageUrl.
     */
    private String imageUrl;
    /**
     * password.
     */
    @JsonIgnore
    private String password;
    /**
     * authProvider.
     */
    @NotNull
    private AuthProvider provider;
    /**
     * providerId.
     */
    private String providerId;

    /**
     * getName.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * setName.
     * @param namu
     */
    public final void setName(final String namu) {
        this.name = namu;
    }

    /**
     * @return imageUrl.
     */
    public final String getImageUrl() {
        return imageUrl;
    }

    /**
     * so sad.
     * @param url
     */
    public final void setImageUrl(final String url) {
        this.imageUrl = url;
    }

    /**
     * thalai eluthu.
     * @return password.
     */
    public final String getPassword() {
        return password;
    }

    /**
     * pwd. abc.
     * @param pwd
     */
    public final void setPassword(final String pwd) {
        this.password = pwd;
    }

    /**
     * ss.
     * @return provider.
     */
    public final AuthProvider getProvider() {
        return provider;
    }

    /**
     * summa.
     * @param provider1
     */
    public final void setProvider(final AuthProvider provider1) {
        this.provider = provider1;
    }

    /**
     * su.
     * @return providerId.
     */
    public final String getProviderId() {
        return providerId;
    }

    /**
     * oa.
     * @param providerI
     */
    public final void setProviderId(final String providerI) {
        this.providerId = providerI;
    }
}
