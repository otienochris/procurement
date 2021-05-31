package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Department;
import com.otienochris.procurement_management_system.models.DepartmentHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
