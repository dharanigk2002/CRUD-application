package com.employee_details.employees.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String message;
    public EmployeeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
