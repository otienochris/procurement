package com.otienochris.procurement_management_system.repositories;



import com.otienochris.procurement_management_system.models.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "departmentHead", path = "departmentHead")
public interface DepartmentHeadRepo extends JpaRepository<DepartmentHead, Integer> {

}
