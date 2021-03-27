package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.exception_handlers.EmployeeNotFoundException;
import com.otienochris.procurement_management_system.models.DepartmentHead;
import com.otienochris.procurement_management_system.repositories.DepartmentHeadRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentHeadService {

    private final DepartmentHeadRepo departmentHeadRepo;

    //create a department head
    public DepartmentHead createDepartmentHead(DepartmentHead departmentHead) {
        return departmentHeadRepo.save(departmentHead);
    }

    //get a department head by id
    public DepartmentHead getDepartmentHead(Integer id) {
        return departmentHeadRepo.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The department with id: " + id + " is not found!");
        });
    }

    //get all department heads
    public List<DepartmentHead> getAllDepartmentHeads() {
        return departmentHeadRepo.findAll();
    }

    //delete a department head
    public void deleteDepartmentHead(Integer id) {
        departmentHeadRepo.findById(id).ifPresentOrElse(departmentHeadRepo::delete, () -> {
            throw new EmployeeNotFoundException(id);
        });

    }

    //update details on a department head
    public void updateDepartmentHead(DepartmentHead newDepartmentHead, Long empId) {
        departmentHeadRepo.findById(empId.intValue()).ifPresentOrElse(departmentHead -> {
            departmentHead.setName(newDepartmentHead.getName());
            departmentHead.setPassword(newDepartmentHead.getPassword());
            departmentHead.setEmail(newDepartmentHead.getEmail());
            departmentHead.setDepartment(newDepartmentHead.getDepartment());
            departmentHeadRepo.save(departmentHead);
        }, () -> {
            throw new NoSuchElementException("The department with id: " + empId + " does not exist");
        });

    }
}

