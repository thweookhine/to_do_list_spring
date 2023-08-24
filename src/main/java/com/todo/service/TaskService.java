package com.todo.service;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Task;
import com.todo.repo.TaskRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class TaskService  {

	@Autowired
	private TaskRepo repo;
	
	public Task createTask(Task task) {
		return repo.save(task);
	}
	
	public Task updateTask(Task input) {
		Optional<Task> taskData = repo.findById(input.getId());
		if(taskData.isPresent()) {
			Task task = taskData.get();
			task.setTitle(input.getTitle());
			task.setDescription(input.getDescription());
			task.setDate(input.getDate());
			task.setComplete(input.isComplete());
			if(input.getTime() != null) {
				task.setTime(input.getTime());
			}
			return repo.save(task);
		}
		return null;
	}
	
	public void deleteTask(long id) {
		repo.deleteById(id);
	}
	
	public List<Task> findAll(){
		return repo.findAllTasks();
	}
	
	public Optional<Task> findById(long id) {
		return repo.findById(id);
	}
	
	public List<Task> findForToday(){
		LocalDate today = LocalDate.now();
		return repo.getForToday(today.toString());
	}
}
