package com.gurukulams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Hello controller to test.
 */
@RestController
@RequestMapping("/api/info")
class HelloController {

    /**
     * application name.
     */
    private final String name;

    /**
     * Application version.
     */
    private final String version;

    HelloController(@Value("${spring.application.name}")
                    final String aName,
                    @Value("${spring.application.version}")
                    final String aVersion) {
        this.name = aName;
        this.version = aVersion;
    }


    /**
     * index method to show he details.
     *
     * @return list of details
     * @throws URISyntaxException the uri syntax exception
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> index()
            throws URISyntaxException {
        final Map<String, Object> map =
                Map.of("javaVersion", java.lang.Runtime.version().toString(),
                        "appVersion", version);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
