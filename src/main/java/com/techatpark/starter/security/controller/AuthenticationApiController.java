package com.techatpark.starter.security.controller;

import com.techatpark.starter.security.payload.AuthenticationRequest;
import com.techatpark.starter.security.payload.AuthenticationResponse;
import com.techatpark.starter.security.security.CustomUserDetailsService;
import com.techatpark.starter.security.security.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",
        description = "Resource to manage authentication")
public class AuthenticationApiController {

    /**
     * instance of authenticationManager.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * instance of userDetailsService.
     */
    private final CustomUserDetailsService userDetailsService;
    /**
     * instance of tokenUtil.
     */
    private final TokenProvider tokenUtil;

    /**
     * constructs authenticationManager,userDetailsService,tokenUtil.
     *
     * @param anAuthenticationManager
     * @param anUserDetailsService
     * @param aTokenUtil
     */
    public AuthenticationApiController(final AuthenticationManager
                                               anAuthenticationManager,
                                       final CustomUserDetailsService
                                               anUserDetailsService,
                                       final TokenProvider aTokenUtil) {
        this.authenticationManager = anAuthenticationManager;
        this.userDetailsService = anUserDetailsService;
        this.tokenUtil = aTokenUtil;
    }

    /**
     * performs the login function.
     *
     * @param authenticationRequest
     * @return authentication response
     */
    @Operation(summary = "Login with credentials")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            final @RequestBody @Valid
                    AuthenticationRequest
                    authenticationRequest) {

        Authentication authResult = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()));
        if (authResult == null) {
            throw new BadCredentialsException("Invalid Login Credentials");
        }


        final String token = tokenUtil.generateToken(authResult);

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(authenticationRequest.getUserName(),
                        token,
                        "Refresh",
                        userDetailsService.findByName(authenticationRequest
                                .getUserName())
                                .get().getImageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }

    /**
     * logout an user.
     *
     * @return void
     */
    @Operation(summary = "logout current user",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

    /**
     * get the user details from the principal.
     *
     * @param principal
     */
    @Operation(summary = "Get logged in user profile",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public ResponseEntity<AuthenticationResponse> me(
            final Principal principal) {
        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(principal.getName(),
                        "",
                        "Refresh",
                        userDetailsService.findByName(principal
                                .getName())
                                .get().getImageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }
}
