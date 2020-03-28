package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfElseSwitchDemoTest {

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(OperatorsDemoTest.class);

        String weekday = "wednesday";
        String day;

        if (weekday == "monday") {
            day = "monday";
        } else if (weekday == "tuesday") {
            day = "tuesday";
        } else if (weekday == "wednesday") {
            day = "wednesday";
        } else {
            day = "null";
        }

        logger.info(" day = " + day);

        Integer dayno = 4;
        String day1;

        switch (dayno) {
            case 1:
                day1 = "monday";
                break;
            case 2:
                day1 = "tuesday";
                break;
            case 3:
                day1 = "wednesday";
                break;
            case 4:
                day1 = "thursday";
                break;

            default:
                day1 = "invalid day";
                break;
        }

        logger.info(" day = " + day1);

    }
}