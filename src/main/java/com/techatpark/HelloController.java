package com.techatpark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
class HelloController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.application.version}")
    private String version;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() throws URISyntaxException {
        Map<String, Object> map = Map.of(
                "javaVersion", java.lang.Runtime.version().toString(),
                "appVersion",version);
        return new ResponseEntity<>(map,
                HttpStatus.OK);
    }

}
