package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ArrayDemoTest {

    private final static Logger logger = LoggerFactory.getLogger(ArrayDemoTest.class);

    public static void main(String[] arguments) {

        // Declare
        Integer[] anArray;

        anArray = new Integer[10];

        anArray[0] = 2;
        anArray[1] = 3;

        logger.info("Hi");
    }

}
