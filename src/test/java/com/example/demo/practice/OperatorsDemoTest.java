package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorsDemoTest {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(OperatorsDemoTest.class);
        Integer result = 1 + 4;
        logger.info("addition - " + result);

        result = result - 1;
        logger.info("subtraction - " + result);

        result++;
        logger.info("increment - " + result);

        result--;
        logger.info("decrement - " + result);

        ++result;
        logger.info("prefix - " + result);

        result++;
        logger.info("postfix - " + result);

        String firstString = " this does arithmetic, unary and ";
        String secString = "assingment operators demo.";
        String thirdString = firstString + secString;
        logger.info(thirdString);
    }
}