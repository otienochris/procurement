package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, String> {
}
