package com.todo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
	
	@Query("select t from Task t order by t.date")
	public List<Task> findAllTasks();

}
