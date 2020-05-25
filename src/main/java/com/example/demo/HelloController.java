package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Greetings coming from Spring Boot! ", HttpStatus.OK);
    }

    @RequestMapping("/secured_hello")
    @Secured("ROLE_USER")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Secured Greetings!-2", HttpStatus.OK);
    }

}
