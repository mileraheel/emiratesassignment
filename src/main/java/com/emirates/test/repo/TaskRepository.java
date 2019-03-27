package com.emirates.test.repo;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Service;

import com.emirates.test.model.Task;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "task", viewName = "all")
@Service
public interface TaskRepository extends CouchbaseRepository<Task, String> {
	
	List<Task> findByName(String name);
	
	/*@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND name like $0")
	List<Task> searchByName(String name);*/
	
	List<Task> findByCompleted(Boolean completed);
	
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
	List<Task> findAll();
}
