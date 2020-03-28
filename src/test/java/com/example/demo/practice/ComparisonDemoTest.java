package com.example.demo.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComparisonDemoTest {

    private final static Logger logger = LoggerFactory.getLogger(ComparisonDemoTest.class);

    public static void main(String[] args) {
        Parent parentObj = new Parent();
        parentObj.parentFn();

        Child childObj = new Child();
        childObj.childFn();
        childObj.parentFn();

        Parent childObjAsParent = new Child();

    }

}

class Parent {
    private final static Logger logger = LoggerFactory.getLogger(Parent.class);

    public void parentFn() {
        logger.info("I am parent fn");
    }
}

// is a
class Child extends Parent {
    private final static Logger logger = LoggerFactory.getLogger(Parent.class);

    public void childFn() {
        logger.info("I am child fn");
    }
}