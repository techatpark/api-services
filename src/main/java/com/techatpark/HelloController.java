package com.techatpark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> index(Principal principal) {
        return new ResponseEntity<String>( "Hello " +principal.getName(), HttpStatus.OK);
    }

}
