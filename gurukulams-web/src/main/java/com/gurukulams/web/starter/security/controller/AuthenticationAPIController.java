package com.gurukulams.web.starter.security.controller;

import com.gurukulams.core.payload.AuthenticationRequest;
import com.gurukulams.core.payload.AuthenticationResponse;
import com.gurukulams.core.payload.RefreshToken;
import com.gurukulams.core.payload.RegistrationRequest;
import com.gurukulams.core.payload.SignupRequest;
import com.gurukulams.core.service.LearnerService;
import com.gurukulams.web.starter.security.security.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
class AuthenticationAPIController {


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
     * @param anAuthenticationManager the authentication manager
     * @param anUserDetailsService    the user details service
     * @param apasswordEncoder        the apasswordEncoder
     * @param aTokenUtil              a token util
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
    public ResponseEntity<Void> signUp(
            final @RequestBody SignupRequest signUpRequest) {
        learnerService.signUp(signUpRequest,
                s -> passwordEncoder.encode(s));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @param registrationRequest
     * @param authHeader
     * @param principal
     * @return loginRequest
     */
    @Operation(summary = "Register the User")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            final Principal principal,
            @RequestHeader(name = "Authorization") final String authHeader,
            final @RequestBody RegistrationRequest registrationRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                tokenProvider.register(authHeader,
                        principal.getName(),
                        registrationRequest));
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

        return ResponseEntity.ok().body(
                tokenProvider.getAuthenticationResponse(authResult));
    }

    /**
     * performs the login function.
     * @param authHeader
     * @param refreshToken the authentication request
     * @param principal
     * @return authentication response
     */
    @Operation(summary = "Refresh the credentials")
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            final Principal principal,
            @RequestHeader(name = "Authorization") final String authHeader,
            final @RequestBody
            RefreshToken
                    refreshToken) {
        return ResponseEntity.ok().body(tokenProvider
                .refresh(authHeader,
                        principal.getName(), refreshToken));
    }

    /**
     * logout an user.
     *
     * @param authHeader
     * @return void response entity
     */
    @Operation(summary = "logout current user",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(name = "Authorization") final String authHeader) {
        tokenProvider.logout(authHeader);
        return ResponseEntity.ok().build();
    }

    /**
     * get the user details from the principal.
     *
     * @param authHeader the auth Header
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
            @RequestHeader(name = "Authorization") final String authHeader) {
        return ResponseEntity.ok().body(
                tokenProvider.getWelcomeResponse(authHeader));
    }
}
