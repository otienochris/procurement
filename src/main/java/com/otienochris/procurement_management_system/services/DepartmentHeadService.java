package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.DepartmentHeadDto;
import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.DepartmentHead;
import com.otienochris.procurement_management_system.models.Role;
import com.otienochris.procurement_management_system.models.enums.RoleEnum;
import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.repositories.DepartmentHeadRepo;
import com.otienochris.procurement_management_system.repositories.RoleRepository;
import com.otienochris.procurement_management_system.responses.DepartmentHeadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentHeadService {

    private final DepartmentHeadRepo departmentHeadRepo;
    private final UserService userService;
    private final RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public DepartmentHeadResponse createDepartmentHead(DepartmentHeadDto departmentHeadDto) {

        String encodedPassword = encoder.encode(departmentHeadDto.getPassword());
        User user = User.builder()
                .username(departmentHeadDto.getEmpId())
                .isActive(false)
                .roles(roleRepository.findByRole(RoleEnum.ROLE_SUPPLIER).orElse(
                        Role.builder()
                                .role(RoleEnum.ROLE_SUPPLIER)
                                .build()))
                .password(encodedPassword)
                .build();
        DepartmentHead departmentHead = DepartmentHead.builder()
                .email(departmentHeadDto.getEmail())
                .empId(departmentHeadDto.getEmpId())
                .departmentId(departmentHeadDto.getDepartmentId())
                .name(departmentHeadDto.getName())
                .user(user)
                .build();

        System.out.println("Department id " + departmentHead.getDepartmentId());
        System.out.println("Emp id " + departmentHead.getEmpId());
        DepartmentHead saved = departmentHeadRepo.save(departmentHead);
        userService.sendEmailVerificationToken(saved.getEmpId(), saved.getEmail());

        return createResponse(saved);
    }

    //get a department head by id
    public DepartmentHeadResponse getDepartmentHead(String id) {
        return createResponse(departmentHeadRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The department with id: " + id + " is not found!");
        }));
    }

    //get all department heads
    public List<DepartmentHeadResponse> getAllDepartmentHeads() {
        List<DepartmentHeadResponse> responses = new ArrayList<>();
        departmentHeadRepo.findAll().forEach(departmentHead -> {
            responses.add(createResponse(departmentHead));
        });
        return responses;
    }

    //delete a department head
    public void deleteDepartmentHead(String id) {
        departmentHeadRepo.findById(id).ifPresentOrElse(departmentHeadRepo::delete, () -> {
            throw new EmployeeNotFoundException(id);
        });

    }

    //update details on a department head
    public void updateDepartmentHead(DepartmentHeadDto newDepartmentHead, String empId) {
        departmentHeadRepo.findById(empId).ifPresentOrElse(departmentHead -> {
            departmentHead.setName(newDepartmentHead.getName());
            departmentHead.setEmail(newDepartmentHead.getEmail());
            departmentHead.setDepartmentId(newDepartmentHead.getDepartmentId());
            departmentHeadRepo.save(departmentHead);
        }, () -> {
            throw new NoSuchElementException("The department with id: " + empId + " does not exist");
        });

    }

    private DepartmentHeadResponse createResponse(DepartmentHead departmentHead) {
        return DepartmentHeadResponse.builder()
                .departmentId(departmentHead.getDepartmentId())
                .email(departmentHead.getEmail())
                .isActive(departmentHead.getUser().getIsActive())
                .name(departmentHead.getName())
                .empId(departmentHead.getEmpId())
                .build();
    }
}
