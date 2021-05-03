package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.RequestForQuotationDto;
import com.otienochris.procurement_management_system.responses.RequestForQuotationResponse;
import com.otienochris.procurement_management_system.services.RequestForQuotationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rfqs")
@Slf4j
@RequiredArgsConstructor
public class RequestForQuotationController {

    private final RequestForQuotationService requestForQuotationService;

    @GetMapping("/{id}")
    public ResponseEntity<RequestForQuotationResponse> getRFQById(@PathVariable("id") UUID id) {
        log.info("A get request to retrieve a rfq with id: " + id);
        return new ResponseEntity<>(requestForQuotationService.getRFQById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestForQuotationResponse>> allRFQs() {
        return new ResponseEntity<>(requestForQuotationService.allFRQs(), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestForQuotationResponse> upload(@Validated RequestForQuotationDto requestForQuotationDto) {
        log.info("A post request to upload a rfq");
        return new ResponseEntity<>(requestForQuotationService.saveRFQ(requestForQuotationDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") UUID id,
                                    @Valid @NotNull @Length(max = 10) @RequestPart(value = "message") String message,
                                    @Valid @NotNull @RequestPart(value = "quotationDocument") MultipartFile quotationDocument,
                                    @Valid @NotNull @RequestPart(value = "termsAndConditions") MultipartFile termsAndConditions,
                                    @Valid @NotNull @RequestPart(value = "purchaseOrderId") UUID purchaseOrderId
    ) {
        log.info("A put request to update a rfq with id: " + id);
        RequestForQuotationDto requestForQuotationDto = RequestForQuotationDto.builder()
                .termsAndConditions(termsAndConditions)
                .message(message)
                .purchaseOrderId(purchaseOrderId)
                .quotationDocument(quotationDocument)
                .build();
        requestForQuotationService.updateRFQ(id, requestForQuotationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        log.info("A delete request to delete a rfq with id: " + id);
        requestForQuotationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
