package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.EmployeeDto;
import com.otienochris.procurement_management_system.models.Employee;
import com.otienochris.procurement_management_system.responses.EmployeeResponse;
import com.otienochris.procurement_management_system.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")

@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;

    //create one employee
    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Validated EmployeeDto employeeDto,
                                                           HttpServletRequest request) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto, request), HttpStatus.CREATED);
    }

    //Getting all employees
    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    //get employee by id
    @GetMapping(value = "/{empId}", produces = "application/json")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable(value = "empId") String empId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
    }

    //update an employee
    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Validated EmployeeDto newEmployeeDto, @PathVariable String empId) {
        employeeService.updateEmployee(newEmployeeDto, empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete employee by id
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("empId") String empId) {
        employeeService.deleteEmployeeById(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
