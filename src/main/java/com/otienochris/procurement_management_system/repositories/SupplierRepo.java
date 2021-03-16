package com.group4.procurement1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.group4.procurement1.model.Supplier;

@RepositoryRestResource(collectionResourceRel = "supplier" , path = "supplier")
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
