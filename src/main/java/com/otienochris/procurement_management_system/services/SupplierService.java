package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.Supplier;
import com.otienochris.procurement_management_system.repositories.SupplierRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepo supplierRepo;

    //update a supplier
    public void updateSupplier(Supplier newSupplier, String kra) {
        supplierRepo.findById(kra).ifPresentOrElse(supplier -> {
            supplier.setDescription(newSupplier.getDescription());
            supplier.setName(newSupplier.getName());
            supplier.setPassword(newSupplier.getPassword());
        }, () -> {
            throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
        });
    }

    //create an supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    //get an supplier by id
    public Supplier getSupplier(String kra) {
        return supplierRepo.findById(kra).orElseThrow(() -> {
            throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
        });
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    //delete a supplier
    public void deleteSupplier(String kra) {
        supplierRepo.findById(kra).orElseThrow(() -> {
            throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
        });
    }

}
