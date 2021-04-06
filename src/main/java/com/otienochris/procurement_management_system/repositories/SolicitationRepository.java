package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolicitationRepository extends JpaRepository<Solicitation, UUID> {
}
