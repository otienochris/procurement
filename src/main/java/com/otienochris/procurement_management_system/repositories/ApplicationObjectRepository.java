package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.ApplicationObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationObjectRepository extends JpaRepository<ApplicationObject, Integer> {
}
