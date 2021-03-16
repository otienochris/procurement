package com.group4.procurement1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.group4.procurement1.model.Department;
@RepositoryRestResource(collectionResourceRel = "department", path = "department")
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
