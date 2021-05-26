package com.techatpark.starter.security.controller;

import com.techatpark.starter.security.model.AuthenticationRequest;
import com.techatpark.starter.security.model.AuthenticationResponse;
import com.techatpark.starter.security.utils.TokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;
    /**
     * instance of tokenUtil.
     */
    private final TokenUtil tokenUtil;

    /**
     * constructs authenticationManager,userDetailsService,tokenUtil.
     *
     * @param anAuthenticationManager
     * @param anUserDetailsService
     * @param aTokenUtil
     */
    public AuthenticationApiController(final AuthenticationManager
                                               anAuthenticationManager,
                                       final UserDetailsService
                                               anUserDetailsService,
                                       final TokenUtil aTokenUtil) {
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

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());
        final String token = tokenUtil.generateToken(userDetails);
        final String profilePicture = userDetails.getUsername().equals("tom")
                ? "/images/tom.png"
                : "/images/jerry.png";
        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(authenticationRequest.getUserName(),
                        token,
                        "Refresh",
                        profilePicture);
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
     * @return userdetails
     */
    @Operation(summary = "Get logged in user profile",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public ResponseEntity<UserDetails> me(final Principal principal) {
        return ResponseEntity.ok(userDetailsService
                .loadUserByUsername(principal.getName()));
    }
}
