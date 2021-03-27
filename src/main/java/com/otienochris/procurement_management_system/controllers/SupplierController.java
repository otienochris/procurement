package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.Supplier;
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
    @PostMapping("/")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.createSupplier(supplier), HttpStatus.CREATED);
    }

    //get an supplier by id
    @GetMapping("/{kra}")
    public ResponseEntity<Supplier> getSupplier(@RequestParam("kra") String kra) {
        return new ResponseEntity<>(supplierService.getSupplier(kra), HttpStatus.OK);
    }

    //get all suppliers
    @GetMapping("/")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    //update a supplier
    @PutMapping("/update/{KRA}")
    public ResponseEntity<?> updateSupplier(Supplier newSupplier, String kra) {
        supplierService.updateSupplier(newSupplier, kra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete a supplier
    @DeleteMapping(value = "/delete/{kra}")
    public ResponseEntity<?> deleteSupplier(String KRA) {
        supplierService.deleteSupplier(KRA);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
