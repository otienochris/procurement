package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Role;
import com.otienochris.procurement_management_system.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(RoleEnum roleSupplier);
}
