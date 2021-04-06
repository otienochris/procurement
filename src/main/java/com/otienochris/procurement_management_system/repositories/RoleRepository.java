package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
