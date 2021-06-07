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

    /**
     * declares variable name.
     */
    private String name;
    /**
     * declares variable password.
     */
    private String password;
    /**
     * declares collection of authority.
     */
    private Collection<? extends GrantedAuthority> authorities;
    /**
     * declares map of getAttributes().
     */
    private Map<String, Object> attributes;

    /**
     * Instantiates a new User principal.
     *
     * @param theName        the name
     * @param thePassword    the password
     * @param theAuthorities the authorities
     */
    public UserPrincipal(final String theName, final String thePassword,
                         final Collection<? extends GrantedAuthority>
                                 theAuthorities) {

        this.name = theName;
        this.password = thePassword;
        this.authorities = theAuthorities;
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


    /**
     * gets the password.
     *
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * gets the username.
     *
     * @return username
     */
    @Override
    public String getUsername() {
        return name;
    }

    /**
     * gets the value of accountnotexpired.
     *
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * returns value of isaccountnotlocked.
     *
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * returns value ofiscredentialsnonexpired.
     *
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * returns value of isEnabled.
     *
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * gets the values of authorities.
     *
     * @return authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * gets the getAttributes().
     *
     * @return map of attributes
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Sets getAttributes().
     *
     * @param theAttributes the attributes
     */
    public void setAttributes(final Map<String, Object> theAttributes) {
        this.attributes = theAttributes;
    }

    /**
     * gets the name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }
}
