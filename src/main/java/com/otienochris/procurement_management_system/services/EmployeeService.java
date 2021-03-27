package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.Employee;
import com.otienochris.procurement_management_system.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public void updateEmployee(Employee newEmployee, Long empId) {
        employeeRepo.findById(empId).ifPresentOrElse(employee -> {
            employee.setEmail(newEmployee.getEmail());
            employee.setName(newEmployee.getName());
            employee.setPassword(newEmployee.getPassword());
            employee.setRole(newEmployee.getRole());
            employee.setPosition(newEmployee.getPosition());
            employeeRepo.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(empId.intValue());
        });

    }

    //create an employee
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    //getting one employee by Id
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> {
            throw new EmployeeNotFoundException(id.intValue());
        });
    }

    //getting all employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    //delete employee by id
    public void deleteEmployeeById( Long empId) throws EmployeeNotFoundException {
        employeeRepo.findById(empId).ifPresentOrElse(employeeRepo::delete, () -> {
            throw new EmployeeNotFoundException(empId.intValue());
        });
    }

}
