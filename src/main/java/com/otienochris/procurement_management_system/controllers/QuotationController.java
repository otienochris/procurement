package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.QuotationDto;
import com.otienochris.procurement_management_system.responses.QuotationResponse;
import com.otienochris.procurement_management_system.services.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quotations")
public class QuotationController {

    private final QuotationService quotationService;

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<QuotationResponse>> getAllQuotation(){
        return new ResponseEntity<>(quotationService.gelAllQuotations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<QuotationResponse> getQuotationById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(quotationService.getQuotationById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<QuotationResponse> saveQuotation(@Validated QuotationDto quotationDto){
        return new ResponseEntity<>(quotationService.saveQuotation(quotationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuotation(@PathVariable("id") Integer id){
        quotationService.deleteQuotation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuotation(@PathVariable("id") Integer id, @Validated QuotationDto quotationDto) throws IOException {
        quotationService.updateQuotation(id, quotationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
