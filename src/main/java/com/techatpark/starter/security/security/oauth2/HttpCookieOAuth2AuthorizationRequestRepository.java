package com.techatpark.starter.security.security.oauth2;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.techatpark.starter.security.util.CookieUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Http cookie o auth 2 authorization request repository.
 */
@Component
public class HttpCookieOAuth2AuthorizationRequestRepository
        implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    /**
     * The constant OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME.
     */
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME =
            "oauth2_auth_request";
    /**
     * The constant REDIRECT_URI_PARAM_COOKIE_NAME.
     */
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int cookieExpireSeconds = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(
            final HttpServletRequest request) {
        return CookieUtils
                .getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtils
                        .deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(
            final OAuth2AuthorizationRequest authorizationRequest,
            final HttpServletRequest request, final HttpServletResponse response) {
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response,
                    OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response,
                    REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }

        CookieUtils
                .addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
                        CookieUtils.serialize(authorizationRequest),
                        cookieExpireSeconds);
        final String redirectUriAfterLogin =
                request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME,
                    redirectUriAfterLogin, cookieExpireSeconds);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(
            final HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    /**
     * Remove authorization request cookies.
     *
     * @param request  the request
     * @param response the response
     */
    public void removeAuthorizationRequestCookies(final HttpServletRequest request,
                                                  final HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response,
                OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtils.deleteCookie(request, response,
                REDIRECT_URI_PARAM_COOKIE_NAME);
    }
}
