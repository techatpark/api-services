package com.techatpark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>( "Welcome to Spring Boot 2.x",
                HttpStatus.OK);
    }

}
