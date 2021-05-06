package com.techatpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * Workaround to fix Checkstyle Issue.
     * Ref: https://github.com/checkstyle/checkstyle/issues/3155
     */
    public void s() {
    }

    /**
     * Main method of this application.
     *
     * @param args
     */

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
