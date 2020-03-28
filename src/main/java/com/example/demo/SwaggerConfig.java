package com.example.demo;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .genericModelSubstitutes(ResponseEntity.class).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Vehicle Tracker", "This is the ststem that tracks the vehicles andanalyze the operations",
                "API ßTOS", "Terms of service", new Contact("Sathish Kumar", "www.zols.org", "abc@xyz.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
