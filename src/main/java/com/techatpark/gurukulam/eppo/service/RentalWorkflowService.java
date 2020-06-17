package com.techatpark.gurukulam.eppo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.techatpark.gurukulam.eppo.model.RentalLocation;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentalWorkflowService {

    /**
     * the proess engine.
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * the task ervie.
     */
    @Autowired
    private TaskService taskService;

    /**
     * starting the process engine.
     * 
     * @param rentalLocation
     */
    @Transactional
    public void startProcess(final RentalLocation rentalLocation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", rentalLocation.getId());
        variables.put("locationName", rentalLocation.getRentalLocationName());
        runtimeService.startProcessInstanceByKey("rentalFlow", variables);
    }

    /**
     * gets the list of tasks.
     * 
     * @param assignee
     * @return tasks
     */
    @Transactional
    public List<RentalLocation> getTasks(final String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(assignee).list();
        return tasks.stream().map(task -> {
            Map<String, Object> variables = taskService.getVariables(task.getId());
            return new RentalLocation(Integer.parseInt(task.getId()), (String) variables.get("LocationName"));
        }).collect(Collectors.toList());
    }

    /**
     * Submit the rental location.
     * 
     * @param rentalLocation
     */
    @Transactional
    public void submitReview(final RentalLocation rentalLocation) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", rentalLocation.getStatus());
        taskService.complete(rentalLocation.getId().toString(), variables);
    }
}
