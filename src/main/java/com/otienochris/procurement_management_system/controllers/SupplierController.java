package com.procurement.procure.controller;

import com.procurement.procure.dao.SupplierRepo;
import com.procurement.procure.model.Employee;
import com.procurement.procure.model.Supplier;
import com.procurement.procure.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    public SupplierRepo supplierRepo;
    @Autowired
    SupplierService supplierService;

    //create an supplier
    @PostMapping("/")
    public Supplier createSupplier(@RequestBody Supplier supplier){
        return supplierService.createSupplier(supplier);
    }

    //get an supplier by id
    @GetMapping("/{id}")
    public Object getSupplier(@RequestParam("id") String KRA){
        return supplierService.getSupplier(KRA);

    //get all suppliers
        @GetMapping("/suppliers")
        public List<Supplier> getAllSuppliers(){

            return supplierService.getAllSuppliers();
        }

    //update a supplier
        @PutMapping("/suppliers/{KRA}")
        public Supplier updateSupplier(Supplier newSupplier, String KRA){
            return supplierService.updateSupplier(newSupplier, KRA);
        }

    //delete a supplier
        @DeleteMapping(value = "/suppliers/{kra}")
        public void deleteSupplier(String KRA)

        supplierService.deleteSupplier(KRA);

    }
