package com.gurukulams.web.starter.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurukulams.core.service.LearnerService;
import com.gurukulams.web.starter.security.security.CustomUserDetailsService;
import com.gurukulams.web.starter.security.security.RestAuthenticationEntryPoint;
import com.gurukulams.web.starter.security.security.TokenAuthenticationFilter;
import com.gurukulams.web.starter.security.security.TokenProvider;
import com.gurukulams.web.starter.security.security.oauth2.CustomOAuth2UserService;
import com.gurukulams.web.starter.security.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.gurukulams.web.starter.security.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.gurukulams.web.starter.security.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@EnableConfigurationProperties(AppProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
         * @param objectMapper
         * @param aCacheManager   aCacheManager
         */
        public SecurityConfig(final LearnerService alearnerService,
                              final AppProperties appProperties,
                              final Environment environment,
                              final CacheManager aCacheManager,
                              final ObjectMapper objectMapper) {
                this.learnerService = alearnerService;
                TokenProvider tokenProvider = new TokenProvider(appProperties,
                        aCacheManager, objectMapper);
                cookieAuthorizationRequestRepository = new
                        HttpCookieOAuth2AuthorizationRequestRepository();
                oAuth2AuthenticationSuccessHandler = new
                        OAuth2AuthenticationSuccessHandler(tokenProvider,
                        appProperties,
                        cookieAuthorizationRequestRepository);
                oAuth2AuthenticationFailureHandler = new
                        OAuth2AuthenticationFailureHandler(
                        cookieAuthorizationRequestRepository);
                passwordEncoder = new BCryptPasswordEncoder();
                customUserDetailsService = new CustomUserDetailsService(
                        passwordEncoder, environment, this.learnerService);
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
         * method configure is override here.
         *
         * @param authenticationManagerBuilder authentication manager builder
         * @throws Exception exception
         */
        @Override
        public void configure(final
                              AuthenticationManagerBuilder
                                      authenticationManagerBuilder)
                throws Exception {
                authenticationManagerBuilder
                        .userDetailsService(customUserDetailsService)
                        .passwordEncoder(passwordEncoder);
        }

        /**
         * Hi.
         *
         * @param web web
         * @throws Exception exception
         */
        @Override
        public void configure(final WebSecurity web) {
                web.ignoring().antMatchers("/api/metrics/**",
                        "/h2-console", "/h2-console/**",
                        "/swagger-ui.html", "/swagger-ui/**",
                        "/v3/api-docs/**", "/resources/**",
                        "/static/**", "/css/**", "/js/**", "/images/**",
                        "/questions/**", "/ta/questions/**",
                        "/courses/**", "/courses/**/**", "/courses/**/**/**",
                        "/subjects/**",
                        "/books/**",
                        "/books/**/**",
                        "/books/**/**/**",
                        "/books/**/**/**/**",
                        "/books/**/**/**/**",
                        "/ta/books/**",
                        "/ta/books/**/**",
                        "/ta/books/**/**/**",
                        "/ta/books/**/**/**/**",
                        "/ta/books/**/**/**/**",
                        "/chat",
                        "/chat/**",
                        "/chat/**/**",
                        "/chat/**/**/**");
        }

        /**
         * method authenticationManagerBean is overrided.
         *
         * @return AuthenticationManager
         * @throws Exception
         */
        @Bean(BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager
        authenticationManagerBean() throws Exception {
                return super.authenticationManagerBean();
        }

        /**
         * method configure is overrided here.
         *
         * @param http http
         * @throws Exception exception
         */
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
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
                        .authenticationEntryPoint(
                                new RestAuthenticationEntryPoint())
                        .and()
                        .authorizeRequests()
                        .antMatchers("/",
                                "/error",
                                "/favicon.ico",
                                "/**/*.png",
                                "/**/*.gif",
                                "/**/*.svg",
                                "/**/*.jpg",
                                "/**/*.html",
                                "/**/*.css",
                                "/**/*.js")
                        .permitAll()
                        .antMatchers("/api/auth/login",
                                "/api/auth/signup",
                                "/oauth2/**")
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
                                "POST", "PUT", "PATCH",
                                "DELETE", "OPTIONS"));

                final UrlBasedCorsConfigurationSource source =
                        new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);

                return new CorsFilter(source);
        }
}
