package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.services.QuotationService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/quotations")
@Slf4j
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationService quotationService;

    @GetMapping("/{id}")
    public ResponseEntity<QuotationDto> getQuotationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(quotationService.getQuotationById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuotationDto>> getAllQuotations(){
        List<QuotationDto> quotationDtos = quotationService.gelAllQuotations();
        quotationDtos.forEach(quotationDto -> {
            quotationDto.setQuotationAttachment(null);
        });
        return new ResponseEntity<>(quotationDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<QuotationDto> saveQuotation(@Validated QuotationDto quotationDto) {
        log.info("Request contains, file: " + quotationDto.getQuotationAttachment().getOriginalFilename());

        QuotationDto saved = quotationService.saveQuotation(quotationDto);
        saved.setQuotationAttachment(null);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuotation(@PathVariable("id") Long id){
        quotationService.deleteQuotation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateQuotation(@PathVariable("id") Long id,
                                             @Validated QuotationDto newQuotation) throws IOException {
        quotationService.updateQuotation(id, newQuotation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
