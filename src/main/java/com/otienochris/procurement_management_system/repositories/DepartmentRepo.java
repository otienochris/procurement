package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<Department, UUID> {
}
