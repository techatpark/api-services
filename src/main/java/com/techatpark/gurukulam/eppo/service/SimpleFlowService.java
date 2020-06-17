package com.techatpark.gurukulam.eppo.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SimpleFlowService implements JavaDelegate {
    /**
     * method to exxeute the taks.
     * 
     * @param execution
     */
    public void execute(final DelegateExecution execution) {
        System.out.println("Simple Task executed.");
    }
}
