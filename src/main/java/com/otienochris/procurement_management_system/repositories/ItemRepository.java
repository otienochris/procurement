package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByBatchSerialNumber(String batchNumber);
}
