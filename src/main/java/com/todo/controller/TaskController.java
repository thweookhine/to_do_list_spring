package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Task;
import com.todo.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService service;
	
	@GetMapping("/")
	public List<Task> findAll(){
		return service.findAll();
	}
	
}
