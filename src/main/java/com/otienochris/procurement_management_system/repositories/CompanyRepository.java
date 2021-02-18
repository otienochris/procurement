package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByKraPin(String kra);
}
