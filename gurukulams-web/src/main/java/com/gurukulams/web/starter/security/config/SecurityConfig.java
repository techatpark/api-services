package com.gurukulams.web.starter.security.config;

import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.gurukulams.core.service.LearnerService;
import com.gurukulams.web.starter.security.security.CustomUserDetailsService;
import com.gurukulams.web.starter.security.security.RestAuthenticationEntryPoint;
import com.gurukulams.web.starter.security.security.TokenAuthenticationFilter;
import com.gurukulams.web.starter.security.security.TokenProvider;
import com.gurukulams.web.starter.security.security.oauth2.CustomOAuth2UserService;
import com.gurukulams.web.starter.security.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.gurukulams.web.starter.security.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.gurukulams.web.starter.security.security.oauth2.OAuth2AuthenticationSuccessHandler;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
        jsr250Enabled = true, prePostEnabled = true)
@EnableConfigurationProperties(AppProperties.class)
public class SecurityConfig {

        /**
         * PasswordEncoder.
         */
        private final PasswordEncoder passwordEncoder;
        /**
         * Learner Service.
         */
        private final LearnerService learnerService;

        /**
         * inject the customUserDetailsService object dependency.
         */
        private final CustomUserDetailsService customUserDetailsService;

        /**
         * inject the customOAuth2UserService object dependency.
         */
        private final CustomOAuth2UserService customOAuth2UserService;

        /**
         * TokenAuthenticationFilter.
         */
        private final TokenAuthenticationFilter tokenAuthenticationFilter;

        /**
         * inject the oAuth2AuthenticationSuccessHandler object dependency.
         */
        private final OAuth2AuthenticationSuccessHandler
                oAuth2AuthenticationSuccessHandler;

        /**
         * By default, Spring OAuth2 uses
         * HttpSessionOAuth2AuthorizationRequestRepository to save.
         * <p>
         * the authorization request. But, since our service is stateless,
         * we can't save it in
         * the session. We'll save the request in a
         * Base64 encoded cookie instead.
         */
        private final HttpCookieOAuth2AuthorizationRequestRepository
                cookieAuthorizationRequestRepository;

        /**
         * inject the oAuth2AuthenticationFailureHandler object dependency.
         */
        private final OAuth2AuthenticationFailureHandler
                oAuth2AuthenticationFailureHandler;

        /**
         * Creates Security Config.
         *
         * @param alearnerService
         * @param appProperties   properties
         * @param environment     environment
         * @param aCacheManager   aCacheManager
         */
        public SecurityConfig(final LearnerService alearnerService,
                        final AppProperties appProperties,
                        final Environment environment,
                        final CacheManager aCacheManager) {
                this.learnerService = alearnerService;
                TokenProvider tokenProvider = new TokenProvider(appProperties,
                                aCacheManager);
                cookieAuthorizationRequestRepository
                        = new HttpCookieOAuth2AuthorizationRequestRepository();
                oAuth2AuthenticationSuccessHandler
                        = new OAuth2AuthenticationSuccessHandler(tokenProvider,
                                appProperties,
                                cookieAuthorizationRequestRepository);
                oAuth2AuthenticationFailureHandler
                        = new OAuth2AuthenticationFailureHandler(
                                cookieAuthorizationRequestRepository);
                passwordEncoder = new BCryptPasswordEncoder();
                customUserDetailsService = new CustomUserDetailsService(
                                passwordEncoder, environment,
                        this.learnerService);
                customOAuth2UserService = new CustomOAuth2UserService(
                                this.learnerService);
                tokenAuthenticationFilter = new TokenAuthenticationFilter(
                                tokenProvider, customUserDetailsService);
        }

        /**
         * passwordEncoder.
         *
         * @return passwordEncoder
         */
        @Bean
        PasswordEncoder passwordEncoder() {
                return passwordEncoder;
        }

        /**
         * customUserDetailsService.
         *
         * @return customUserDetailsService
         */
        @Bean
        CustomUserDetailsService customUserDetailsService() {
                return customUserDetailsService;
        }

        /**
         * Configure Authentication Manager.
         * @param http
         * @param bCryptPasswordEncoder
         * @param userDetailService
         * @return AuthenticationManager
         * @throws Exception
         */
        @Bean
        public AuthenticationManager authenticationManager(
                        final HttpSecurity http,
                        final BCryptPasswordEncoder bCryptPasswordEncoder,
                        final UserDetailsService userDetailService)
                        throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(customUserDetailsService)
                                .passwordEncoder(bCryptPasswordEncoder)
                                .and()
                                .build();
        }

        /**
         * WebSecurityCustomizer.
         * @return WebSecurityCustomizer
         */
        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.ignoring()
                                .requestMatchers("h2-console", "/h2-console/**",
                                        "/swagger-ui.html", "/swagger-ui/**",
                                        "/oauth2/**",
                                        "/v3/api-docs/**",
                                        "/questions/**",
                                        "/error",
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/practices/**");
        }

        /**
         * method configure is overrided here.
         *
         * @param http http
         * @return SecurityFilterChain
         * @throws Exception exception
         */
        @Bean
        public SecurityFilterChain filterChain(
                        final HttpSecurity http) throws Exception {
                http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()

                .requestMatchers("/api/auth/login",
                                "/api/auth/signup",
                                "/oauth2")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(
                                cookieAuthorizationRequestRepository)
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

                // Add our custom Token based authentication filter
                http.addFilterBefore(tokenAuthenticationFilter,
                                UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }

        /**
         * In Production we can controld the filters from application.yml.
         *
         * @return corsFilter cors filter
         */
        @Bean
        public CorsFilter corsFilter() {
                final CorsConfiguration config = new CorsConfiguration();
                config.addAllowedOriginPattern("*");
                config.addAllowedHeader("*");
                config.setAllowedMethods(
                                List.of("GET",
                                        "POST",
                                        "PUT",
                                        "PATCH",
                                        "DELETE",
                                        "OPTIONS"));

                final UrlBasedCorsConfigurationSource source
                        = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);

                return new CorsFilter(source);
        }
}
