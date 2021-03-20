package com.techatpark.starter.security.controller;

import com.techatpark.starter.security.model.AuthenticationRequest;
import com.techatpark.starter.security.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
public class LoginApiController {

    private final AuthenticationManager authenticationManager;

    public LoginApiController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        Authentication authResult = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        if( authResult == null) {
            throw new BadCredentialsException("Invalid Login Credentials");
        }
        String token = Base64.getEncoder()
                .encodeToString(
                        (authenticationRequest.getUserName() +":"+ authenticationRequest.getPassword()).getBytes()
                );

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);

        return ResponseEntity.ok().body(authenticationResponse);
    }

}
