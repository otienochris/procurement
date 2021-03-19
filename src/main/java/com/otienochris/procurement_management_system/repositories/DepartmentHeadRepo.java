package com.procurement.procure.dao;


import com.procurement.procure.model.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "departmentHead", path = "departmentHead")
public interface DepartmentHeadRepo extends JpaRepository<DepartmentHead, Integer> {

}
