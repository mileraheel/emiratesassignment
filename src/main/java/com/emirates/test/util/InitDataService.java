package com.emirates.test.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emirates.test.model.Task;
import com.emirates.test.service.TaskService;

/**
 * this is the service to create
 * init data for sample transactions.
 * @author Raheel
 *
 */

@Service
public class InitDataService {

	public static final int initialTasks = 10;
	
	@Autowired
	private TaskService taskService;
	
	@PostConstruct
	public void createSampleTasks1() {
		
		taskService.deleteAllTasks();
		
		for (int i = 1; i <= initialTasks; i++) {
			Task task = new Task("Sample Task " + i, Math.random() < 0.5);
			taskService.saveTask(task);
		}
	}
}
