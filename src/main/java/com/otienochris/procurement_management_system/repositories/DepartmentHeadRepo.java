package com.otienochris.procurement_management_system.repositories;


import com.otienochris.procurement_management_system.models.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentHeadRepo extends JpaRepository<DepartmentHead, String> {
    Optional<DepartmentHead> findByEmail(String email);

    boolean existsByEmail(String toEmail);

    Optional<DepartmentHead> findByUser_Username(String username);
}
