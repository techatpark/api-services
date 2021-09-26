package com.gurukulams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Application.
 */
@SpringBootApplication
public class Application {

    /**
     * Main method of this application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Workaround to fix Checkstyle Issue.
     * Ref: https://github.com/checkstyle/checkstyle/issues/3155
     */
    public void s() {
        throw new UnsupportedOperationException("Dummy Method");
    }

}
