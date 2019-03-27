package com.emirates.test.service;


import java.util.List;

import com.emirates.test.model.Task;

/**
 * task service interface to be used in controller.
 * @author Raheel
 *
 */
public interface TaskService {
	
	/**
	 * get document by Id.
	 * @param name
	 * @return
	 */
	Task get(String id);
	
	/**
	 * method to find task by name.
	 * @param name
	 * @return task for the given name.
	 */
	Task findByName(String id);

	/**
	 * method to save a new task.
	 * @param task
	 */
	void saveTask(Task task);
	
	/**
	 * method updates the task.
	 * @param task
	 */
	void updateTask(Task task);

	/**
	 * method to delete the task by id.
	 * @param name
	 */
	void deleteTask(Task task);
	
	/**
	 * method to delete all the available tasks
	 * in the database.
	 * @return
	 */
	List<Task> findAllTasks();
	
	/**
	 * method to check if task exists.
	 * @param task
	 * @return
	 */
	boolean isTaskExist(Task task);
	
	/**
	 * method to find all completed tasks
	 * @return list of completed tasks.
	 */
	List<Task> findAllCompleted();
	
	/**
	 * method to get all pending tasks.
	 * @return list of pending tasks.
	 */
	List<Task> findAllPending();
	
	/**
	 * method will save list of tasks 
	 * for testing purpose.
	 * @param tasks
	 */
	void saveTasks(List<Task> tasks);
	
	
	/**
	 * delete all the tasks in the database;
	 */
	void deleteAllTasks();
}