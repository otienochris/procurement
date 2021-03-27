package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
