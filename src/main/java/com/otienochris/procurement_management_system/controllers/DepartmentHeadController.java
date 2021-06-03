package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.DepartmentHeadDto;
import com.otienochris.procurement_management_system.models.DepartmentHead;
import com.otienochris.procurement_management_system.responses.DepartmentHeadResponse;
import com.otienochris.procurement_management_system.services.DepartmentHeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/department-heads")

@RequiredArgsConstructor
public class DepartmentHeadController {

    private final DepartmentHeadService departmentHeadService;

    //create a department head
    @PostMapping("/signup")
    public ResponseEntity<DepartmentHeadResponse> createDepartmentHead(@RequestBody @Validated DepartmentHeadDto departmentHeadDto){
        return new ResponseEntity<>(departmentHeadService.createDepartmentHead(departmentHeadDto), HttpStatus.CREATED);
    }
    //get a department head by id
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentHeadResponse> getDepartmentHead(@PathVariable("id") String id) {
        return new ResponseEntity<>(departmentHeadService.getDepartmentHead(id), HttpStatus.OK);
    }

    //get all department heads
    @GetMapping("/")
    public ResponseEntity<List<DepartmentHeadResponse>> getAllDepartmentHeads(){
        return ResponseEntity.ok(departmentHeadService.getAllDepartmentHeads());
    }

    //update details on a department head
    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateDepartmentHead(@RequestBody DepartmentHeadDto newDepartmentHeadDto, @PathVariable String empId){
        String id = empId.replaceAll("_","/");
        departmentHeadService.updateDepartmentHead(newDepartmentHeadDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete a department head
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartmentHead(@PathVariable (value = "id") String empId){
        String id = empId.replaceAll("_","/");
        departmentHeadService.deleteDepartmentHead(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
