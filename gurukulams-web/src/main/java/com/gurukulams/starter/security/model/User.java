package com.gurukulams.starter.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

/**
 * The type User.
 */
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
     *
     * @return name. name
     */
    public String getName() {
        return name;
    }

    /**
     * setName.
     *
     * @param namu the namu
     */
    public final void setName(final String namu) {
        this.name = namu;
    }

    /**
     * Gets image url.
     *
     * @return imageUrl. image url
     */
    public final String getImageUrl() {
        return imageUrl;
    }

    /**
     * so sad.
     *
     * @param url the url
     */
    public final void setImageUrl(final String url) {
        this.imageUrl = url;
    }

    /**
     * thalai eluthu.
     *
     * @return password. password
     */
    public final String getPassword() {
        return password;
    }

    /**
     * pwd. abc.
     *
     * @param pwd the pwd
     */
    public final void setPassword(final String pwd) {
        this.password = pwd;
    }

    /**
     * ss.
     *
     * @return provider. provider
     */
    public final AuthProvider getProvider() {
        return provider;
    }

    /**
     * summa.
     *
     * @param provider1 the provider 1
     */
    public final void setProvider(final AuthProvider provider1) {
        this.provider = provider1;
    }

    /**
     * su.
     *
     * @return providerId. provider id
     */
    public final String getProviderId() {
        return providerId;
    }

    /**
     * oa.
     *
     * @param providerI the provider i
     */
    public final void setProviderId(final String providerI) {
        this.providerId = providerI;
    }
}
