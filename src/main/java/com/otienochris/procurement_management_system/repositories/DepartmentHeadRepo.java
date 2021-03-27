package com.procurement.procure.dao;


import com.procurement.procure.model.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentHeadRepo extends JpaRepository<DepartmentHead, Integer> {

}
