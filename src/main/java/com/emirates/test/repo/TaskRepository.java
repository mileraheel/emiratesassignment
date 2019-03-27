package com.emirates.test.repo;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.emirates.test.model.Task;


@RepositoryRestResource
@Lazy
public interface TaskRepository extends JpaRepository <Task, String>{
	//@RestResource(path = "byEmail", rel = "customFindMethod")
	List<Task> findByName(@Param("name") String name);
	List<Task> findByCompleted(@Param("completed") Boolean completed);
}


