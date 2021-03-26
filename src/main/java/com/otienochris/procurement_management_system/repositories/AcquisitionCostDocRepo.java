package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.AcquisitionCostDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;


public interface AcquisitionCostDocRepo extends JpaRepository<AcquisitionCostDoc, Integer> {
	
}
