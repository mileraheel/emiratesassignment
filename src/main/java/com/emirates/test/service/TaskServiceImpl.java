package com.emirates.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emirates.test.model.Task;
import com.emirates.test.repo.TaskRepository;

/**
 * Task service implementation to perform 
 * all crud operations, method docs are 
 * defined in interface.
 * @author Raheel
 *
 */
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;

	public Task findByName(String name) {
		Task task = null;
		List<Task> tasks = taskRepository.findByName(name);
		if (tasks !=null && !tasks.isEmpty()) {
			task = tasks.get(0);
		}
		return task;
	}

	public void saveTask(Task task) {
		if (task.getId() == null) {
			task.setId();
		}
		Task updatedTask = taskRepository.save(task);
		updatedTask.getName();
	}

	public void updateTask(Task task){
		saveTask(task);
	}

	public void deleteTask(Task task){
		taskRepository.delete(task);
	}

	public void deleteAllTasks(){
		taskRepository.deleteAll();
	}

	public List<Task> findAllTasks(){
		return taskRepository.findAll();
	}

	public boolean isTaskExist(Task task) {
		return taskRepository.existsById(task.getName());
	}

	@Override
	public List<Task> findAllCompleted() {
		return taskRepository.findByCompleted(true);
	}

	@Override
	public List<Task> findAllPending() {
		return taskRepository.findByCompleted(false);
	}
	
	public void saveTasks(List<Task> tasks) {
		taskRepository.saveAll(tasks);
	}

	@Override
	public Task get(String id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.get();
	}

}
