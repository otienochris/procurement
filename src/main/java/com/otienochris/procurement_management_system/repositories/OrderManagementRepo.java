package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.OrderManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "orderManagement" , path = "orderManagement")
public interface OrderManagementRepo extends JpaRepository<OrderManagement, Integer> {

}
