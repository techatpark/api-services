package com.gurukulams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TestApplication {
    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(TestApplication.class);

    @Bean
    ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .findAndAddModules()
                .build();
    }

    /**
     * Main method of this application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    /**
     * This will be invoked one the application is started.
     * @param event
     */
    @EventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        logger.info("Application Started", event.getTimestamp());
    }
}
