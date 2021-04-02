package com.techatpark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
class HelloController {

    @RequestMapping("/")
    public ResponseEntity<Map<String,Object>> index() {
        Runtime.Version version = java.lang.Runtime.version();
        Map<String,Object> map = Map.of(
                    "Application Name","Hey Dude",
                "Java Version",version.toString(),
                "Java Version Build",version.build().get(),
                "Java Version Pre-Release Info",version.pre().orElse("NA"));
        return new ResponseEntity<>(map,
                HttpStatus.OK);
    }

}
