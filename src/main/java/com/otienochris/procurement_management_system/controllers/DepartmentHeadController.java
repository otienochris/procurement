package com.procurement.procure.controller;

import com.procurement.procure.dao.DepartmentHeadRepo;
import com.procurement.procure.model.DepartmentHead;
import com.procurement.procure.model.Employee;
import com.procurement.procure.services.DepartmentHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class DepartmentHeadController {
    @Autowired
    private DepartmentHeadRepo departmentHeadRepo;
    @Autowired
    DepartmentHeadService departmentHeadService;
    //create a department head
    @PostMapping("/")
    public DepartmentHead createDepartmentHead(@RequestBody DepartmentHead departmentHead){
        return departmentHeadService.createDepartmentHead(departmentHead);
    }
    //get a department head by id
    @GetMapping("/{id}")
    public Optional<DepartmentHead> getDepartmentHead(@RequestParam("id") long id) {
        return departmentHeadService.getDepartmentHead(id);
    }

    //get all department heads
    @GetMapping("/departmentHeads")
    public List<DepartmentHead> getAllDepartmentHeads(){

        return departmentHeadService.getAllDepartmentHeads();
    }

    //update details on a department head
    @PutMapping("/departmentHeads/{id}")
    public DepartmentHead updateDepartmentHead(@RequestBody DepartmentHead newDepartmentHead, @PathVariable Long empId){
        return departmentHeadService.updateDepartmentHead(newDepartmentHead, empId);
    }


    //delete a department head
    @DeleteMapping("/departmentHeads/{id}")
    public void deleteDepartmentHead(@PathVariable (value = "id") Long empId){

        departmentHeadService.deleteDepartmentHead(empId);
    }

}
