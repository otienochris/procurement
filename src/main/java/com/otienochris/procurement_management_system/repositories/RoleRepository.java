package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Role;
import com.otienochris.procurement_management_system.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(RoleEnum roleSupplier);
}
