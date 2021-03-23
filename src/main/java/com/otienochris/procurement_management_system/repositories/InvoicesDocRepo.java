package com.group4.procurement.dao.newones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.procurement.model.newones.documents.InvoicesDoc;

public interface InvoicesDocRepo extends JpaRepository<InvoicesDoc, Integer> {

}
