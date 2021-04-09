package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.DepartmentHead;
import com.otienochris.procurement_management_system.services.DepartmentHeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department-heads")

@RequiredArgsConstructor
public class DepartmentHeadController {

    private final DepartmentHeadService departmentHeadService;

    //create a department head
    @PostMapping("/")
    public ResponseEntity<DepartmentHead> createDepartmentHead(@RequestBody DepartmentHead departmentHead){
        return new ResponseEntity<>(departmentHeadService.createDepartmentHead(departmentHead), HttpStatus.CREATED);
    }
    //get a department head by id
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentHead> getDepartmentHead(@PathVariable("id") String id) {
        return new ResponseEntity<>(departmentHeadService.getDepartmentHead(id), HttpStatus.OK);
    }

    //get all department heads
    @GetMapping("/")
    public ResponseEntity<List<DepartmentHead>> getAllDepartmentHeads(){
        return ResponseEntity.ok(departmentHeadService.getAllDepartmentHeads());
    }

    //update details on a department head
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartmentHead(@RequestBody DepartmentHead newDepartmentHead, @PathVariable String empId){
        departmentHeadService.updateDepartmentHead(newDepartmentHead, empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete a department head
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartmentHead(@PathVariable (value = "id") String empId){
        departmentHeadService.deleteDepartmentHead(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
