package com.gurukulams.starter.security.security;


import com.gurukulams.starter.security.model.User;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Holds all the application users.
     */
    private final List<User> users;

    /**
     * Builds the Object.
     *
     * @param passwordEncoder the password encoder
     * @param environment the environment
     */
    public CustomUserDetailsService(final PasswordEncoder passwordEncoder,
                                    final Environment environment) {
        this.users = new ArrayList<>();

        if (Arrays.binarySearch(environment.getActiveProfiles(),
                "prod") == -1) {
            User user = new User();
            user.setName("tom");
            user.setPassword(passwordEncoder.encode("password"));
            user.setImageUrl("/images/" + user.getName() + ".png");

            users.add(user);

            user = new User();
            user.setName("jerry");
            user.setPassword(passwordEncoder.encode("password"));
            user.setImageUrl("/images/" + user.getName() + ".png");
            users.add(user);
        }


    }

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<User> findByName(final String name) {
        return this.users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    /**
     * Exists by name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public Boolean existsByName(final String name) {
        return this.users.stream()
                .anyMatch(user -> user.getName().equals(name));
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    public User save(final User user) {
        users.add(user);
        return user;
    }

    /**
     * load userdetails with username.
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        final User user = findByName(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }


}
