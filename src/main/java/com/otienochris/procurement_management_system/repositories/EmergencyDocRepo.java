package com.groupwork.Explorers.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupwork.Explorers.model.Docs.EmergencyDoc;

public interface EmergencyDocRepo extends JpaRepository<EmergencyDoc, Integer> {

}
