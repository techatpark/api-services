package com.gurukulams;

import com.gurukulams.core.util.BoardMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


/**
 * The type Application.
 */
@SpringBootApplication
public class Application {

    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(Application.class);
    /**
     * Board Maker.
     */
    @Autowired
    private BoardMaker boardMaker;

    /**
     * Main method of this application.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This will be invoked one the application is started.
     * @param event
     */
    @EventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        logger.info("Application Started", event.getTimestamp());
        boardMaker.createAllBoards("tom");
    }
}
