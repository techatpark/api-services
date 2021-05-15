package com.techatpark.starter.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class StaticContentConfiguration implements WebMvcConfigurer {

    /**
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        List.of("/courses/**",
                "/courses/**/**",
                "/books/**",
                "/books/**/**",
                "/practices/**").stream().forEach(path -> {
            registry.addViewController(path).setViewName("forward:"
                    + path + "/index.html");
        });
    }
}
