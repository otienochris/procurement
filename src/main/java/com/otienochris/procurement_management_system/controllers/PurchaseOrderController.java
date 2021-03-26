package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.PurchaseOrderDto;
import com.otienochris.procurement_management_system.models.POStatus;
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
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-orders")
@Slf4j
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponse> getPurchaseOrder(@PathVariable @Valid Long id) {
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
    public ResponseEntity<PurchaseOrderResponse> savePurchaseOrder(@Validated PurchaseOrderDto purchaseOrder) {
        log.info("Saving a purchase order [in the purchase order controller]");
        return new ResponseEntity<>(purchaseOrderService.savePO(purchaseOrder), HttpStatus.CREATED);
    }


    @PutMapping(value = "/update/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable("id") Long id,
                                                 @NotNull @RequestPart("rfiTemplate") MultipartFile rfiTemplate,
                                                 @NotNull @RequestPart("rfpTemplate") MultipartFile rfpTemplate,
                                                 @NotNull @RequestPart("status") POStatus status
                                                 ) {
        log.info("Updating the purchase order with id: " + id + "[in the purchase order controller]");

        PurchaseOrderDto newPurchaseOrderDto = PurchaseOrderDto.builder()
                .rfpTemplate(rfpTemplate)
                .rfiTemplate(rfiTemplate)
                .status(status)
                .build();
        purchaseOrderService.updatePO(id, newPurchaseOrderDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePurchaseOrder(@PathVariable("id") Long id) {
        log.info("Deleting a purchase order with id: " + id + "[in the purchase order controller]");
        purchaseOrderService.deletePO(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
