package com.todo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todo.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
	
	@Query("select t from Task t order by t.date, t.time")
	public List<Task> findAllTasks();
	
	@Query(value="select * from task t where t.date=:date",nativeQuery=true)
	public List<Task> getForToday(@Param("date") String date);

}
