package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.responses.QuotationResponse;
import com.otienochris.procurement_management_system.services.QuotationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quotations")
@Slf4j
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

    @GetMapping("/{id}")
    public ResponseEntity<QuotationResponse> getQuotationById(@PathVariable("id") Long id){
        log.info("A request to retrieve a quotation with id: " + id);
        return new ResponseEntity<>(quotationService.getQuotationById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuotationResponse>> getAllQuotations(){
        log.info("A request to retrieve all quotations.");
        return new ResponseEntity<>(quotationService.gelAllQuotations(), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuotationResponse> saveQuotation(@Validated QuotationDto quotationDto) {
        log.info("Save request containing, file: " + quotationDto.getQuotationAttachment().getOriginalFilename());
        return new ResponseEntity<>(quotationService.saveQuotation(quotationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuotation(@PathVariable("id") Long id){
        log.info("Request to delete a quotation with id: " + id);
        quotationService.deleteQuotation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateQuotation(@PathVariable("id") Long id,
                                             @Validated QuotationDto newQuotation) throws IOException {
        log.info("Request to update a quotation with id: " + id);
        quotationService.updateQuotation(id, newQuotation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
