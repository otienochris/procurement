package com.group4.procurement1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.group4.procurement1.model.Contract;

@RepositoryRestResource(collectionResourceRel = "contract" , path = "contract")
public interface ContractRepo extends JpaRepository<Contract, Integer> {

}
