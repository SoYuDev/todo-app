package com.luis.todoapp.task.repository;

import com.luis.todoapp.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
