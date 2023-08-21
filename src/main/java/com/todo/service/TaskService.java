package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Task;
import com.todo.repo.TaskRepo;

@Service
public class TaskService  {

	@Autowired
	private TaskRepo repo;
	
	public Task createRepo(Task task) {
		return repo.save(task);
	}
	
	public List<Task> findAll(){
		return repo.findAll();
	}
}
