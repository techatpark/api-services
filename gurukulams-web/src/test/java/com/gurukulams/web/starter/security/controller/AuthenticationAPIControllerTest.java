package com.gurukulams.web.starter.security.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationAPIControllerTest {
    @Autowired
    private AuthenticationAPIController controller;

    @Test
    public void basicLogin() throws Exception {
        assertThat(controller).isNotNull();
    }

}