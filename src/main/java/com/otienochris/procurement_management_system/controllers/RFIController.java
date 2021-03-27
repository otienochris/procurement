package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.RFIDto;
import com.otienochris.procurement_management_system.responses.RFIResponse;
import com.otienochris.procurement_management_system.services.RFIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rfi/")
@RestController
@Slf4j
public class RFIController {

    RFIService rfiService;

    @GetMapping("/getFiles")
    public ResponseEntity<List<RFIResponse>> getAll() {
        return new ResponseEntity<>(rfiService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getFile/{id}")
    public ResponseEntity<RFIResponse> getById(@PathVariable("id") Long id) {
        log.info("A get request to retrieve an RFI Requisition document with id: " + id);
        return new ResponseEntity<>(rfiService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RFIResponse> save(@Validated RFIDto rfiDto) {
        return new ResponseEntity<>(rfiService.saveRFI(rfiDto), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Validated RFIDto rfiDto) {
        rfiService.updateRFI(id, rfiDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        rfiService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
