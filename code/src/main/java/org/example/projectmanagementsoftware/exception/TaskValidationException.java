package org.example.projectmanagementsoftware.exception;

public class TaskValidationException extends RuntimeException {
    public TaskValidationException(String message) {
        super(message);
    }
}