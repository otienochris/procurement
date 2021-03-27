package com.otienochris.procurement_management_system.exception_handlers;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer empId) {
        super("Could not find employee with id: " + empId);
    }
}
