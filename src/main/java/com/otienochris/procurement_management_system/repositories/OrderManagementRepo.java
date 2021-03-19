package com.groupwork.Explorers.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.groupwork.Explorers.model.OrderManagement;

@RepositoryRestResource(collectionResourceRel = "orderManagement" , path = "orderManagement")
public interface OrderManagementRepo extends JpaRepository<OrderManagement, Integer> {

}
