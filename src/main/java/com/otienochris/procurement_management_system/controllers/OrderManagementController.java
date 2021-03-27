package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orderManagement/")
@RestController
@Slf4j
public class OrderManagementController {

    OrderManagementService orderManagementService;


    @GetMapping("/getFile/{id}")
    public ResponseEntity<OrderManagementResponse> getById(@PathVariable("id") Long id) {
        log.info("A get request to retrieve a Order Management document with id: " + id);
        return new ResponseEntity<>(orderManagementService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getFiles")
    public ResponseEntity<List<OrderManagementResponse>> getFiles(){
        return new ResponseEntity<>(orderManagementService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<OrderManagementResponse> save(@Validated OrderManagementDto orderManagementDto){
        return new ResponseEntity<>(orderManagementService.saveOrderManagement(orderManagementDto), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Validated OrderManagementDto orderManagementDto){
        orderManagementService.updateOrderManagement(id, orderManagementDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        orderManagementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
