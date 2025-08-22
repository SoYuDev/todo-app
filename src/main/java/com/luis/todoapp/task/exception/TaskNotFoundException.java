package com.luis.todoapp.task.exception;

import jakarta.persistence.EntityNotFoundException;

// Exception thrown when a Task entity cannot be found in the database.
public class TaskNotFoundException extends EntityNotFoundException {

    // Creates a TaskNotFoundException with a custom message.
    public TaskNotFoundException(String message) {
        super(message);
    }
    // Creates a TaskNotFoundException for a missing Task by ID.
    public TaskNotFoundException(Long id) {
        super("Task with ID: %d not found.".formatted(id));
    }
}
