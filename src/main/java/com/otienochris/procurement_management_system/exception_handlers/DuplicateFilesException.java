package com.otienochris.procurement_management_system.exception_handlers;

public class DuplicateFilesException extends RuntimeException {
    public DuplicateFilesException(String message) {
        super("The file name already exists");
    }
}
