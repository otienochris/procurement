package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.EmployeeDto;
import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.*;
import com.otienochris.procurement_management_system.repositories.EmployeeRepo;
import com.otienochris.procurement_management_system.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    //getting all employees
    public List<EmployeeResponse> getAllEmployees() {

        List<EmployeeResponse> responses = new ArrayList<>();
        employeeRepo.findAll().forEach(employee -> {
            responses.add(createResponse(employee));
        });

        return responses;
    }


    //create an employee
    public EmployeeResponse createEmployee(EmployeeDto employeeDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(employeeDto.getPassword());

        EmployeePositionEnum position = employeeDto.getPosition();

        User user = User.builder()
                .username(employeeDto.getEmpId())
                .isActive(false)
                .password(encodedPassword)
                .roles(Set.of(assignRole(position))) // assignRole is a helper method
                .build();

        Employee newEmployee = Employee.builder()
                .email(employeeDto.getEmail())
                .empId(employeeDto.getEmpId())
                .name(employeeDto.getName())
                .position(employeeDto.getPosition())
                .user(user)
                .build();
        return createResponse(employeeRepo.save(newEmployee));
    }

    //getting one employee by Id
    public EmployeeResponse getEmployeeById(String id) {
        return createResponse(
                employeeRepo.findById(id).orElseThrow(() -> {
                    throw new EmployeeNotFoundException(id);
                }));
    }

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

    //    only the admin or the human resource manager can change the employee's position
    public void employeesPositionUpdate(String username, EmployeePositionEnum newPosition) {
        employeeRepo.findById(username).ifPresentOrElse(employee -> {
            if (employee.getPosition() != newPosition) {
                employee.setPosition(newPosition);
                employee.getUser().setRoles(Set.of(assignRole(newPosition)));
            }
        }, () -> {
            throw new EmployeeNotFoundException("Employee with username: " + username + " is not found!");
        });
    }

    //    todo shift to the users service
    public void employeesRoleUpdate(String username, Set<Role> newRoles) {
        employeeRepo.findById(username).ifPresentOrElse(employee -> {

            Set<Role> oldRoles = employee.getUser().getRoles();
            if (oldRoles != newRoles) {
                employee.getUser().setRoles(newRoles);
            }

        }, () -> {
            throw new EmployeeNotFoundException("Employee with username: " + username + " is not found!");
        });
    }

    //delete employee by id
    public void deleteEmployeeById(String empId) throws EmployeeNotFoundException {
        employeeRepo.findById(empId).ifPresentOrElse(employeeRepo::delete, () -> {
            throw new EmployeeNotFoundException(empId);
        });
    }


    //    helper methods
    private Role assignRole(EmployeePositionEnum position) {
        return switch (position) {
            case PROCUREMENT_MANAGER, PROCUREMENT_OFFICER -> Role.builder()
                    .role(RoleEnum.ROLE_ADMIN)
                    .build();
            case INVENTORY_MANAGER, FINANCE, STORES_MANAGER -> Role.builder()
                    .role(RoleEnum.ROLE_MODERATOR)
                    .build();
            case ICT_MANAGER, PURCHASING_ASSISTANT, HUMAN_RESOURCE_MANAGER, INTERN -> Role.builder()
                    .role(RoleEnum.ROLE_USER)
                    .build();
            default -> Role.builder()
                    .role(RoleEnum.ROLE_UNSPECIFIED)
                    .build();
        };
    }

    private EmployeeResponse createResponse(Employee employee) {
        return EmployeeResponse.builder()
                .dataCreated(employee.getUser().getDataCreated())
                .dateModified(employee.getUser().getDateModified())
                .employmentId(employee.getEmpId())
                .position(employee.getPosition().name())
                .email(employee.getEmail())
                .isActive(employee.getUser().getIsActive())
                .name(employee.getName())
                .roles(employee.getUser().getRoles())
                .build();
    }
}
