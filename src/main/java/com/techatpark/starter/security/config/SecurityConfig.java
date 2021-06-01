package com.techatpark.starter.security.config;

import com.techatpark.starter.security.security.CustomUserDetailsService;
import com.techatpark.starter.security.security.RestAuthenticationEntryPoint;
import com.techatpark.starter.security.security.TokenAuthenticationFilter;
import com.techatpark.starter.security.security.oauth2.CustomOAuth2UserService;
import com.techatpark.starter.security.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.techatpark.starter.security.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.techatpark.starter.security.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
     * inject the customUserDetailsService object dependency.
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * inject the customOAuth2UserService object dependency.
     */
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    /**
     * inject the oAuth2AuthenticationSuccessHandler object dependency.
     */
    @Autowired
    private OAuth2AuthenticationSuccessHandler
            oAuth2AuthenticationSuccessHandler;

    /**
     * inject the oAuth2AuthenticationFailureHandler object dependency.
     */
    @Autowired
    private OAuth2AuthenticationFailureHandler
            oAuth2AuthenticationFailureHandler;



    /**
     * Token authentication filter token authentication filter.
     *
     * @return the token authentication filter
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /*
      By default, Spring OAuth2 uses
      HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless,
      we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */

    /**
     * Cookie authorization request repository http cookie o auth 2
     * authorization request repository.
     *
     * @return the http cookie o auth 2 authorization request repository
     */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository
    cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    /**
     * method configure is overrided here.
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(final
                          AuthenticationManagerBuilder
                                      authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Hi.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html", "/swagger-ui/**",
                "/v3/api-docs/**", "/resources/**",
                "/static/**", "/css/**", "/js/**", "/images/**",
                "/practices/**",
                "/courses/**", "/courses/**/**", "/courses/**/**/**/",
                "/subjects/**", "/books/**");
    }

    /**
     * method authenticationManagerBean is overrided.
     *
     * @return
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * method configure is overrided here.
     *
     * @param http
     * @throws Exception
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
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
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
                        cookieAuthorizationRequestRepository())
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
        http.addFilterBefore(tokenAuthenticationFilter(),
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
                List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        final UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
