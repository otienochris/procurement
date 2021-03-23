package com.group4.procurement.dao.newones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.procurement.model.newones.documents.EmergencyDoc;

public interface EmergencyDocRepo extends JpaRepository<EmergencyDoc, Integer> {

}
