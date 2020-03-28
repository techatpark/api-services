package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhileDoWhileDemoTest {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(WhileDoWhileDemoTest.class);

        int count = 1;
        while (count < 1) {
            logger.info("Count is: " + count);
            count++;
        }
        logger.info("while loop is not executed even once as the condition is not true here.");

        int count1 = 0;
        do {
            logger.info("Count1 is: " + count1);
            count1++;
        } while (count1 < 2);
        logger.info("while loop is executed atleast once as the condition checking happens at t bottom here.");

    }

}
