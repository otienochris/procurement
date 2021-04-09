package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String toEmail);
}
