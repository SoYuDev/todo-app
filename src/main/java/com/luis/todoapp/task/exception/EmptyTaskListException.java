package com.luis.todoapp.task.exception;

// Exception thrown when there are any Tasks in the database.
public class EmptyTaskListException extends RuntimeException {
    public EmptyTaskListException(String message) {
        super(message);
    }
}
