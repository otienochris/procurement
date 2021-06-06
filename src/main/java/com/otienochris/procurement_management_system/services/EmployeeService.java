package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.EmployeeDto;
import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.*;
import com.otienochris.procurement_management_system.models.enums.RoleEnum;
import com.otienochris.procurement_management_system.repositories.EmployeeRepo;
import com.otienochris.procurement_management_system.repositories.RoleRepository;
import com.otienochris.procurement_management_system.responses.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    //getting all employees
    public List<EmployeeResponse> getAllEmployees() {

        List<EmployeeResponse> responses = new ArrayList<>();
        employeeRepo.findAll().forEach(employee -> {
            responses.add(createResponse(employee));
        });

        return responses;
    }


    //create an employee
    public EmployeeResponse createEmployee(EmployeeDto employeeDto, HttpServletRequest request) {

        if (employeeRepo.existsByEmail(employeeDto.getEmail()))
            throw new DuplicateKeyException("Employee with email: " + employeeDto.getEmail() + " already exists!");
        if (employeeRepo.existsById(employeeDto.getEmpId())) {
            throw new DuplicateKeyException("Employee with employee id: " + employeeDto.getEmpId() + " already exists!");
        }


        String encodedPassword = encoder.encode(employeeDto.getPassword());
        EmployeePositionEnum position = employeeDto.getPosition();

        User user = User.builder()
                .username(employeeDto.getEmpId())
                .isActive(false)
                .password(encodedPassword)
                .roles(computeRole(position))
                .build();

        Employee newEmployee = Employee.builder()
                .email(employeeDto.getEmail())
                .empId(employeeDto.getEmpId())
                .name(employeeDto.getName())
                .position(employeeDto.getPosition())
                .user(user)
                .build();
        Employee savedUser = employeeRepo.save(newEmployee);

        userService.sendEmailVerificationToken(savedUser.getEmpId(),employeeDto.getEmail());

        return createResponse(savedUser);
    }

    //getting one employee by Id
    public EmployeeResponse getEmployeeById(String id) {
        return createResponse(
                employeeRepo.findById(id).orElseThrow(() -> {
                    throw new EmployeeNotFoundException(id);
                }));
    }

    public void updateEmployee(EmployeeDto newEmployeeDto, String empId) {
        EmployeePositionEnum position = null;

        if (newEmployeeDto.getPosition() != null){
            position = newEmployeeDto.getPosition();
        }

        EmployeePositionEnum finalPosition = position;

        employeeRepo.findById(empId).ifPresentOrElse(employee -> {
            User user = employee.getUser();

            employee.setEmail(newEmployeeDto.getEmail());
            employee.setName(newEmployeeDto.getName());
            if (finalPosition != null){
                employee.setPosition(finalPosition);
                user.setRoles(computeRole(finalPosition));
            }

            employee.setUser(user);
            System.out.println(employee);
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
                employee.getUser().setRoles(computeRole(newPosition));
            }
        }, () -> {
            throw new EmployeeNotFoundException("Employee with username: " + username + " is not found!");
        });
    }

    //    todo shift to the users service
    public void employeesRoleUpdate(String username, Role newRoles) {
        employeeRepo.findById(username).ifPresentOrElse(employee -> {

            Role oldRoles = employee.getUser().getRoles();
            if (oldRoles != newRoles) {
                employee.getUser().setRoles(newRoles);
            }

        }, () -> {
            throw new EmployeeNotFoundException("Employee with username: " + username + " is not found!");
        });
    }

    //delete employee by id
    public void deleteEmployeeById(String empId) throws EmployeeNotFoundException {
        employeeRepo.findById(empId).ifPresentOrElse(employee -> {
            userService.deleteUser(empId);
            employeeRepo.delete(employee);
        }, () -> {
            throw new EmployeeNotFoundException(empId);
        });
    }


    //    helper methods
    private Role computeRole(EmployeePositionEnum position) {
        return switch (position) {
            case PROCUREMENT_MANAGER, PROCUREMENT_OFFICER -> roleRepository.findByRole(RoleEnum.ROLE_ADMIN).orElse(
                    Role.builder()
                            .role(RoleEnum.ROLE_ADMIN)
                            .build());
            case INVENTORY_MANAGER, FINANCE, STORES_MANAGER -> roleRepository.findByRole(RoleEnum.ROLE_MODERATOR).orElse(
                    Role.builder()
                            .role(RoleEnum.ROLE_MODERATOR)
                            .build());
            case ICT_MANAGER, PURCHASING_ASSISTANT, HUMAN_RESOURCE_MANAGER, INTERN -> roleRepository.findByRole(RoleEnum.ROLE_USER).orElse(
                    Role.builder()
                            .role(RoleEnum.ROLE_USER)
                            .build());
            default -> roleRepository.findByRole(RoleEnum.ROLE_UNSPECIFIED).orElse(
                    Role.builder()
                            .role(RoleEnum.ROLE_UNSPECIFIED)
                            .build());
        };
    }

    private EmployeeResponse createResponse(Employee employee) {
        return EmployeeResponse.builder()
                .dataCreated(employee.getUser().getDateCreated())
                .dateModified(employee.getUser().getDateModified())
                .id(employee.getEmpId())
                .position(employee.getPosition().name())
                .email(employee.getEmail())
                .isActive(employee.getUser().getIsActive())
                .name(employee.getName())
                .roles(employee.getUser().getRoles())
                .build();
    }
}
