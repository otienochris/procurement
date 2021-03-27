package com.procurement.procure.controller;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long empId){
        super("Could not find employee" +empId);
    }
    /*String employeeNotFoundHandler(EmployeeNotFoundException ex){
        return ex.getMessage();*/
    }
