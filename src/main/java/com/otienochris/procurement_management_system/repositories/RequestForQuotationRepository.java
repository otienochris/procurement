package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.RequestForQuotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequestForQuotationRepository extends JpaRepository<RequestForQuotation, UUID> {
}
