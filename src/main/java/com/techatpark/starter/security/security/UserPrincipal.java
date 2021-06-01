package com.techatpark.starter.security.security;

import com.techatpark.starter.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type User principal.
 */
public class UserPrincipal implements OAuth2User, UserDetails {

    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    /**
     * Instantiates a new User principal.
     *
     * @param name        the name
     * @param password    the password
     * @param authorities the authorities
     */
    public UserPrincipal(final String name, final String password,
                         final Collection<? extends GrantedAuthority> authorities) {

        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Create user principal.
     *
     * @param user the user
     * @return the user principal
     */
    public static UserPrincipal create(final User user) {
        final List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(

                user.getName(),
                user.getPassword(),
                authorities
        );
    }

    /**
     * Create user principal.
     *
     * @param user       the user
     * @param attributes the attributes
     * @return the user principal
     */
    public static UserPrincipal create(final User user,
                                       final Map<String, Object> attributes) {
        final UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }
}
