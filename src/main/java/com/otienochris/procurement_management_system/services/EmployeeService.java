package com.procurement.procure.services;

import com.procurement.procure.controller.EmployeeNotFoundException;
import com.procurement.procure.dao.EmployeeRepo;
import com.procurement.procure.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
private EmployeeRepo employeeRepo;

    //create an employee
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepo.save(employee);
    }
//getting one employee by Id
 public Optional<Employee> getEmployee(Long id){

     return employeeRepo.findById(id);
 }
 //getting all employees
 public List<Employee> getAllEmployees(){

        return employeeRepo.findAll();
 }
 //update an employee
 public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long empId){
     return getEmployee(newEmployee, empId, employeeRepo);
 }

    public static Employee getEmployee(@RequestBody Employee newEmployee, @PathVariable Long empId, EmployeeRepo employeeRepo) {
        return employeeRepo.findById(empId).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setPassword(newEmployee.getPassword());
            employee.setPosition(newEmployee.getPosition());
            employee.setEmail(newEmployee.getEmail());
            employee.setRole(newEmployee.getRole());
            return employeeRepo.save(employee);
        }).orElseGet(() -> {
            newEmployee.setEmpId(empId);
            return employeeRepo.save(newEmployee);
        });
    }
    //delete employee by id
    public RequestEntity.BodyBuilder deleteEmployee(@PathVariable Long empId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(empId);
        employeeRepo.deleteById(empId);
        return (RequestEntity.BodyBuilder) ResponseEntity.ok();

    }

}
