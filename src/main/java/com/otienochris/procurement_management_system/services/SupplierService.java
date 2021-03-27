package com.procurement.procure.services;

import com.procurement.procure.controller.EmployeeNotFoundException;
import com.procurement.procure.dao.EmployeeRepo;
import com.procurement.procure.dao.SupplierRepo;
import com.procurement.procure.model.Employee;
import com.procurement.procure.model.Supplier;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    SupplierRepo supplierRepo;
    //create an supplier
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierRepo.save(supplier);
    }
    //get an supplier by id
    public Optional<Supplier> getSupplier(String kra) {
         return supplierRepo.findById(kra);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    //delete a supplier
    public RequestEntity.BodyBuilder deleteSupplier(@PathVariable String KRA) throws EmployeeNotFoundException {
        Optional<Supplier> supplier = supplierRepo.findById(KRA);
        supplierRepo.deleteById(KRA);
        return (RequestEntity.BodyBuilder) ResponseEntity.ok();

    }

    //update a supplier
    public static Supplier updateSupplier(@RequestBody Supplier newSupplier, @PathVariable String KRA, SupplierRepo supplierRepo) {
        return supplierRepo.findById(KRA).map(supplier -> {
            supplier.setName(newSupplier.getName());
            supplier.setPassword(newSupplier.getPassword());
            supplier.setDescription(newSupplier.getDescription());
            return supplierRepo.save(supplier);
        }).orElseGet(() -> {
            newSupplier.setKRA(KRA);
            return supplierRepo.save(newSupplier);
        });
    }

}
