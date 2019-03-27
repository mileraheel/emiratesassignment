package com.emirates.test.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {
	
	@Id
	private String id;
	
	//@Field
	private String name;

	//@Field
	private Boolean completed;
	
	public Task() {}
	
	public Task(String name, Boolean completed) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.completed = completed;
	}
	
	public void setId() {
		this.id = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Task[name=%s, completed=%s]", this.name, this.completed);
	}
}
