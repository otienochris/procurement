package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.models.PurchaseOrder;
import com.otienochris.procurement_management_system.responses.PurchaseOrderResponse;
import com.otienochris.procurement_management_system.services.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchase-orders")
@Slf4j
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponse> getPurchaseOrder(@PathVariable @Valid Integer id) {
        log.info("Getting the purchase order with id: " + id + "[In the purchase order controller]");
        return new ResponseEntity<>(purchaseOrderService.getPOById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrderResponse>> getAllPOs() {
        log.info("Getting all purchase orders [in the purchase order controller]");
        return new ResponseEntity<>(purchaseOrderService.getAllPO(), HttpStatus.OK);
    }

    @PostMapping(value = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PurchaseOrderResponse> savePurchaseOrder(@Validated PurchaseOrderDto purchaseOrderDto) {
        return new ResponseEntity<>(purchaseOrderService.savePO(purchaseOrderDto), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable("id") Integer id,
                                                 @Validated PurchaseOrderDto purchaseOrder) {
        log.info("Updating the purchase order with id: " + id + "[in the purchase order controller]");
        purchaseOrderService.updatePO(id, purchaseOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePurchaseOrder(@PathVariable("id") Integer id) {
        log.info("Deleting a purchase order with id: " + id + "[in the purchase order controller]");
        purchaseOrderService.deletePO(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
