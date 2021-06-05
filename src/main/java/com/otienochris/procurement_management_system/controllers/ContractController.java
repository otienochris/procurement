package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.ContractDto;
import com.otienochris.procurement_management_system.responses.ContractResponse;
import com.otienochris.procurement_management_system.services.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/")
    public ResponseEntity<List<ContractResponse>> getAllContracts(){
        return new ResponseEntity<>(contractService.getAllContracts(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} ,
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<ContractResponse> saveContract(@Validated ContractDto contractDto){
        return new ResponseEntity<>(contractService.save(contractDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable Integer id){
        contractService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(
            value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} ,
    produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<?> updateContract(@PathVariable Integer id, ContractDto contractDto){
        contractService.updateContract(id, contractDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
