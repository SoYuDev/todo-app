package com.luis.todoapp.task.service;

import com.luis.todoapp.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
// We could also use @RequiredArgsConstructor (Lombok) instead of typing the constructor manually.
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
