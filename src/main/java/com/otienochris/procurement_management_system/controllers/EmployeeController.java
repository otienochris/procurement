package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Employee;
import com.otienochris.procurement_management_system.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")

@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //create one employee
    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    //Getting all employees
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    //get employee by id
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "empId") long empId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
    }

    //update an employee
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long empId) {
        employeeService.updateEmployee(newEmployee, empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete employee by id
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("empId") Long empId) {
        employeeService.deleteEmployeeById(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
