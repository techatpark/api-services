package com.techatpark.starter.security.security.oauth2;

import com.techatpark.starter.security.config.AppProperties;
import com.techatpark.starter.security.exception.BadRequestException;
import com.techatpark.starter.security.security.TokenProvider;
import com.techatpark.starter.security.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.techatpark.starter.security.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

/**
 * The type O auth 2 authentication success handler.
 */
@Component
public class OAuth2AuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * declare a TokenProvider.
     */
    private final TokenProvider tokenProvider;

    /**
     * declare a AppProperties.
     */
    private final AppProperties appProperties;

    /**
     * declare a HttpCookieOAuth2AuthorizationRequestRepository.
     */
    private final HttpCookieOAuth2AuthorizationRequestRepository
            httpCookieOAuth2AuthorizationRequestRepository;


    /**
     * Instantiates a new O auth 2 authentication success handler.
     *
     * @param atokenProvider                                  the token provider
     * @param theappProperties                                the app properties
     * @param ahttpCookieOAuth2AuthorizationRequestRepository the http                                                        cookie o auth 2                                                        authorization                                                        request                                                        repository
     */
    @Autowired
    OAuth2AuthenticationSuccessHandler(final TokenProvider atokenProvider,
                                       final AppProperties theappProperties,
                                       final
    HttpCookieOAuth2AuthorizationRequestRepository
                        ahttpCookieOAuth2AuthorizationRequestRepository) {
        this.tokenProvider = atokenProvider;
        this.appProperties = theappProperties;
        this.httpCookieOAuth2AuthorizationRequestRepository =
                ahttpCookieOAuth2AuthorizationRequestRepository;
    }

    /**
     * overrides the method onAuthenticationSuccess.
     *
     * @param arequest
     * @param aresponse
     * @param anauthentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest arequest,
                                        final HttpServletResponse aresponse,
                                        final Authentication anauthentication)
            throws IOException, ServletException {
        final String targetUrl =
                determineTargetUrl(arequest, aresponse, anauthentication);

        if (aresponse.isCommitted()) {
            logger.debug(
                    "Response has already been committed."
                            + " Unable to redirect to "
                            + targetUrl);
            return;
        }

        clearAuthenticationAttributes(arequest, aresponse);
        getRedirectStrategy().sendRedirect(arequest, aresponse, targetUrl);
    }

    /**
     * determine target url.
     *
     * @param arequest
     * @param aresponse
     * @param anauthentication
     * @return targeturl
     */
    protected String determineTargetUrl(final HttpServletRequest arequest,
                                        final HttpServletResponse aresponse,
                                        final Authentication anauthentication) {
        final Optional<String> redirectUri =
                CookieUtils.getCookie(arequest, REDIRECT_URI_PARAM_COOKIE_NAME)
                        .map(Cookie::getValue);

        if (redirectUri.isPresent()
                && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException(
                    "Sorry! We've got an Unauthorized Redirect URI and"
                            + " can't proceed with the authentication");
        }

        final String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        final String token = tokenProvider.generateToken(anauthentication);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build().toUriString();
    }

    /**
     * Clear authentication attributes.
     *
     * @param arequest  the request
     * @param aresponse the response
     */
    protected void clearAuthenticationAttributes(final HttpServletRequest
                                                         arequest,
                                                 final HttpServletResponse
                                                         aresponse) {
        super.clearAuthenticationAttributes(arequest);
        httpCookieOAuth2AuthorizationRequestRepository
                .removeAuthorizationRequestCookies(arequest, aresponse);
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        final URI clientRedirectUri = URI.create(uri);

        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use
                    // different paths if they want to
                    final URI authorizedURI =
                            URI.create(authorizedRedirectUri);
                    return authorizedURI.getHost()
                            .equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort()
                            == clientRedirectUri.getPort();
                });
    }
}
