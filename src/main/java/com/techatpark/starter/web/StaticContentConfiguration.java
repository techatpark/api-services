package com.techatpark.starter.web;

import com.techatpark.starter.security.security.RestAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Static content configuration.
 */
@Configuration
public class StaticContentConfiguration implements WebMvcConfigurer {

    /**
     * declares the logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

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

                            String urlPath = indexHtmlPath
                                    .replaceAll("/index.html",
                                            "");
                            registry
                                    .addViewController(
                                            urlPath)
                                    .setViewName("forward:"
                                            + indexHtmlPath);
                        });
            } catch (final IOException e) {
                LOGGER.error("Unable to load Static Folders", e);
            }

            registry
                    .addViewController(
                            "/oauth2/redirect")
                    .setViewName("forward:/oauth2/redirect/index.html");
        }

    }
}
