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
        List.of("/courses/c-programming",
                "/courses/c-programming/introduction",
                "/courses/c-programming/recursion",
                "/courses/c-programming/instructions",
                "/courses/c-programming/decisioncontrol",
                "/courses/c-programming/logicaloperators",
                "/courses/c-programming/loopcontrol",
                "/courses/c-programming/morecomplexrepetitions",
                "/courses/c-programming/casecontrol",
                "/courses/c-programming/function",
                "/courses/c-programming/pointers",
                "/courses/c-programming/array",
                "/courses/c-programming/multidimensionalarray",
                "/courses/c-programming/strings",
                "/courses/c-programming/multistrings",
                "/courses/c-programming/structures",
                "/courses/c-programming/consoleio",
                "/courses/c-programming/fileio",
                "/practices/sql").stream().forEach(path -> {
            registry.addViewController(path).setViewName("forward:"
                    + path + "/index.html");
        });
    }
}
