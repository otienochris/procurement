package com.groupwork.Explorers.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.groupwork.Explorers.model.PurchaseRequisition;


@RepositoryRestResource(collectionResourceRel = "purchaseRequisition" , path = "purchaseRequisition")
public interface PurchaseRequisitionRepo extends JpaRepository<PurchaseRequisition, Integer> {

}
