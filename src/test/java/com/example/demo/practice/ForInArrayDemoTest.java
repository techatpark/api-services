package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForInArrayDemoTest {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(ForInArrayDemoTest.class);

        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int item : numbers) {
            logger.info("Count is: " + item);
        }
    }

}