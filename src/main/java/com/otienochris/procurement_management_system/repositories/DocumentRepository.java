package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Override
    @Query("SELECT new Document (d.id, d.dateCreated, d.dateModified, d.type, d.fileName)" +
            " FROM Document d WHERE d.id=:aLong")
    Optional<Document> findById(@Param("aLong") Long aLong);

    @Query("SELECT new Document (d.id, d.dateCreated, d.dateModified, d.type, d.fileName)" +
            " FROM Document d order by d.dateCreated")
    @Override
    List<Document> findAll();

    Optional<Document> findByFileName(String fileName);
}
