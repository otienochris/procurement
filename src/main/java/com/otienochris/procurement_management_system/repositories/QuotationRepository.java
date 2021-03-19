package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
/*
    @Query("SELECT " +
            "new Quotation(q.id, q.version, q.date_created, q.date_modified) " +
            "FROM quotations as q ORDER BY q.date_created")
    @Override
    List<Quotation> findAll();*/

}
