package com.procurement.procure.dao;

import com.procurement.procure.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "supplier", path = "supplier")
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}
