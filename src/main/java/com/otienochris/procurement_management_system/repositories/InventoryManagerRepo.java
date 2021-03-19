package com.procurement.procure.dao;

import com.procurement.procure.model.InventoryManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "inventoryManager", path = "inventoryManager")
public interface InventoryManagerRepo extends JpaRepository<InventoryManager, Integer> {
}
