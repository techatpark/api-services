package com.gurukulams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/metrics")
@Tag(name = "Application Metrics",
        description = "Resource to retrieve application metrics")
class MetricsAPIController {

    /**
     * application name.
     */
    private final String name;

    /**
     * Application version.
     */
    private final String version;

    MetricsAPIController(@Value("${spring.application.name}")
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
    @Operation(summary = "provides application metrics")
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> index()
            throws URISyntaxException {
        final Map<String, Object> map =
                Map.of("javaVersion", java.lang.Runtime.version().toString(),
                        "appVersion", version);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
