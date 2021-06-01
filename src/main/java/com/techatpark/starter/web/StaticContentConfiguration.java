package com.techatpark.starter.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class StaticContentConfiguration implements WebMvcConfigurer {

    /**
     * Location of static folder.
     */
    @Value("${spring.web.resources.static-locations:#{null}}")
    private String staticFileLocation;

    /**
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

        if (this.staticFileLocation != null
                && staticFileLocation.startsWith("file:")) {
            final String pathPrefix = staticFileLocation.replaceFirst("file:",
                    "");
            final Path path = new File(pathPrefix).toPath();
            try {
                Files.find(path,
                        Integer.MAX_VALUE,
                        (filePath, fileAttr) -> fileAttr.isRegularFile())
                        .map(path1 ->
                                path1.toString()
                                        .replaceFirst(pathPrefix, "")
                                        .toLowerCase()
                        )
                        .filter(p -> !p.equals("/index.html")
                                && p.endsWith("index.html"))
                        .forEach(indexHtmlPath -> {
                            registry
                                    .addViewController(
                                            indexHtmlPath
                                                    .replaceAll("/index.html",
                                                            ""))
                                    .setViewName("forward:"
                                            + indexHtmlPath);
                        });
            } catch (final IOException e) {
                e.printStackTrace();
            }

            registry
                    .addViewController(
                            "/oauth2/redirect")
                    .setViewName("forward:/oauth2/redirect/index.html");
        }

    }
}
