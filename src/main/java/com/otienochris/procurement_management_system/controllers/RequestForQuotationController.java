package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.models.RequestForQuotation;
import com.otienochris.procurement_management_system.services.RequestForQuotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ap1/v1/rfqs")
@Slf4j
public class RequestForQuotationController {

    @Autowired
    private RequestForQuotationService requestForQuotationService;

//    todo get by id
    @GetMapping("/{id}")
    public ResponseEntity<RequestForQuotationDto> getRFQById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(requestForQuotationService.getRFQById(id), HttpStatus.OK);
    }

//    todo get all
    @GetMapping("/all")
    public ResponseEntity<List<RequestForQuotation>> allRFQs(){
        return new ResponseEntity<>(requestForQuotationService.allFRQs(), HttpStatus.OK);
    }

//    todo upload
    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestForQuotationDto> upload(@Validated RequestForQuotationDto requestForQuotationDto) throws IOException {
        return new ResponseEntity<>(requestForQuotationService.saveRFQ(requestForQuotationDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id,@Validated RequestForQuotationDto requestForQuotationDto) throws IOException {
        requestForQuotationService.updateRFQ(id, requestForQuotationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    todo delete
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        requestForQuotationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
