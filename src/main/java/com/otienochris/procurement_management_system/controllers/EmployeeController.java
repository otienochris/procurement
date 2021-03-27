package com.procurement.procure.controller;

import com.procurement.procure.dao.EmployeeRepo;
import com.procurement.procure.model.Employee;
import com.procurement.procure.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    EmployeeService employeeService;

    //create one employee
    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    //getting one employee
    /*@GetMapping("/{id}")
    public Optional<Employee> getEmployee(@RequestParam("id") long empId){
        return employeeService.getEmployee(empId);
    }*/

    //Getting all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){

        return employeeService.getAllEmployees();
    }

    //get employee by id
    @GetMapping("/employees/{empId}")
    public Employee getEmployeeById(@PathVariable(value = "empId") long empId){
        return employeeRepo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
    }

    //update an employee
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long empId){
        return employeeService.updateEmployee(newEmployee, empId);
    }

    //delete employee by id
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable (value = "empId") Long empId){

        employeeService.deleteEmployee(empId);
    }

}
