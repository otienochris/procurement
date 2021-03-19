package com.groupwork.Explorers.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

import com.groupwork.Explorers.model.Docs.AcquisitionCostDoc;

public interface AcquisitionCostDocRepo extends JpaRepository<AcquisitionCostDoc, Integer> {
	
}
