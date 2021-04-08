package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.SupplierDto;
import com.otienochris.procurement_management_system.models.Supplier;
import com.otienochris.procurement_management_system.responses.SupplierResponse;
import com.otienochris.procurement_management_system.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    //create a supplier
    @PostMapping("/signup")
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody SupplierDto supplierDto) {
        return new ResponseEntity<>(supplierService.createSupplier(supplierDto), HttpStatus.CREATED);
    }

    //get an supplier by id
    @GetMapping("/{kra}")
    public ResponseEntity<SupplierResponse> getSupplier(@RequestParam("kra") String kra) {
        return new ResponseEntity<>(supplierService.getSupplier(kra), HttpStatus.OK);
    }

    //get all suppliers
    @GetMapping("/")
    public List<SupplierResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    //update a supplier
    @PutMapping("/update/{KRA}")
    public ResponseEntity<?> updateSupplier(SupplierDto supplierDto, String kra) {
        supplierService.updateSupplier(supplierDto, kra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete a supplier
    @DeleteMapping(value = "/delete/{kra}")
    public ResponseEntity<?> deleteSupplier(String KRA) {
        supplierService.deleteSupplier(KRA);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
