package com.otienochris.procurement_management_system.repositories;


import com.otienochris.procurement_management_system.models.NeedDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "needDoc", path = "needDoc")
public interface NeedDocRepo extends JpaRepository<NeedDoc, Integer> {

}
