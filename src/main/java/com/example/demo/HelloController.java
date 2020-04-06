package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("Greetings!-2", HttpStatus.OK);
    }

}
