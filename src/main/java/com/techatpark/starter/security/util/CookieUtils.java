package com.techatpark.starter.security.util;

import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.SerializationUtils;

final class CookieUtils {

    CookieUtils() {

    }
    /**
     * dash.
     * @param request
     * @param name
     * @return a.
     */
    public static Optional<Cookie> getCookie(
        final HttpServletRequest request,
        final String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }

        return Optional.empty();
    }
/**
 * ssdsd.
 * @param response
 * @param name
 * @param value
 * @param maxAge
 */
    public static void addCookie(
        final HttpServletResponse response,
    final String name,
    final String value,
    final int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
/**
 * dd.
 * @param request
 * @param response
 * @param name
 */
    public static void deleteCookie(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
/**
 * aa.
 * @param object
 * @return nn.
 */
    public static String serialize(final Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }
/**
 * aaa.
 * @param <T>
 * @param cookie
 * @param cls
 * @return mm.
 */
    public static <T> T deserialize(final Cookie cookie, final Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())));
    }


}
