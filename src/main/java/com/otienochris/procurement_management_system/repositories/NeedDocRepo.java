package com.groupwork.Explorers.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.groupwork.Explorers.model.Docs.NeedDoc;

@RepositoryRestResource(collectionResourceRel = "needDoc", path = "needDoc")
public interface NeedDocRepo extends JpaRepository<NeedDoc, Integer> {

}
