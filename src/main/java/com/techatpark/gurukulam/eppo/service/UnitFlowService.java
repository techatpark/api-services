package com.techatpark.gurukulam.eppo.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class UnitFlowService implements JavaDelegate {
    /**
     * executes the task to process the units.
     * 
     * @param execution
     */
    public void execute(final DelegateExecution execution) {
        System.out.println("Unit Flow exxecute.");
    }

}
