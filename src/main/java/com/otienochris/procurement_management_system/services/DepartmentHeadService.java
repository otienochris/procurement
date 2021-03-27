package com.procurement.procure.services;

import com.procurement.procure.controller.EmployeeNotFoundException;
import com.procurement.procure.dao.DepartmentHeadRepo;
import com.procurement.procure.model.DepartmentHead;
import com.procurement.procure.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentHeadService {
    @Autowired
    DepartmentHeadRepo departmentHeadRepo;

    //create a department head
    public DepartmentHead createDepartmentHead(@RequestBody DepartmentHead departmentHead){

        return departmentHeadRepo.save(departmentHead);
    }

    //get a department head by id
    public Optional<DepartmentHead> getDepartmentHead(Long id) {

        return departmentHeadRepo.findById(id.intValue());
    }
    //get all department heads
    public List<DepartmentHead> getAllDepartmentHeads(){

        return departmentHeadRepo.findAll();
    }

    //delete a department head
    public RequestEntity.BodyBuilder deleteDepartmentHead(@PathVariable Long id) throws EmployeeNotFoundException {
        Optional<DepartmentHead> departmentHead = departmentHeadRepo.findById(id.intValue());
        departmentHeadRepo.deleteById(id.intValue());
        return (RequestEntity.BodyBuilder) ResponseEntity.ok();

    }
    //update details on a department head
    public DepartmentHead updateDepartmentHead(DepartmentHead newDepartmentHead, Long empId) {
        return departmentHeadRepo.findById(empId.intValue()).map(departmentHead -> {
            departmentHead.setName(newDepartmentHead.getName());
            departmentHead.setPassword(newDepartmentHead.getPassword());
            departmentHead.setEmail(newDepartmentHead.getEmail());
            return departmentHeadRepo.save(departmentHead);
        }).orElseGet(() -> {
            newDepartmentHead.setId(newDepartmentHead.getId());
            return departmentHeadRepo.save(newDepartmentHead);
        });
    }

    }

