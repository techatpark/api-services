package com.gurukulams;

import com.gurukulams.core.model.Syllabus;
import com.gurukulams.core.service.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Autowired
    private SyllabusService syllabusService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Demo task
        Runnable runnableTask = () -> {
            for (int i = 0; i < 100000; i++) {
                Syllabus syllabus = new Syllabus(null,"Title","Description",null,
                        null,null,null);
                syllabusService.create("Mani",syllabus);
            }
        };

        //Executor service instance
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //1. execute task using execute() method
        executor.execute(runnableTask);
    }

}
