package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.ApplicationObjectDto;
import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import com.otienochris.procurement_management_system.responses.ApplicationObjectResponse;
import com.otienochris.procurement_management_system.services.ApplicationObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationObjectController {
    private final ApplicationObjectService applicationObjectService;

    @GetMapping("/")
    public ResponseEntity<List<ApplicationObjectResponse>> allRFQs() {
        return new ResponseEntity<>(applicationObjectService.allFRQs(), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationObjectResponse> save(@Validated ApplicationObjectDto requestForQuotationDto) {
        return new ResponseEntity<>(applicationObjectService.saveRFQ(requestForQuotationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationObjectResponse> getRFQById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(applicationObjectService.getRFQById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Integer id, ApplicationObjectDto applicationObjectDto) {
        applicationObjectService.updateRFQ(id, applicationObjectDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        applicationObjectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/approve/{id}/{status}")
    public ResponseEntity<?> approve(@PathVariable Integer id, @PathVariable POStatusEnum status){
        applicationObjectService.handleApprovals(id, status);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
