package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
}
