package com.gurukulams.web.starter.security.security;


import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.LearnerService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {


    /**
     * PasswordEncoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Holds all the application users.
     */
    private final LearnerService learnerService;

    /**
     * Builds the Object.
     *
     * @param alearnerService
     */
    public CustomUserDetailsService(final LearnerService alearnerService) {
        passwordEncoder = new BCryptPasswordEncoder();
        this.learnerService = alearnerService;
    }

    /**
     * passwordEncoder.
     * @return passwordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

    /**
     * authenticationManager.
     * @param config
     * @return authenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager
                            authenticationManager(final
               AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Aithe Provide.
     * @return authenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
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
