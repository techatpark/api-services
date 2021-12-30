package com.gurukulams.starter.security.controller;

import com.gurukulams.core.service.LearnerService;
import com.gurukulams.starter.security.payload.AuthenticationRequest;
import com.gurukulams.starter.security.payload.AuthenticationResponse;
import com.gurukulams.starter.security.security.CustomUserDetailsService;
import com.gurukulams.starter.security.security.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import java.security.Principal;

/**
 * The type Authentication api controller.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",
        description = "Resource to manage authentication")
class AuthenticationApiController {

    /**
     * instance of authenticationManager.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * instance of userDetailsService.
     */
    private final LearnerService userDetailsService;
    /**
     * instance of tokenUtil.
     */
    private final TokenProvider tokenUtil;

    /**
     * constructs authenticationManager,userDetailsService,tokenUtil.
     *
     * @param anAuthenticationManager the an authentication manager
     * @param anUserDetailsService    the an user details service
     * @param aTokenUtil              the a token util
     */
    AuthenticationApiController(final AuthenticationManager
                                        anAuthenticationManager,
                                final LearnerService
                                        anUserDetailsService,
                                final TokenProvider aTokenUtil) {
        this.authenticationManager = anAuthenticationManager;
        this.userDetailsService = anUserDetailsService;
        this.tokenUtil = aTokenUtil;
    }

    /**
     * performs the login function.
     *
     * @param authenticationRequest the authentication request
     * @return authentication response
     */
    @Operation(summary = "Login with credentials")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            final @RequestBody
                    AuthenticationRequest
                    authenticationRequest) {

        final Authentication authResult = this.authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUserName(),
                                authenticationRequest.getPassword()));
        if (authResult == null) {
            throw new BadCredentialsException("Invalid Login Credentials");
        }


        final String token = tokenUtil.generateToken(authResult);

        final AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(authenticationRequest.getUserName(),
                        token,
                        "Refresh",
                        userDetailsService.readByEmail(authenticationRequest
                                .getUserName())
                                .get().imageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }

    /**
     * logout an user.
     *
     * @return void response entity
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
     * @param principal the principal
     * @return AuthenticationResponse response entity
     */
    @Operation(summary = "Get logged in user profile",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "practice"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "practice not found")})
    @GetMapping("/me")
    public ResponseEntity<AuthenticationResponse> me(
            final Principal principal) {
        final AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(principal.getName(),
                        "",
                        "Refresh",
                        userDetailsService.readByEmail(principal
                                .getName())
                                .get().imageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }
}
