package com.emirates.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.emirates.test.model.Task;
import com.emirates.test.service.TaskService;
import com.emirates.test.util.CustomErrorType;

/**
 * The application rest api class.
 * All communication between front end
 * and backend are done through this class.
 * @author Raheel
 *
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	private TaskService taskService; //Service which will do all data retrieval/manipulation work

	
	/**
	 * This will return all the available tasks in the application.
	 * @return list of all available tasks.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTasks() {
		logger.info("request received to load all the tasks.");
		List<Task> tasks = taskService.findAllTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info(tasks.size() + " tasks found .");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	/**
	 * This method will return all the pending tasks, not yet completed.
	 * @return list of pending tasks.
	 */
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllPendingTasks() {
		logger.info("request received to load all pending tasks.");
		List<Task> tasks = taskService.findAllPending();
		if (tasks.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info(tasks.size() + " pending tasks found .");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	/**
	 * This will return all completed tasks.
	 * @return List of completed tasks.
	 */
	@RequestMapping(value = "/completed", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllCompletedTasks() {
		logger.info("request received to load all completed tasks.");
		List<Task> tasks = taskService.findAllCompleted();
		if (tasks.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		logger.info(tasks.size() + " completed tasks found .");
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	/**
	 * Get tasks by id.
	 * @param id, id of the task to get.
	 * @return task with the id.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTask(@PathVariable("id") String id) {
		logger.info("Fetching Task with id {}", id);
		Task task = taskService.findByName(id);
		if (task == null) {
			logger.error("Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Task with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	/**
	 * method takes post request to create a new task.	
	 * @param task
	 * @param ucBuilder
	 * @return persisted task is returned.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Task : {}", task);

		if (taskService.isTaskExist(task)) {
			logger.error("Unable to create. A Task with name {} already exist", task.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Task with name " + 
			task.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		taskService.saveTask(task);
		return new ResponseEntity<Task>(task, HttpStatus.CREATED);
	}

	/**
	 * this takes put request to update the existing task.
	 * @param id 
	 * @param task
	 * @return updated task is returned.
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		logger.info("Updating Task with name {}", task.getName());
		Task currentTask = taskService.get(task.getId());

		if (currentTask == null) {
			logger.error("Unable to update. Task with name {} not found.", task.getName());
			return new ResponseEntity(new CustomErrorType("Unable to upate. Task with name " + task.getName() + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentTask.setName(task.getName());
		currentTask.setCompleted(task.getCompleted());

		taskService.updateTask(currentTask);
		return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	}

	/**
	 * method takes delete request to delete task by id.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting Task with id {}", id);

		Task task = taskService.get(id);
		if (task == null) {
			logger.error("Unable to delete. Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Task with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		taskService.deleteTask(task);
		return new ResponseEntity<Task>(HttpStatus.ACCEPTED);
	}
}