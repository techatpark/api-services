package com.gurukulams.starter.security.security;

import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.LearnerService;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Holds all the application users.
     */
    private final LearnerService learnerService;

    /**
     * Builds the Object.
     * @param passwordEncoder the password encoder
     * @param environment the environment
     * @param alearnerService the a learner Service
     */
    public CustomUserDetailsService(final PasswordEncoder passwordEncoder,
                                    final Environment environment,
                                    final LearnerService alearnerService) {
        this.learnerService = alearnerService;

        if (Arrays.binarySearch(environment.getActiveProfiles(),
                "prod") < 0) {
            learnerService.create(new Learner(null, "tom", "tom@email.com",
                    "/images/tom.png", passwordEncoder.encode("password"),
                    "local", "local", "Tom", null, null));

            learnerService.create(new Learner(null, "jerry", "jerry@email.com",
                    "/images/jerry.png", passwordEncoder.encode("password"),
                    "local",  "local", "Jerry", null, null));

        }


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
        final Learner learner = learnerService.readByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email : " + email)
                );

        return UserPrincipal.create(learner);
    }


}
