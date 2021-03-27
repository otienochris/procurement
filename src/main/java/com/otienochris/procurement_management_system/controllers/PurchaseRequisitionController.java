package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.PurchaseRequisitionDto;
import com.otienochris.procurement_management_system.responses.PurchaseRequisitionResponse;
import com.otienochris.procurement_management_system.services.PurchaseRequisitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-requisitions/")
@Slf4j
@RequiredArgsConstructor
public class PurchaseRequisitionController {

    private final PurchaseRequisitionService purchaseRequisitionService;


    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRequisitionResponse> getById(@PathVariable("id") Long id) {
        log.info("A get request to retrieve a Purchase Requisition document with id: " + id);
        return new ResponseEntity<>(purchaseRequisitionService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PurchaseRequisitionResponse>> getAll() {
        return new ResponseEntity<>(purchaseRequisitionService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PurchaseRequisitionResponse> save(@Validated PurchaseRequisitionDto purchaseRequisitionDto) {
        return new ResponseEntity<>(purchaseRequisitionService.savePurchaseRequisition(purchaseRequisitionDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Validated PurchaseRequisitionDto purchaseRequisitionDto) {
        purchaseRequisitionService.updatePurchaseRequisition(id, purchaseRequisitionDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        purchaseRequisitionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
