package com.procurement.procure.dao;

import com.procurement.procure.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "department", path = "department")
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
