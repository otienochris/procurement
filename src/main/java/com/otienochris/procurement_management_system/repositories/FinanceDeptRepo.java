package com.procurement.procure.dao;

import com.procurement.procure.model.FinanceDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "financeDept", path = "financeDept")
public interface FinanceDeptRepo extends JpaRepository<FinanceDept, Integer> {
}
