package com.otienochris.procurement_management_system.repositories;
import com.otienochris.procurement_management_system.*;


import com.otienochris.procurement_management_system.models.PurchaseRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseRequisitionRepo extends JpaRepository<PurchaseRequisition, UUID> {
}
