package com.gurukulams.web.starter.security.controller;

import com.gurukulams.core.payload.AuthenticationRequest;
import com.gurukulams.core.payload.AuthenticationResponse;
import com.gurukulams.core.payload.RefreshToken;
import com.gurukulams.core.payload.SignupRequest;
import com.gurukulams.core.service.LearnerService;
import com.gurukulams.web.starter.security.security.TokenProvider;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * The type Authentication api controller.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",
        description = "Resource to manage authentication")
class AuthenticationAPIController {

    /**
     * value.
     */
    private static final int VALUE = 7;
    /**
     * instance of authenticationManager.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * instance of userDetailsService.
     */
    private final LearnerService learnerService;
    /**
     * instance of tokenUtil.
     */
    private final TokenProvider tokenProvider;

    /**
     * instance of PasswordEncoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * constructs authenticationManager,userDetailsService,tokenUtil.
     *
     * @param anAuthenticationManager the an authentication manager
     * @param anUserDetailsService    the an user details service
     * @param apasswordEncoder        the a apasswordEncoder
     * @param aTokenUtil              the a token util
     */
    AuthenticationAPIController(final AuthenticationManager
                                        anAuthenticationManager,
                                final LearnerService
                                        anUserDetailsService,
                                final PasswordEncoder apasswordEncoder,
                                final TokenProvider aTokenUtil) {
        this.authenticationManager = anAuthenticationManager;
        this.learnerService = anUserDetailsService;
        this.tokenProvider = aTokenUtil;
        this.passwordEncoder = apasswordEncoder;
    }

    /**
     * @param signUpRequest
     * @return loginRequest
     */
    @Operation(summary = "Signup the User")
    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(
            final @RequestBody SignupRequest signUpRequest) {
        learnerService.signUp(signUpRequest,
                s -> passwordEncoder.encode(s));
        return ResponseEntity.ok().build();
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


        final String token = tokenProvider.generateToken(authResult);

        final AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(authenticationRequest.getUserName(),
                        token,
                        this.tokenProvider.generateRefreshToken(token),
                        learnerService.readByEmail("System",
                                        authenticationRequest
                                                .getUserName())
                                .get().imageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }

    /**
     * performs the login function.
     *
     * @param refreshToken the authentication request
     * @param principal
     * @return authentication response
     */
    @Operation(summary = "Refresh the credentials")
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            final Principal principal,
            final @RequestBody
            RefreshToken
                    refreshToken) {
        return ResponseEntity.ok().body(tokenProvider
                .refresh(principal, refreshToken));
    }

    /**
     * logout an user.
     *
     * @param request
     * @return void response entity
     */
    @Operation(summary = "logout current user",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth
                .startsWith("Bearer ")) {
            tokenProvider.logout(headerAuth.substring(VALUE));
        }

        return ResponseEntity.ok().build();
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
                        learnerService.readByEmail("System",
                                        principal
                                                .getName())
                                .get().imageUrl());
        return ResponseEntity.ok().body(authenticationResponse);
    }
}
