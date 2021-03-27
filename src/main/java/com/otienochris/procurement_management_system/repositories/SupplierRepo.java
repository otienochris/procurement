package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "supplier", path = "supplier")
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}
