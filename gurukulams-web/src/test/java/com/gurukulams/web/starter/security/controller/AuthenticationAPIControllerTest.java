package com.gurukulams.web.starter.security.controller;



import com.gurukulams.core.payload.AuthenticationRequest;
import com.gurukulams.core.payload.AuthenticationResponse;
import com.gurukulams.core.payload.RefreshToken;
import com.gurukulams.core.payload.SignupRequest;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.LearnerService;
import com.gurukulams.web.starter.security.config.AppProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private AppProperties appProperties;

    AuthenticationAPIControllerTest() {
        this.signupRequest = new SignupRequest();
        this.signupRequest.setEmail("email@email.com");
        this.signupRequest.setPassword("password");
        this.signupRequest.setImageUrl("image_url");
    }

    @DynamicPropertySource
    static void authProperties(DynamicPropertyRegistry registry) {
        registry.add("app.auth.tokenExpirationMsec",() -> 1500);
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

        AuthenticationResponse authenticationResponse = login(authenticationRequest);

        getBoards(authenticationResponse).isEqualTo(HttpStatus.NO_CONTENT.value());

        refresh(authenticationResponse.getAuthToken(),
                authenticationResponse.getRefreshToken()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

        // Wait for Token Expiry
        TimeUnit.MILLISECONDS.sleep(appProperties.getAuth().getTokenExpirationMsec());

        AuthenticationResponse refreshedResponse = refresh(
                authenticationResponse.getAuthToken(),
                authenticationResponse.getRefreshToken())
                .isEqualTo(HttpStatus.OK.value())
                .expectBody(AuthenticationResponse.class)
                .returnResult().getResponseBody();

        getBoards(authenticationResponse).isEqualTo(HttpStatus.UNAUTHORIZED.value());

        getBoards(refreshedResponse).isEqualTo(HttpStatus.NO_CONTENT.value());

        logout(authenticationRequest, refreshedResponse).isEqualTo(HttpStatus.OK.value());

        getBoards(authenticationResponse).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    void testSwapping() throws InterruptedException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                this.signupRequest.getEmail(),
                this.signupRequest.getPassword());

        AuthenticationResponse originalAuth = login(authenticationRequest);

        // Wait for Token Expiry
        TimeUnit.MILLISECONDS.sleep(appProperties.getAuth().getTokenExpirationMsec());


        AuthenticationResponse anotherAuth = login(authenticationRequest);

        refresh(anotherAuth.getAuthToken(),
                originalAuth.getRefreshToken()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    @Test
    void testExpiredLogout() throws InterruptedException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                this.signupRequest.getEmail(),
                this.signupRequest.getPassword());

        AuthenticationResponse authenticationResponse = login(authenticationRequest);

        getBoards(authenticationResponse).isEqualTo(HttpStatus.NO_CONTENT.value());

        // Wait for Token Expiry
        TimeUnit.MILLISECONDS.sleep(appProperties.getAuth().getTokenExpirationMsec());

        logout(authenticationRequest, authenticationResponse).isEqualTo(HttpStatus.OK.value());

    }

    private AuthenticationResponse login(final AuthenticationRequest authenticationRequest) {
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
        return authenticationResponse;
    }

    private StatusAssertions refresh(final String authToken, final String rToken) {
        RefreshToken refreshToken = new RefreshToken(rToken);
        return this.webTestClient
                .post()
                .uri("/api/auth/refresh")
                .body(Mono.just(refreshToken), RefreshToken.class)
                .header("Authorization", "Bearer " + authToken)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus();
    }

    private StatusAssertions logout(final AuthenticationRequest authenticationRequest, final AuthenticationResponse authenticationResponse) {
        return this.webTestClient
                .post()
                .uri("/api/auth/logout")
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .header("Authorization", "Bearer " + authenticationResponse.getAuthToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                ;
    }

    private StatusAssertions getBoards(final AuthenticationResponse authenticationResponse) {
        return this.webTestClient
                .get()
                .uri("/api/boards")
                .header("Authorization", "Bearer " + authenticationResponse.getAuthToken())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus();
    }

}