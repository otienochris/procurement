package com.procurement.procure.dao;

import com.procurement.procure.model.ProcurementOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "procurementOfficer", path = "procurementOfficer")
public interface ProcurementOfficerRepo extends JpaRepository<ProcurementOfficer, Integer> {
}
