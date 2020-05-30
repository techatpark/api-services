package com.example.demo;

import com.example.demo.jwt.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	@Value("${jwt.type}")
	private String jwtType;
	@Value("${jwt.audience}")
	private String jwtAudience;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().realmName("My org ream").and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtAudience, jwtIssuer, jwtSecret, jwtType))
				.authorizeRequests().anyRequest().permitAll();
	}

}