package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.services.PurchaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchaseorders")
@Slf4j
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDto> getPurchaseOrder(@PathVariable @Valid Long id){
        return new ResponseEntity<>(purchaseOrderService.getPOById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrder>> getAllPOs(){
        return new ResponseEntity<>(purchaseOrderService.getAllPO(), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PurchaseOrderDto> savePurchaseOrder(@Validated PurchaseOrderDto purchaseOrder) throws IOException {
        return new ResponseEntity<>(purchaseOrderService.savePO(purchaseOrder), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable("id") Long id,
                                                @Validated PurchaseOrderDto purchaseOrder) throws IOException {
        purchaseOrderService.updatePO(id, purchaseOrder);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePurchaseOrder(@PathVariable("id") Long id){
        purchaseOrderService.deletePO(id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
