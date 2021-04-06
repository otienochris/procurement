package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.EmployeeDto;
import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.Employee;
import com.otienochris.procurement_management_system.models.Role;
import com.otienochris.procurement_management_system.models.RoleEnum;
import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public void updateEmployee(EmployeeDto newEmployeeDto, String empId) {
        employeeRepo.findById(empId).ifPresentOrElse(employee -> {
            employee.setEmail(newEmployeeDto.getEmail());
            employee.setName(newEmployeeDto.getName());
            employee.getUser().setPassword(newEmployeeDto.getPassword());
            employeeRepo.save(employee);
        }, () -> {
            throw new EmployeeNotFoundException(empId);
        });

    }

    //create an employee
    public Employee createEmployee(EmployeeDto employeeDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(employeeDto.getPassword());

        User user = User.builder()
                .username(employeeDto.getEmpId())
                .isActive(false)
                .password(encodedPassword)
                .roles(Set.of(
                        Role.builder()
                                .role(RoleEnum.ROLE_USER)
                                .build()))
                .build();
        Employee newEmployee = Employee.builder()
                .email(employeeDto.getEmail())
                .empId(employeeDto.getEmpId())
                .name(employeeDto.getName())
                .user(user)
                .build();
        return employeeRepo.save(newEmployee);
    }

    //getting one employee by Id
    public Employee getEmployeeById(String id) {
        return employeeRepo.findById(id).orElseThrow(() -> {
            throw new EmployeeNotFoundException(id);
        });
    }

    //getting all employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    //delete employee by id
    public void deleteEmployeeById(String empId) throws EmployeeNotFoundException {
        employeeRepo.findById(empId).ifPresentOrElse(employeeRepo::delete, () -> {
            throw new EmployeeNotFoundException(empId);
        });
    }

}
