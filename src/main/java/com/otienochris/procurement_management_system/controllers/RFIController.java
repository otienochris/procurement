package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.RFIDto;
import com.otienochris.procurement_management_system.responses.RFIResponse;
import com.otienochris.procurement_management_system.services.RFIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rfis")
@Slf4j
@RequiredArgsConstructor
public class RFIController {

    private final RFIService rfiService;

    @GetMapping("/")
    public ResponseEntity<List<RFIResponse>> getAll() {
        return new ResponseEntity<>(rfiService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RFIResponse> getById(@PathVariable("id") Long id) {
        log.info("A get request to retrieve an RFI Requisition document with id: " + id);
        return new ResponseEntity<>(rfiService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RFIResponse> save(@Validated RFIDto rfiDto) {
        return new ResponseEntity<>(rfiService.saveRFI(rfiDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
