package com.group4.procurement.dao.newones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.procurement.model.newones.documents.NeedDocument;

public interface NeedDocumentRepo extends JpaRepository<NeedDocument, Integer> {

}
