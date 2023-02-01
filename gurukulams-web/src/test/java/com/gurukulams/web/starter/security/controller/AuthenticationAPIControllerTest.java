package com.gurukulams.web.starter.security.controller;



import com.gurukulams.core.payload.AuthenticationRequest;
import com.gurukulams.core.payload.AuthenticationResponse;
import com.gurukulams.core.payload.SignupRequest;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.LearnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthenticationAPIControllerTest {
    @Value(value="${local.server.port}")
    private int port;

    private final SignupRequest signupRequest;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private LearnerService learnerService;

    @Autowired
    private BoardService boardService;

    AuthenticationAPIControllerTest() {
        this.signupRequest = new SignupRequest();
        this.signupRequest.setEmail("email@email.com");
        this.signupRequest.setPassword("password");
        this.signupRequest.setImageUrl("image_url");
    }

    @BeforeEach
    void before() {
        cleanup();
    }

    @AfterEach
    void after() {
        cleanup();
    }

    void cleanup() {
        learnerService.deleteAll();
        boardService.deleteAll();
        this.webTestClient
                .post()
                .uri("/api/auth/signup")
                .body(Mono.just(signupRequest), SignupRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void basicLogin() throws Exception {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                this.signupRequest.getEmail(),
                this.signupRequest.getPassword());

        AuthenticationResponse authenticationResponse = this.webTestClient
                .post()
                .uri("/api/auth/login")
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK.value())
                .expectBody(AuthenticationResponse.class)
                .returnResult().getResponseBody();

        this.webTestClient
                .get()
                .uri("/api/boards")
                .header("Authorization", "Bearer " + authenticationResponse.getAuthToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.NO_CONTENT.value());

        this.webTestClient
                .post()
                .uri("/api/auth/logout")
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .header("Authorization", "Bearer " + authenticationResponse.getAuthToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK.value());

        this.webTestClient
                .get()
                .uri("/api/boards")
                .header("Authorization", "Bearer " + authenticationResponse.getAuthToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

}