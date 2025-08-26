package com.luis.todoapp.task.service;

import com.luis.todoapp.task.exception.TaskNotFoundException;
import com.luis.todoapp.task.model.Task;
import com.luis.todoapp.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// We could also use @RequiredArgsConstructor (Lombok) instead of typing the constructor manually.
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        List<Task> result = taskRepository.findAll();

        if (result.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }

        return result;
    }
}
