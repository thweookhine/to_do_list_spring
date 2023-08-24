package com.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.todo.model.Task;
import com.todo.service.TaskService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class TaskController {

	@Autowired
	private TaskService service;

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = service.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/taskForToday")
	public ResponseEntity<List<Task>> taskForToday(){
		List<Task> list = service.findForToday();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/createTask")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		
		Task result = service.createTask(task);
		if (result == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task update) {
		update.setId(id);
		Task task = service.updateTask(update);
		if(task !=null) {
			return new ResponseEntity<>(task,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id){
		try {
			service.deleteTask(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/task")
	public ResponseEntity<Task> findById(@RequestParam("id") long id){
		Optional<Task> taskData = service.findById(id);
		if(taskData.isPresent()) {
			return new ResponseEntity<>(taskData.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}

}
