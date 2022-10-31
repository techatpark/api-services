package com.gurukulams.web.starter.security.security;


import com.gurukulams.core.model.AuthProvider;
import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.LearnerService;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * The type Custom user details service.
 */
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Holds all the application users.
     */
    private final LearnerService learnerService;

    /**
     * Builds the Object.
     *
     * @param passwordEncoder the password encoder
     * @param environment     the environment
     * @param alearnerService
     */
    public CustomUserDetailsService(final PasswordEncoder passwordEncoder,
                                       final Environment environment,
                                       final LearnerService alearnerService) {
        this.learnerService = alearnerService;


        if (Arrays.binarySearch(environment.getActiveProfiles(),
                "prod") < 0 && learnerService.list("System").isEmpty()) {

            learnerService.create("System", new Learner(null, "tom",
                    passwordEncoder.encode("password"),
                    "/images/tom.png", AuthProvider.local, null, null,
                    null, null));

            learnerService.create("System", new Learner(null, "jerry",
                    passwordEncoder.encode("password"),
                    "/images/jerry.png", AuthProvider.local, null, null,
                    null, null));
        }


    }


    /**
     * load userdetails with username.
     *
     * @param email email
     * @return UserDetails user detail
     * @throws UsernameNotFoundException exception
     */
    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        final Learner user = learnerService.readByEmail("System", email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }


}
